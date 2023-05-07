package com.argotedev.triviaapp.logic

interface Game {
    fun winnerRound(id: Int, score: Int)
    fun winnerGame(): Player
    fun endGame()
}
