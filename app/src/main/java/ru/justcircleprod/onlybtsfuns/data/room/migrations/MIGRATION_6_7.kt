package ru.justcircleprod.onlybtsfuns.data.room.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.justcircleprod.onlybtsfuns.data.room.dataForUpdate.AudioQuestionsStorage
import ru.justcircleprod.onlybtsfuns.data.room.dataForUpdate.ImageQuestionsStorage
import ru.justcircleprod.onlybtsfuns.data.room.dataForUpdate.TextQuestionsStorage

val MIGRATION_6_7: Migration = object : Migration(6, 7) {
    override fun migrate(database: SupportSQLiteDatabase) {
        for (question in TextQuestionsStorage.getQuestionsFor67Migration()) {
            database.execSQL(
                """INSERT OR IGNORE INTO text_questions
                | (id, question, first_option, second_option, 
                | third_option, fourth_option, answer_num, points) 
                | VALUES (${question.id}, '${question.question}',
                | "${question.firstOption}", "${question.secondOption}",
                | "${question.thirdOption}", "${question.fourthOption}",
                | ${question.answerNum}, ${question.points})""".trimMargin()
            )
        }

        for (question in ImageQuestionsStorage.getQuestionsFor67Migration()) {
            database.execSQL(
                """INSERT OR IGNORE INTO image_questions
                | (id, image_entry_name, first_option, second_option, 
                | third_option, fourth_option, answer_num, points) 
                | VALUES (${question.id}, "${question.image_entry_name}",
                | "${question.firstOption}", "${question.secondOption}",
                | "${question.thirdOption}", "${question.fourthOption}",
                | ${question.answerNum}, ${question.points})""".trimMargin()
            )
        }

        for (question in AudioQuestionsStorage.getQuestionsFor67Migration()) {
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

        for ((id, points) in AudioQuestionsStorage.getUpdatedPointsFor67Migration()) {
            database.execSQL(
                "UPDATE OR IGNORE audio_questions SET points = $points WHERE id = $id"
            )
        }
    }
}