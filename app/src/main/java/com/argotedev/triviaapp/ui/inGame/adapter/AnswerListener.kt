package com.argotedev.triviaapp.ui.inGame.adapter

import com.argotedev.triviaapp.model.Answer

interface AnswerListener {
    fun clickItem(answer: Answer)
}