package ru.justcircleprod.onlybtsfuns.dataForUpdate

import ru.justcircleprod.onlybtsfuns.data.VideoQuestion

object VideoQuestionsStorage {
    fun getQuestions(): List<VideoQuestion> {
        return listOf(
            VideoQuestion(
                1,
                "test",
                "1", "2",
                "3", "4",
                1, 400
            ),
            VideoQuestion(
                2,
                "test",
                "1", "2",
                "3", "4",
                1, 400
            )
        )
    }

}