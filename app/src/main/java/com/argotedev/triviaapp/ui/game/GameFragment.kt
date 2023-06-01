package com.argotedev.triviaapp.ui.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.argotedev.triviaapp.R
import com.argotedev.triviaapp.databinding.FragmentGameBinding
import com.argotedev.triviaapp.logic.Player
import com.argotedev.triviaapp.ui.startGame.StartGameFragment

class GameFragment : Fragment() {

    private var players: String? = null
    private lateinit var binding: FragmentGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            players = it.getString(StartGameFragment.KEY_BUNDLE_PLAYERS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(players: ArrayList<Player>) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(StartGameFragment.KEY_BUNDLE_PLAYERS, players)
                }
            }
    }
}