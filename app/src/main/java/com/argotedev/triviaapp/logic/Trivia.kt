package com.argotedev.triviaapp.logic

import kotlinx.serialization.Serializable

@Serializable
class Trivia(private val players: List<Player>) : Game {

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