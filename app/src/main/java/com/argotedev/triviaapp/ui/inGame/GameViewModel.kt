package com.argotedev.triviaapp.ui.inGame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.argotedev.triviaapp.fake.FakeAnswers
import com.argotedev.triviaapp.model.Player
import com.argotedev.triviaapp.model.PlayerAnswer
import com.argotedev.triviaapp.model.Question
import com.argotedev.triviaapp.model.Trivia
import com.argotedev.triviaapp.ui.inGame.state.GameState

class GameViewModel : ViewModel() {

    private val questions: List<Question> = FakeAnswers.shuffledQuestions

    private var _game = MutableLiveData<Trivia>()
    val game: LiveData<Trivia> get() = _game

    private var _gameState = MutableLiveData<GameState>()
    val gameState: LiveData<GameState> get() = _gameState

    private var _winner = MutableLiveData<Player>()
    val winner: LiveData<Player> get() = _winner

    private var _answers = MutableLiveData<MutableList<PlayerAnswer>>()
    val answers: LiveData<MutableList<PlayerAnswer>> get() = _answers

    private var _counter = MutableLiveData(0)
    val counter: LiveData<Int> get() = _counter

    private var answerMutable = mutableListOf<PlayerAnswer>()

    fun setGame(game: Trivia) {
        _game.value = game
    }

    fun firstQuestion(): Question? {
        if (questions.isNotEmpty()) {
            _gameState.value = GameState.STARTED
            _counter.value = 1
            return questions[0]
        }
        return null
    }

    fun nextQuestion(question: Question): Question? {
        val beforeIndex = questions.indexOf(question)
        if (beforeIndex < questions.size - 1) {
            val nextIndex = beforeIndex + 1
            changeScore(question)
            cleanAnswer()
            _counter.value = _counter.value?.plus(1)
            return questions[nextIndex]
        }
        _gameState.value = GameState.FINISHED
        return null
    }

    private fun changeScore(question: Question) {
        _answers.value?.forEach { playerAnswer ->
            question.answers.find { it == playerAnswer.answer }
                ?.isCorrect.also { value ->
                    if (value == true) {
                        _game.value?.players?.indexOf(playerAnswer.player)
                            ?.also { index ->
                                val score = _game.value?.players?.get(index)?.getScore() ?: 0
                                _game.value?.players?.get(index)?.setScore(score + 10)
                                _game.value = _game.value
                            }
                    }
                }
        }
    }

    fun showWinner() {
        _game.value?.winnerGame()?.let { player ->
            _winner.value = player
        }
    }

    fun addAnswer(playerAnswer: PlayerAnswer) {
        answerMutable.add(playerAnswer)
        _answers.postValue(answerMutable)
    }

    private fun cleanAnswer() {
        answerMutable = mutableListOf()
        _answers.postValue(mutableListOf())
    }

    fun getNumberOfQuestions() = questions.size

}