package ru.mytest.onlybtsfuns.dataClasses

object TextQuestionStorage {
    fun getTextQuestions(count: Int): Array<TextQuestion> {
        return listOf(
            TextQuestion(
                "Test2",
                arrayOf("Правильный ответ", "2", "3", "4"),
                1
            ),
            TextQuestion(
                "Test1",
                arrayOf("1", "2", "3", "Правильный ответ"),
                4
            ),
            TextQuestion(
                "Test2",
                arrayOf("Правильный ответ", "2", "3", "4"),
                1
            ),
            TextQuestion(
                "Test1",
                arrayOf("1", "2", "3", "Правильный ответ"),
                4
            ),
            TextQuestion(
                "Test2",
                arrayOf("Правильный ответ", "2", "3", "4"),
                1
            ),
            TextQuestion(
                "Test1",
                arrayOf("1", "2", "3", "Правильный ответ"),
                4
            ),
            TextQuestion(
                "Test2",
                arrayOf("Правильный ответ", "2", "3", "4"),
                1
            ),
            TextQuestion(
                "Test1",
                arrayOf("1", "2", "3", "Правильный ответ"),
                4
            ),
            TextQuestion(
                "Test2",
                arrayOf("Правильный ответ", "2", "3", "4"),
                1
            ),
            TextQuestion(
                "Test1",
                arrayOf("1", "2", "3", "Правильный ответ"),
                4
            ),
        ).shuffled().take(count).toTypedArray()
    }
}