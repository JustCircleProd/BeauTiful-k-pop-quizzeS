package ru.justcircleprod.onlybtsfuns.data.dataForUpdate

import ru.justcircleprod.onlybtsfuns.data.models.oldModels.AudioQuestion

object AudioQuestionsStorage {
    fun getQuestions(): List<AudioQuestion> {
        return listOf(
            AudioQuestion(
                1,
                "black_swan",
                "Black Swan", "FAKE LOVE",
                "Come Back Home", "DNA",
                1, 400
            ),
            AudioQuestion(
                2,
                "blood_sweat_tears",
                "Euphoria", "Blood Sweat & Tears",
                "Dynamite", "Waste It On Me",
                2, 550
            ),
            AudioQuestion(
                3,
                "boy_with_luv",
                "Save ME", "Black Swan",
                "Boy With Luv", "FAKE LOVE",
                3, 400
            ),
            AudioQuestion(
                4,
                "butter",
                "Dynamite", "MIC Drop",
                "Spring Day", "Butter",
                4, 350
            ),
            AudioQuestion(
                5,
                "dna",
                "DNA", "Crystal Show",
                "Permission to Dance", "Magic Shop",
                1, 400
            ),
            AudioQuestion(
                6,
                "dynamite",
                "Butter", "Dynamite",
                "Pied Piper", "The Truth Untold",
                2, 350
            ),
            AudioQuestion(
                7,
                "euphoria",
                "IDOL", "Not Today",
                "Euphoria", "DNA",
                3, 500
            ),
            AudioQuestion(
                8,
                "fake_love",
                "BEGIN", "Dynamite",
                "Crystal Snow", "FAKE LOVE",
                4, 400
            ),
            AudioQuestion(
                9,
                "idol",
                "IDOL", "DNA",
                "MIC Drop", "Save ME",
                1, 400
            ),
            AudioQuestion(
                10,
                "mic_drop",
                "Blood Sweat & Tears", "MIC Drop",
                "IDOL", "Not Today",
                2, 500
            ),
            AudioQuestion(
                11,
                "not_today",
                "On", "Boy With Luv",
                "Not Today", "Butter",
                3, 350
            ),
            AudioQuestion(
                12,
                "permission_to_dance",
                "Pied Piper", "Life Goes On",
                "DNA", "Permission to Dance",
                4, 500
            ),
            AudioQuestion(
                13,
                "pied_piper",
                "Pied Piper", "Euphoria",
                "Spring Day", "Dynamite",
                1, 500
            ),
            AudioQuestion(
                14,
                "save_me",
                "Black Swan", "Save ME",
                "FAKE LOVE", "IDOL",
                2, 350
            ),
            AudioQuestion(
                15,
                "spring_day",
                "Euphoria", "Black Swan",
                "Spring Day", "Pied Piper",
                3, 550
            )
        )
    }
}