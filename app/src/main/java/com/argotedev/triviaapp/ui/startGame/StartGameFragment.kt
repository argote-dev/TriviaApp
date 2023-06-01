package com.argotedev.triviaapp.ui.startGame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.argotedev.triviaapp.R
import com.argotedev.triviaapp.databinding.FragmentStartGameBinding
import com.argotedev.triviaapp.model.Trivia
import com.argotedev.triviaapp.ui.inGame.GameRouter

class StartGameFragment : Fragment() {

    private lateinit var binding: FragmentStartGameBinding
    private val viewModel: StartGameViewModel by viewModels()

    private lateinit var starGameRouter: GameRouter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartGameBinding.inflate(layoutInflater, container, false)
        starGameRouter = GameRouter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStratGame.setOnClickListener {
            val game = viewModel.startGame()
            starGameRouter.replace(
                requireActivity().supportFragmentManager,
                R.id.containerFragment,
                game as Trivia
            )
        }

        binding.inputNicknameOne.addTextChangedListener { editable ->
            editable?.let { value ->
                if (value.toString().isEmpty()) {
                    binding.btnStratGame.isClickable = false
                } else {
                    binding.btnStratGame.isClickable = true
                    viewModel.setFirstNickName(value.toString())
                }
            }
        }

        binding.inputNicknameTwo.addTextChangedListener { editable ->
            editable?.let { value ->
                if (value.toString().isEmpty()) {
                    binding.btnStratGame.isClickable = false
                } else {
                    binding.btnStratGame.isClickable = true
                    viewModel.setSecondNickName(value.toString())
                }
            }
        }
    }

}