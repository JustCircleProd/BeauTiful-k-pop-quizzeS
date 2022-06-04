package ru.justcircleprod.onlybtsfuns.data.room.dataForUpdate

import ru.justcircleprod.onlybtsfuns.data.models.oldModels.ImageQuestion

object ImageQuestionsStorage {
    fun getQuestions(): List<ImageQuestion> {
        return listOf(
            ImageQuestion(
                156,
                "chimmy_1",
                "Cooky", "Mang",
                "Tata", "Chimmy",
                4, 600
            ), ImageQuestion(
                157,
                "chimmy_2",
                "Chimmy", "Van",
                "Shooky", "Koya",
                1, 600
            ), ImageQuestion(
                158,
                "cooky_1",
                "Koya", "Cooky",
                "Van", "Mang",
                2, 600
            ), ImageQuestion(
                159,
                "cooky_2",
                "Shooky", "Rj",
                "Cooky", "Chimmy",
                3, 600
            ), ImageQuestion(
                160,
                "koya_1",
                "Rj", "Cooky",
                "Mang", "Koya",
                4, 600
            ), ImageQuestion(
                161,
                "koya_2",
                "Koya", "Chimmy",
                "Tata", "Cooky",
                1, 600
            ), ImageQuestion(
                162,
                "mang_1",
                "Shooky", "Mang",
                "Chimmy", "Koya",
                2, 600
            ), ImageQuestion(
                163,
                "mang_2",
                "Chimmy", "Tata",
                "Mang", "Rj",
                3, 600
            ), ImageQuestion(
                164,
                "rj_1",
                "Shooky", "Van",
                "Tata", "Rj",
                4, 600
            ), ImageQuestion(
                165,
                "rj_2",
                "Rj", "Chimmy",
                "Van", "Koya",
                1, 600
            ), ImageQuestion(
                166,
                "shooky_1",
                "Cooky", "Shooky",
                "Chimmy", "Tata",
                2, 600
            ), ImageQuestion(
                167,
                "shooky_2",
                "Cooky", "Mang",
                "Shooky", "Rj",
                3, 600
            ), ImageQuestion(
                168,
                "tata_1",
                "Mang", "Cooky",
                "Chimmy", "Tata",
                4, 600
            ), ImageQuestion(
                169,
                "tata_2",
                "Tata", "Cooky",
                "Rj", "Koya",
                1, 600
            ), ImageQuestion(
                170,
                "van_1",
                "Shooky", "Van",
                "Mang", "Chimmy",
                2, 600
            ), ImageQuestion(
                171,
                "van_2",
                "Tata", "Cooky",
                "Van", "Chimmy",
                3, 600
            )
        )
    }
}