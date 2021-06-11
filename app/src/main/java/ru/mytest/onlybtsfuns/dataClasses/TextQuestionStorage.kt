package ru.mytest.onlybtsfuns.dataClasses

object TextQuestionStorage {
    fun getQuestions(count: Int): Array<TextQuestion> {
        return listOf(
            TextQuestion(
                "Когда был сфомирован BTS?",
                arrayOf("2010", "2012", "2013", "2014"),
                3
            ),
            TextQuestion(
                "Как расшифровывается BTS?",
                arrayOf(
                    "Bольшой Tолковый Sловарь",
                    "Beyond The Scene",
                    "Behind The Scenes",
                    "Base Theater Showtime"
                ),
                2
            ),
            TextQuestion(
                "Какой альбом позволил выиграть группе номинацию \"Артист года\"?",
                arrayOf(
                    "Wings",
                    "Love Yourself: Her",
                    "2 Cool 4 Skool",
                    "Wings: You Never Walk Alone"
                ),
                1
            ),
            TextQuestion(
                "Когда был сфомирован BTS?",
                arrayOf("2010", "2012", "2013", "2014"),
                3
            ),
            TextQuestion(
                "Как расшифровывается BTS?",
                arrayOf(
                    "Bольшой Tолковый Sловарь",
                    "Beyond The Scene",
                    "Behind The Scenes",
                    "Base Theater Showtime"
                ),
                2
            ),
            TextQuestion(
                "Какой альбом позволил выиграть группе номинацию \"Артист года\"?",
                arrayOf(
                    "Wings",
                    "Love Yourself: Her",
                    "2 Cool 4 Skool",
                    "Wings: You Never Walk Alone"
                ),
                1
            ),
            TextQuestion(
                "Когда был сфомирован BTS?",
                arrayOf("2010", "2012", "2013", "2014"),
                3
            ),
            TextQuestion(
                "Как расшифровывается BTS?",
                arrayOf(
                    "Bольшой Tолковый Sловарь",
                    "Beyond The Scene",
                    "Behind The Scenes",
                    "Base Theater Showtime"
                ),
                2
            ),
            TextQuestion(
                "Какой альбом позволил выиграть группе номинацию \"Артист года\"?",
                arrayOf(
                    "Wings",
                    "Love Yourself: Her",
                    "2 Cool 4 Skool",
                    "Wings: You Never Walk Alone"
                ),
                1
            ),
            TextQuestion(
                "Когда был сфомирован BTS?",
                arrayOf("2010", "2012", "2013", "2014"),
                3
            ),
            TextQuestion(
                "Как расшифровывается BTS?",
                arrayOf(
                    "Bольшой Tолковый Sловарь",
                    "Beyond The Scene",
                    "Behind The Scenes",
                    "Base Theater Showtime"
                ),
                2
            ),
            TextQuestion(
                "Какой альбом позволил выиграть группе номинацию \"Артист года\"?",
                arrayOf(
                    "Wings",
                    "Love Yourself: Her",
                    "2 Cool 4 Skool",
                    "Wings: You Never Walk Alone"
                ),
                1
            ),
        ).shuffled().take(count).toTypedArray()
    }
}