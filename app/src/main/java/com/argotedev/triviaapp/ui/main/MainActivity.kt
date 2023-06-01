package com.argotedev.triviaapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.argotedev.triviaapp.R
import com.argotedev.triviaapp.databinding.ActivityMainBinding
import com.argotedev.triviaapp.ui.startGame.StartGameRouter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var starGameRouter: StartGameRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        starGameRouter = StartGameRouter()
    }

    override fun onStart() {
        super.onStart()
        starGameRouter.replace(supportFragmentManager, R.id.containerFragment)
    }

}