package ru.justcircleprod.onlybtsfuns.dataForUpdate

import ru.justcircleprod.onlybtsfuns.data.AudioQuestion

object AudioQuestionsStorage {
    fun getQuestions(): List<AudioQuestion> {
        return listOf(
            AudioQuestion(
                1,
                "bad_result",
                "1", "2",
                "3", "4",
                1, 400
            ),
            AudioQuestion(
                2,
                "best_result",
                "1", "2",
                "3", "4",
                1, 400
            )
        )
    }
}