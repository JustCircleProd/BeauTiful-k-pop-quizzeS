package ru.mytest.onlybtsfuns.dataClasses

object TextQuestionStorage {
    fun getTextQuestions(count: Int): Array<TextQuestion> {
        return listOf(
            TextQuestion(
                "dsdsds",
                arrayOf("dsds", "dsds", "dsdsds", "dsfdsf"),
                1
            ),
            TextQuestion(
                "dsdsds",
                arrayOf("dsds", "dsds", "dsdfsfds", "dsdsfsdf"),
                1
            ),
        ).shuffled().take(count).toTypedArray()
    }
}