package com.argotedev.triviaapp.model

import com.google.gson.Gson

class Trivia(val players: List<Player>) : Game {

    override fun winnerRound(id: Int, score: Int) {
        val player = players.first { it.id == id}
        val index  = players.indexOf(player)
        if (index == -1) return
        players[index].setScore(score)
    }

    override fun winnerGame(): Player {
        return players.sortedBy { it.getScore() }[0]
    }

    override fun endGame() {
        players.forEach { player -> player.resetScore()  }
    }

}

fun Trivia.toJsonString(): String {
    return Gson().toJson(this)
}

fun String.toTriviaObject(): Trivia {
    return Gson().fromJson(this, Trivia::class.java)
}