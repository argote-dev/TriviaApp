package com.argotedev.triviaapp.ui.startGame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.argotedev.triviaapp.databinding.FragmentStartGameBinding

class StartGameFragment : Fragment() {

    private lateinit var binding: FragmentStartGameBinding
    private val viewModel: StartGameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartGameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStratGame.setOnClickListener {
            viewModel.startGame()
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