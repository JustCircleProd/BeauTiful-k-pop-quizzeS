package com.justcircleprod.beautifulkpopquizzes.data.room

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.justcircleprod.beautifulkpopquizzes.data.models.AudioQuestion

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        for (question in audioQuestionsForMigration12) {
            database.execSQL(
                """INSERT OR IGNORE INTO audio_questions
                | (id, audio_entry_name, first_option, second_option, 
                | third_option, fourth_option, answer_num, points) 
                | VALUES (${question.id}, "${question.audio_entry_name}",
                | "${question.firstOption}", "${question.secondOption}",
                | "${question.thirdOption}", "${question.fourthOption}",
                | ${question.answerNum}, ${question.points})""".trimMargin()
            )
        }
    }

    val audioQuestionsForMigration12 = listOf(
        AudioQuestion(
            85,
            "rm_still_life",
            "Boyz With Fun", "Tomorrow",
            "Stay Gold", "RM ''Still Life''",
            4, 300,
        ),
        AudioQuestion(
            86,
            "rm_wild_flower",
            "RM ''들꽃놀이 (with 조유진)''", "Hold Me Tight",
            "We are Bulletproof: the Eternal", "Your Eyes Tell",
            1, 550,
        ),
        AudioQuestion(
            87,
            "jin_the_astronaut",
            "Mikrokosmos", "Jin ''The Astronaut''",
            "Spring Day", "RM ''들꽃놀이 (with 조유진)''",
            2, 600,
        ),
        AudioQuestion(
            88,
            "coffee",
            "Friends", "Just One Day",
            "Coffee", "My Time",
            3, 550,
        ),
        AudioQuestion(
            89,
            "taeyang_vibe_feat_jimin",
            "Boy With Luv", "Trivia: Seesaw",
            "Dionysus", "TAEYANG - ''VIBE (feat. Jimin of BTS)''",
            4, 550,
        ),
        AudioQuestion(
            90,
            "am_i_wrong",
            "Am I Wrong", "Dream Glow",
            "Stay Gold", "Permission to Dance",
            1, 350,
        ),
        AudioQuestion(
            91,
            "dreamers_jungkook_fahad",
            "Telepathy", "''Dreamers'' Jung Kook x Fahad Al Kubaisi",
            "134340 (Pluto)", "TAEYANG - ''VIBE (feat. Jimin of BTS)''",
            2, 550,
        ),
        AudioQuestion(
            92,
            "filter",
            "Trivia: Love", "TAEYANG - ''VIBE (feat. Jimin of BTS)'",
            "Jimin ''Filter''", "Airplane Pt.2",
            3, 450,
        ),
        AudioQuestion(
            93,
            "mama",
            "Sea", "Jimin ''Filter''",
            "Fly to My Room", "Mama",
            4, 450,
        ),
        AudioQuestion(
            94,
            "interlude_shadow",
            "Interlude : Shadow", "Whalien 52",
            "Pied Piper", "Arson",
            1, 600,
        ),
    )
}