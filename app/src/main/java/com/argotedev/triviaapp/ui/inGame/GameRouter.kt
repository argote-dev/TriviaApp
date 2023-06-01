package com.argotedev.triviaapp.ui.inGame

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.argotedev.triviaapp.model.Trivia
import com.argotedev.triviaapp.model.toJsonString
import com.argotedev.triviaapp.ui.base.BaseFragmentRouter

class GameRouter: BaseFragmentRouter {

    companion object {
        const val ARG_GAME = "game"

        fun game(bundle: Bundle): String? {
            return bundle.getString(ARG_GAME)
        }
    }

    override fun fragment(): GameFragment {
        return GameFragment.fragment()
    }

    private fun fragmentStartGame(game: String): GameFragment {
        val fragment = fragment()
        fragment.arguments = Bundle().apply {
            putString(ARG_GAME, game)
        }
        return fragment
    }

    fun replace(manager: FragmentManager, containerId: Int, startGame: Trivia) {
        manager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(containerId, fragmentStartGame(startGame.toJsonString()))
            .commit()
    }

}