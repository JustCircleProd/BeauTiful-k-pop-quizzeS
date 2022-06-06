package ru.justcircleprod.onlybtsfuns.data.room.dataForUpdate

import ru.justcircleprod.onlybtsfuns.data.models.AudioQuestion

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

    fun getQuestionsFor23Migration(): List<AudioQuestion> {
        return listOf(
            AudioQuestion(
                16,
                "fire",
                "FIRE", "Black Swan",
                "Daechwita", "Dynamite",
                1, 350
            ),
            AudioQuestion(
                17,
                "i_need_u",
                "PERSONA", "Boy With Luv",
                "Save ME", "I NEED U",
                4, 350
            ),
            AudioQuestion(
                18,
                "life_goes_on",
                "Pied Piper", "Life Goes On",
                "Spring Day", "Butter",
                2, 500
            ),
            AudioQuestion(
                19,
                "my_universe",
                "On", "FIRE",
                "My Universe", "Not Today",
                3, 300
            ),
            AudioQuestion(
                20,
                "on",
                "FIRE", "On",
                "MIC Drop", "DNA",
                2, 500
            ),
            AudioQuestion(
                21,
                "persona",
                "Daechwita", "IDOL",
                "Blood Sweat & Tears", "PERSONA",
                4, 550
            ),
            AudioQuestion(
                22,
                "run",
                "Life Goes On", "RUN",
                "Dynamite", "Permission to Dance",
                2, 400
            ),
            AudioQuestion(
                23,
                "we_are_bulletproof_the_eternal",
                "PERSONA", "Euphoria",
                "We are Bulletproof : the Eternal", "Pied Piper",
                3, 500
            ),
            AudioQuestion(
                24,
                "daechwita",
                "Daechwita", "Agust D",
                "Give It To Me", "The Last",
                1, 500
            ),
            AudioQuestion(
                25,
                "boyz_with_fun",
                "Spring Day", "I NEED U",
                "Just One Day", "Boyz With Fun",
                4, 450
            ),
            AudioQuestion(
                26,
                "ddaeng",
                "Dynamite", "DDAENG",
                "FIRE", "IDOL",
                2, 350
            ),
            AudioQuestion(
                27,
                "dimple",
                "Boyz With Fun", "The Truth Untold",
                "Dimple", "Life Goes On",
                3, 550
            ),
            AudioQuestion(
                28,
                "dis_ease",
                "Dis-ease", "Pied Piper",
                "Dimple", "Euphoria",
                1, 500
            ),
            AudioQuestion(
                29,
                "dope",
                "Silver Spoon", "Ugh!",
                "PERSONA", "Dope",
                4, 450
            ),
            AudioQuestion(
                30,
                "just_one_day",
                "Boyz With Fun", "Just One Day",
                "Butter", "Dis-ease",
                2, 550
            ),
            AudioQuestion(
                31,
                "paradise",
                "Boy With Luv", "Dimple",
                "Paradise", "Just One Day",
                3, 500
            ),
            AudioQuestion(
                32,
                "silver_spoon",
                "Silver Spoon", "Daechwita",
                "IDOL", "DDAENG",
                1, 350
            ),
            AudioQuestion(
                33,
                "silver_spoon_2",
                "Black Swan", "MIC Drop",
                "My Universe", "Silver Spoon",
                4, 400
            ),
            AudioQuestion(
                34,
                "ugh",
                "Blood Sweat & Tears", "Silver Spoon",
                "Ugh!", "PERSONA",
                3, 500
            ),
            AudioQuestion(
                35,
                "serendipity",
                "Spring Day", "Serendipity",
                "Singularity", "Dimple",
                2, 550
            ),
            AudioQuestion(
                36,
                "epilogue_young_forever",
                "Epilogue: Young Forever", "Dis-ease",
                "Paradise", "My Universe",
                1, 350
            ),
            AudioQuestion(
                37,
                "singularity",
                "Serendipity", "Just One Day",
                "Euphoria", "Singularity",
                4, 550
            )
        )
    }
}