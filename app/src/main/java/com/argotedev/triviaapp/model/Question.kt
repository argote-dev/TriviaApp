package com.argotedev.triviaapp.model

data class Question(
    val title: String,
    val score: Int,
    val answers: List<Answer>
) {
    fun shuffleAnswers() = answers.shuffled()
}
