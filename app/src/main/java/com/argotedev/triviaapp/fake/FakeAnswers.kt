package com.argotedev.triviaapp.fake

import com.argotedev.triviaapp.model.Answer
import com.argotedev.triviaapp.model.Question

object FakeAnswers {
    val shuffledQuestions: List<Question> = listOf(
        Question(
            title = "¿Cuál es la capital de Italia?",
            score = 10,
            answers = listOf(
                Answer(title = "Roma", true),
                Answer(title = "Madrid", false),
                Answer(title = "París", false),
            )
        ),
        Question(
            title = "¿Cuál es el río más largo del mundo?",
            score = 10,
            answers = listOf(
                Answer(title = "Amazonas", false),
                Answer(title = "Nilo", true),
                Answer(title = "Misisipi", false),
            )
        ),
        Question(
            title = "¿Cuál es el color primario que se forma al mezclar azul y amarillo?",
            score = 10,
            answers = listOf(
                Answer(title = "Rojo", false),
                Answer(title = "Verde", true),
                Answer(title = "Morado", false),
            )
        ),
        Question(
            title = "¿Quién pintó la Mona Lisa?",
            score = 10,
            answers = listOf(
                Answer(title = "Vincent van Gogh", false),
                Answer(title = "Pablo Picasso", false),
                Answer(title = "Leonardo da Vinci", true),
            )
        ),
        Question(
            title = "¿Cuál es la capital de Australia?",
            score = 10,
            answers = listOf(
                Answer(title = "Sydney", true),
                Answer(title = "Melbourne", false),
                Answer(title = "Canberra", false),
            )
        ),
        Question(
            title = "¿Cuál es el planeta más grande del sistema solar?",
            score = 10,
            answers = listOf(
                Answer(title = "Marte", false),
                Answer(title = "Júpiter", true),
                Answer(title = "Mercurio", false),
            )
        ),
        Question(
            title = "¿Cuál es la montaña más alta del mundo?",
            score = 10,
            answers = listOf(
                Answer(title = "Everest", true),
                Answer(title = "Kilimanjaro", false),
                Answer(title = "Mont Blanc", false),
            )
        ),
        Question(
            title = "¿En qué país se encuentra la Gran Muralla China?",
            score = 10,
            answers = listOf(
                Answer(title = "China", true),
                Answer(title = "Japon", false),
                Answer(title = "India", false),
            )
        ),
        Question(
            title = "¿Cuál es el océano más grande?",
            score = 10,
            answers = listOf(
                Answer(title = "Océano Atlántico", false),
                Answer(title = "Océano Índico", false),
                Answer(title = "Océano Pacífico", true),
            )
        ),
        Question(
            title = "¿Cuál es el símbolo químico del oro?",
            score = 10,
            answers = listOf(
                Answer(title = "Ag", true),
                Answer(title = "Au", false),
                Answer(title = "Fe", false),
            )
        ),
    ).shuffled()
}