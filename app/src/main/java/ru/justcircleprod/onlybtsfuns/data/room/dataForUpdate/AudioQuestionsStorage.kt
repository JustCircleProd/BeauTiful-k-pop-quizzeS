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

    fun getQuestionsFor34Migration(): List<AudioQuestion> {
        return listOf(
            AudioQuestion(
                38,
                "danger",
                "Danger", "IDOL",
                "Ugh!", "FIRE",
                1, 350,
            ),
            AudioQuestion(
                39,
                "hold_me_tight",
                "Serendipity", "Hold Me Tight",
                "We are Bulletproof : the Eternal", "Trust Me",
                2, 550,
            ),
            AudioQuestion(
                40,
                "house_of_cards",
                "Boyz With Fun", "Silver Spoon",
                "House Of Cards", "PERSONA",
                3, 500,
            ),
            AudioQuestion(
                41,
                "jamais_vu",
                "Dimple", "Hold Me Tight",
                "Spring Day", "Jamais Vu",
                4, 550,
            ),
            AudioQuestion(
                42,
                "lie",
                "Lie", "On",
                "RUN", "PERSONA",
                1, 350,
            ),
            AudioQuestion(
                43,
                "louder_than_bombs",
                "Singularity", "Louder Than Bombs",
                "My Universe", "Dis-ease",
                2, 400,
            ),
            AudioQuestion(
                44,
                "mikrokosmos",
                "My Universe", "Whalien 52",
                "Mikrokosmos", "Jamais Vu",
                3, 500,
            ),
            AudioQuestion(
                45,
                "my_time",
                "Mikrokosmos", "Pied Piper",
                "Serendipity", "My Time",
                4, 500,
            ),
            AudioQuestion(
                46,
                "no_more_dream",
                "No More Dream", "MIC Drop",
                "Daechwita", "House Of Cards",
                1, 500,
            ),
            AudioQuestion(
                47,
                "outro_wings",
                "DNA", "Outro : Wings",
                "Boyz With Fun", "Sea",
                2, 400,
            ),
            AudioQuestion(
                48,
                "sea",
                "Hold Me Tight", "Butter",
                "Sea", "Paradise",
                3, 450,
            ),
            AudioQuestion(
                49,
                "so_what",
                "Louder Than Bombs", "RUN",
                "Dynamite", "So What",
                4, 300,
            ),
            AudioQuestion(
                50,
                "trivia_seesaw",
                "Trivia 轉 : Seesaw", "Permission to Dance",
                "Just One Day", "Boy With Luv",
                1, 500,
            ),
            AudioQuestion(
                51,
                "whalien_52",
                "House Of Cards", "Whalien 52",
                "No More Dream", "Trivia 轉 : Seesaw",
                2, 550,
            ),
            AudioQuestion(
                52,
                "yet_to_come",
                "So What", "Outro : Wings",
                "Yet To Come", "My Universe",
                3, 300,
            )
        )
    }

    fun getQuestionsFor45Migration(): List<AudioQuestion> {
        return listOf(
            AudioQuestion(
                53,
                "pluto_134340",
                "PERSONA", "House Of Cards",
                "Dionysus", "134340 (Pluto)",
                4, 550,
            ),
            AudioQuestion(
                54,
                "airplane_pt_2",
                "Airplane Pt. 2", "Just One Day",
                "Sea", "Life Goes On",
                1, 450,
            ),
            AudioQuestion(
                55,
                "dionysus",
                "Serendipity", "Dionysus",
                "Dis-ease", "My Time",
                2, 550,
            ),
            AudioQuestion(
                56,
                "dream_glow",
                "Yet To Come", "Butter",
                "Dream Glow", "Silver Spoon",
                3, 300,
            ),
            AudioQuestion(
                57,
                "epiphany",
                "Telepathy", "Spring Day",
                "Mikrokosmos", "Epiphany",
                4, 500,
            ),
            AudioQuestion(
                58,
                "go_go",
                "Go Go", "FIRE",
                "Lie", "Love Maze",
                1, 350,
            ),
            AudioQuestion(
                59,
                "love_maze",
                "Louder Than Bombs", "Love Maze",
                "Paradise", "Boy With Luv",
                2, 400,
            ),
            AudioQuestion(
                60,
                "more",
                "DDAENG", "MIC Drop",
                "MORE", "Ugh!",
                3, 450,
            ),
            AudioQuestion(
                61,
                "telepathy",
                "Epiphany", "Hold Me Tight",
                "Dope", "Telepathy",
                4, 500,
            ),
            AudioQuestion(
                62,
                "tomorrow",
                "Tomorrow", "Whalien 52",
                "Outro : Wings", "Sea",
                1, 400,
            )
        )
    }

    fun getQuestionsFor56Migration(): List<AudioQuestion> {
        return listOf(
            AudioQuestion(
                63,
                "anpanman",
                "DNA", "Anpanman",
                "Hold Me Tight", "Converse High",
                2, 400,
            ),
            AudioQuestion(
                64,
                "attack_on_bangtan",
                "MORE", "Dionysus",
                "Attack on Bangtan", "Danger",
                3, 450,
            ),
            AudioQuestion(
                65,
                "converse_high",
                "Go Go", "Silver Spoon",
                "FIRE", "Converse High",
                4, 300,
            ),
            AudioQuestion(
                66,
                "dead_leaves",
                "Dead Leaves", "Dimple",
                "Rain", "Epiphany",
                1, 500,
            ),
            AudioQuestion(
                67,
                "friends",
                "Jamais Vu", "Friends",
                "My Time", "Stay",
                2, 550,
            ),
            AudioQuestion(
                68,
                "like",
                "Dope", "Attack on Bangtan",
                "Like", "PERSONA",
                3, 600,
            ),
            AudioQuestion(
                69,
                "rain",
                "Sea", "Dead Leaves",
                "Love Maze", "Rain",
                4, 600,
            ),
            AudioQuestion(
                70,
                "stay",
                "Stay", "Lie",
                "I NEED U", "FAKE LOVE",
                1, 350,
            ),
            AudioQuestion(
                71,
                "trivia_just_dance",
                "Just One Day", "Trivia 起 : Just Dance",
                "Boyz With Fun", "Stay",
                2, 300,
            ),
            AudioQuestion(
                72,
                "zero_o_clock",
                "Trivia 起 : Just Dance", "Go Go",
                "00:00 (Zero O’Clock)", "Singularity",
                3, 350,
            )
        )
    }
}