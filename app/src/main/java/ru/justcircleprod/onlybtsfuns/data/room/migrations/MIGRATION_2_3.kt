package ru.justcircleprod.onlybtsfuns.data.room.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.justcircleprod.onlybtsfuns.data.room.dataForUpdate.AudioQuestionsStorage
import ru.justcircleprod.onlybtsfuns.data.room.dataForUpdate.ImageQuestionsStorage
import ru.justcircleprod.onlybtsfuns.data.room.dataForUpdate.TextQuestionsEditedQuestions
import ru.justcircleprod.onlybtsfuns.data.room.dataForUpdate.VideoQuestionsStorage

val MIGRATION_2_3: Migration = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        for (question in ImageQuestionsStorage.getQuestionsFor23Migration()) {
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

        for (question in VideoQuestionsStorage.getQuestionsFor23Migration()) {
            database.execSQL(
                """INSERT OR IGNORE INTO video_questions
                | (id, video_entry_name, first_option, second_option, 
                | third_option, fourth_option, answer_num, points) 
                | VALUES (${question.id}, "${question.video_entry_name}",
                | "${question.firstOption}", "${question.secondOption}",
                | "${question.thirdOption}", "${question.fourthOption}",
                | ${question.answerNum}, ${question.points})""".trimMargin()
            )
        }

        for (question in AudioQuestionsStorage.getQuestionsFor23Migration()) {
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

        for ((id, question) in TextQuestionsEditedQuestions.getQuestionForEdit23Migration()) {
            database.execSQL(
                "UPDATE OR IGNORE text_questions SET question = \'$question\' WHERE id = $id"
            )
        }

        with(database) {
            execSQL("CREATE TABLE settings_backup (id INTEGER NOT NULL, state INTEGER NOT NULL, PRIMARY KEY (id))")
            execSQL("INSERT INTO settings_backup SELECT id, state FROM settings")
            execSQL("DROP TABLE settings")
            execSQL("ALTER TABLE settings_backup RENAME to settings")

            execSQL("CREATE TABLE scores_backup (id INTEGER NOT NULL, score INTEGER NOT NULL, PRIMARY KEY (id))")
            execSQL("INSERT INTO scores_backup SELECT id, score FROM scores")
            execSQL("DROP TABLE scores")
            execSQL("ALTER TABLE scores_backup RENAME to scores")

            execSQL(
                "INSERT OR IGNORE INTO settings (id, state) VALUES (2, 0)"
            )

            execSQL(
                "CREATE TABLE IF NOT EXISTS passed_questions (" +
                        "id	INTEGER NOT NULL," +
                        "question_id	INTEGER NOT NULL," +
                        "question_content_type	TEXT NOT NULL," +
                        "PRIMARY KEY(id))"
            )
        }
    }
}