package com.argotedev.triviaapp.ui.startGame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.argotedev.triviaapp.logic.Game
import com.argotedev.triviaapp.logic.Player
import com.argotedev.triviaapp.logic.Trivia

class StartGameViewModel : ViewModel() {

    private val _nicknamePlayerOne: MutableLiveData<String> = MutableLiveData()
    val nicknamePlayerOne: LiveData<String> = _nicknamePlayerOne

    private val _nicknamePlayerTwo: MutableLiveData<String> = MutableLiveData()
    val nicknamePlayerTwo: LiveData<String> = _nicknamePlayerTwo


    fun setFirstNickName(nickname: String) {
        _nicknamePlayerOne.value = nickname
    }

    fun setSecondNickName(nickname: String) {
        _nicknamePlayerTwo.value = nickname
    }

    fun startGame(): Game {
        return Trivia(
            listOf(
                Player(1, _nicknamePlayerOne.value.toString(), 0, 0),
                Player(2, _nicknamePlayerTwo.value.toString(), 0, 0)
            )
        )
    }

}