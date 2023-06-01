package com.argotedev.triviaapp.ui.inGame

import android.app.AlertDialog
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.argotedev.triviaapp.R
import com.argotedev.triviaapp.databinding.FragmentGameBinding
import com.argotedev.triviaapp.model.Answer
import com.argotedev.triviaapp.model.PlayerAnswer
import com.argotedev.triviaapp.model.Question
import com.argotedev.triviaapp.model.toTriviaObject
import com.argotedev.triviaapp.ui.inGame.adapter.AnswerAdapter
import com.argotedev.triviaapp.ui.inGame.adapter.listener.AnswerListener
import com.argotedev.triviaapp.ui.inGame.state.GameState
import com.argotedev.triviaapp.ui.startGame.StartGameRouter
import com.argotedev.triviaapp.utils.VibrationManager

class GameFragment : Fragment() {

    companion object {
        fun fragment() = GameFragment()
    }

    private lateinit var binding: FragmentGameBinding
    private val viewModel: GameViewModel by viewModels()

    private lateinit var startGame: StartGameRouter
    private lateinit var vibrateManager: VibrationManager

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var countDownTimer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        startGame = StartGameRouter()
        vibrateManager = VibrationManager(requireContext())
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.show)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { arguments ->
            GameRouter.game(arguments)?.let { trivia ->
                viewModel.setGame(trivia.toTriviaObject())
            }
        }

        countDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = (millisUntilFinished / 30000.0 * 100).toInt()
                binding.pbTime.progress = progress
            }

            override fun onFinish() {
                binding.pbTime.progress = 0
            }

        }

        viewModel.firstQuestion()?.let { question ->
            paintQuestion(question)
        }

        viewModel.game.observe(viewLifecycleOwner) { trivia ->
            trivia?.let { game ->
                if (game.players.isNotEmpty()) {
                    // Player 1
                    binding.tvNicknameOne.text =
                        getString(R.string.text_nickname, 1, game.players[0].nickname)
                    binding.tvScoreOne.text = game.players[0].getScore().toString()
                    // Player 2
                    binding.tvNicknameTwo.text =
                        getString(R.string.text_nickname, 2, game.players[1].nickname)
                    binding.tvScoreTwo.text = game.players[1].getScore().toString()
                }
            }
        }

        viewModel.gameState.observe(viewLifecycleOwner) { state ->
            state?.let { gameState ->
                when (gameState) {
                    GameState.STARTED -> {
                        mediaPlayer.start()
                    }

                    GameState.PAUSED -> {
                        mediaPlayer.pause()
                    }

                    GameState.FINISHED -> {
                        viewModel.showWinner()
                    }
                }
            }
        }

        viewModel.winner.observe(viewLifecycleOwner) { player ->
            player?.let { winner ->
                val alert = AlertDialog.Builder(requireContext())
                alert.setTitle(getString(R.string.text_title_alert))
                alert.setMessage(getString(R.string.text_body_alert, winner.nickname))
                alert.setPositiveButton(getText(R.string.text_exit_game)) { dialog, _ ->
                    dialog.dismiss()
                    goToStartGame()
                }
                alert.setCancelable(false)
                alert.create()
                alert.show()
            }
        }

        binding.btnFinish.setOnClickListener {
            goToStartGame()
        }

        viewModel.answers.observe(viewLifecycleOwner) { answers ->
            answers?.let { list ->
                if (list.size > 1) {
                    binding.btnNextQuestion.isEnabled = true
                }
            }
        }

        viewModel.counter.observe(viewLifecycleOwner) { counter ->
            binding.tvQuantityQuestions.text = getString(
                R.string.text_quantity_questions,
                counter,
                viewModel.getNumberOfQuestions()
            )
        }

    }

    private fun paintQuestion(question: Question) {
        binding.tvQuestion.text = question.title
        countDownTimer.start()

        with(binding.rvAnswersOne) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = AnswerAdapter(question.shuffleAnswers(), object : AnswerListener {
                override fun clickItem(answer: Answer) {
                    vibrate()
                    viewModel.game.value?.players?.get(0)?.let { player ->
                        viewModel.addAnswer(PlayerAnswer(player, answer))
                    }
                }
            })
        }

        with(binding.rvAnswersTwo) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = AnswerAdapter(question.shuffleAnswers(), object : AnswerListener {
                override fun clickItem(answer: Answer) {
                    vibrate()
                    viewModel.game.value?.players?.get(1)?.let { player ->
                        viewModel.addAnswer(PlayerAnswer(player, answer))
                    }
                }
            })
        }

        binding.btnNextQuestion.setOnClickListener {
            viewModel.nextQuestion(question)?.let { newQuestion ->
                binding.btnNextQuestion.isEnabled = false
                countDownTimer.cancel()
                paintQuestion(newQuestion)
            }
        }

    }

    private fun vibrate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrateManager.vibrate(200L)
        }
    }

    private fun goToStartGame() {
        startGame.replace(
            requireActivity().supportFragmentManager, R.id.containerFragment
        )
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.pause()
        countDownTimer.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

}