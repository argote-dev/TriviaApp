package com.argotedev.triviaapp.model

interface Game {
    fun winnerRound(id: Int, score: Int)
    fun winnerGame(): Player
    fun endGame()
}
