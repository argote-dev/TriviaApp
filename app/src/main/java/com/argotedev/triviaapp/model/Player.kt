package com.argotedev.triviaapp.model

data class Player(
    val id: Int,
    val nickname: String,
    private var score: Int = 0,
    private var wins: Int = 0
) {
    fun resetScore() {
        score = 0
    }

    fun resetWins() {
        wins = 0
    }

    fun setScore(score: Int) {
        this.score = score
    }

    fun setWins(wins: Int) {
        this.wins = wins
    }

    fun getWins() = this.wins

    fun getScore() = this.score

}
