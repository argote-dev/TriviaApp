package com.argotedev.triviaapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.argotedev.triviaapp.databinding.ActivityMainBinding
import com.argotedev.triviaapp.ui.startGame.StartGameFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val fragment = StartGameFragment()

        supportFragmentManager.commit {
            add(R.id.containerFragment, fragment)
        }

    }
}