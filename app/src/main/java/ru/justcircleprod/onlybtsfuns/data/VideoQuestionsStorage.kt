package ru.justcircleprod.onlybtsfuns.data

object VideoQuestionsStorage {
    fun getQuestions(): List<VideoQuestion> {
        return listOf(
            VideoQuestion(
                1,
                "test",
                "1", "2",
                "3", "4",
                1, 200
            )
        )
    }

}