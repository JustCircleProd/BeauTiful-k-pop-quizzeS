package ru.justcircleprod.onlybtsfuns.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.justcircleprod.onlybtsfuns.data.models.*
import ru.justcircleprod.onlybtsfuns.data.room.dao.*
import ru.justcircleprod.onlybtsfuns.data.room.dataForUpdate.*

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        updateQuestions(database)
        addVideoQuestions(database)
        addAudioQuestions(database)
        addScores(database)
        addSettings(database)
    }


    private fun updateQuestions(database: SupportSQLiteDatabase) {
        for ((id, points) in TextQuestionUpdatedPoints.getQuestions()) {
            database.execSQL(
                "UPDATE OR IGNORE text_questions SET points = $points WHERE id = $id"
            )
        }

        for ((id, points) in ImageQuestionUpdatedPoints.getQuestions()) {
            database.execSQL(
                "UPDATE OR IGNORE image_questions SET points = $points WHERE id = $id"
            )
        }

        database.execSQL(
            "UPDATE OR IGNORE image_questions SET answer_num = 1, image_entry_name = \"jeon_jung_kook_22\" WHERE id = 102"
        )

        for (question in ImageQuestionsStorage.getQuestions()) {
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
    }

    private fun addVideoQuestions(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS video_questions (" +
                    "id	INTEGER NOT NULL," +
                    "video_entry_name	TEXT NOT NULL," +
                    "first_option	TEXT NOT NULL," +
                    "second_option	TEXT NOT NULL," +
                    "third_option	TEXT NOT NULL," +
                    "fourth_option	TEXT NOT NULL," +
                    "answer_num	INTEGER NOT NULL, " +
                    "points INTEGER NOT NULL DEFAULT 0," +
                    "PRIMARY KEY (id))"
        )

        for (question in VideoQuestionsStorage.getQuestions()) {
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
    }

    private fun addAudioQuestions(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS audio_questions (" +
                    " id INTEGER NOT NULL," +
                    "audio_entry_name	TEXT NOT NULL," +
                    "first_option	TEXT NOT NULL," +
                    "second_option	TEXT NOT NULL," +
                    "third_option	TEXT NOT NULL," +
                    "fourth_option	TEXT NOT NULL," +
                    "answer_num	INTEGER NOT NULL, " +
                    "points INTEGER NOT NULL DEFAULT 0," +
                    "PRIMARY KEY(id))"
        )

        for (question in AudioQuestionsStorage.getQuestions()) {
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

    private fun addScores(database: SupportSQLiteDatabase) {
        database.execSQL(
            "INSERT OR IGNORE INTO scores (id, category, score) VALUES (4, \"video_questions\", 0)"
        )

        database.execSQL(
            "INSERT OR IGNORE INTO scores (id, category, score) VALUES (5, \"audio_questions\", 0)"
        )
    }

    private fun addSettings(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS settings (" +
                    "id	INTEGER NOT NULL," +
                    "name	TEXT NOT NULL," +
                    "state	INTEGER NOT NULL," +
                    "PRIMARY KEY(id))"
        )

        database.execSQL(
            "INSERT OR IGNORE INTO settings (id, name, state) VALUES (1, \"difficulty\", 0)"
        )
    }
}

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

@Database(
    version = 3,
    entities = [TextQuestion::class, ImageQuestion::class, AudioQuestion::class,
        VideoQuestion::class, PassedQuestion::class, Score::class, Setting::class]
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun textQuestionDao(): TextQuestionDao
    abstract fun imageQuestionDao(): ImageQuestionDao
    abstract fun audioQuestionDao(): AudioQuestionDao
    abstract fun videoQuestionDao(): VideoQuestionDao
    abstract fun passedQuestionDao(): PassedQuestionDao
    abstract fun scoreDao(): ScoreDao
    abstract fun settingDao(): SettingDao

    companion object {
        fun getInstance(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "db.db")
                .createFromAsset("database/db.db")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build()
    }
}