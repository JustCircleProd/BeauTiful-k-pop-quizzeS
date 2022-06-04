package ru.justcircleprod.onlybtsfuns.data.room.dataForUpdate

import ru.justcircleprod.onlybtsfuns.data.models.VideoQuestion

object VideoQuestionsStorage {
    fun getQuestions(): List<VideoQuestion> {
        return listOf(
            VideoQuestion(
                1,
                "black_swan_video",
                "Butter", "Boy With Luv",
                "Black Swan", "FAKE LOVE",
                3, 550
            ),
            VideoQuestion(
                2,
                "blood_sweat_tears_video",
                "DNA", "Euphoria",
                "MIC Drop", "Blood Sweat & Tears",
                4, 600
            ),
            VideoQuestion(
                3,
                "boy_with_luv_video",
                "Boy With Luv", "Spring Day",
                "Love Maze", "The Truth Untold",
                1, 550
            ),
            VideoQuestion(
                4,
                "butter_video",
                "Permission to Dance", "Butter",
                "IDOL", "Dynamite",
                2, 500
            ),
            VideoQuestion(
                5,
                "dna_video",
                "IDOL", "Black Swan",
                "DNA", "MIC Drop",
                3, 550
            ),
            VideoQuestion(
                6,
                "dynamite_video",
                "Not Today", "Spring Day",
                "Permission to Dance", "Dynamite",
                4, 550
            ),
            VideoQuestion(
                7,
                "euphoria_video",
                "Euphoria", "IDOL",
                "Save ME", "DNA",
                1, 600
            ),
            VideoQuestion(
                8,
                "fake_love_video",
                "Boy With Luv", "FAKE LOVE",
                "The Truth Untold", "Love Maze",
                2, 600
            ),
            VideoQuestion(
                9,
                "idol_video",
                "DNA", "Butter",
                "IDOL", "Pied Piper",
                3, 550
            ),
            VideoQuestion(
                10,
                "mic_drop_video",
                "Waste It On Me", "IDOL",
                "Magic Shop", "MIC Drop",
                4, 500
            ),
            VideoQuestion(
                11,
                "permission_to_dance_video",
                "Permission to Dance", "Euphoria",
                "Crystal Snow", "Butter",
                1, 550
            ),
        )
    }

    fun getQuestionsFor23Migration(): List<VideoQuestion> {
        return listOf(
            VideoQuestion(
                13,
                "i_need_u_video",
                "PERSONA", "I Need U",
                "IDOL", "Pied Piper",
                2, 500
            ),
            VideoQuestion(
                14,
                "life_goes_on_video",
                "DNA", "Black Swan",
                "Life Goes On", "IDOL",
                3, 600
            ),
            VideoQuestion(
                15,
                "my_universe_video",
                "We are Bulletproof : the Eternal", "FIRE",
                "Permission to Dance", "My Universe",
                4, 500
            ),
            VideoQuestion(
                16,
                "on_video",
                "On", "Blood Sweat & Tears",
                "Butter", "Euphoria",
                1, 550
            ),
            VideoQuestion(
                17,
                "persona_video",
                "My Universe", "PERSONA",
                "Dynamite", "Boy With Luv",
                2, 500
            ),
            VideoQuestion(
                18,
                "run_video",
                "Life Goes On", "I Need U",
                "Run", "Butter",
                3, 500
            ),
            VideoQuestion(
                19,
                "we_are_bulletproof_the_eternal_video",
                "DNA", "IDOL",
                "Daechwita", "We are Bulletproof : the Eternal",
                4, 550
            )
        )
    }
}