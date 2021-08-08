package ru.justcircleprod.onlybtsfuns.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.justcircleprod.onlybtsfuns.dataForUpdate.AudioQuestionsStorage
import ru.justcircleprod.onlybtsfuns.dataForUpdate.ImageQuestionUpdatedPoints
import ru.justcircleprod.onlybtsfuns.dataForUpdate.TextQuestionUpdatedPoints
import ru.justcircleprod.onlybtsfuns.dataForUpdate.VideoQuestionsStorage

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
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
            "CREATE TABLE IF NOT EXISTS video_questions (" +
                    "id	INTEGER NOT NULL," +
                    "video_entry_name	TEXT NOT NULL," +
                    "first_option	TEXT NOT NULL," +
                    "second_option	TEXT NOT NULL," +
                    "third_option	TEXT NOT NULL," +
                    "fourth_option	TEXT NOT NULL," +
                    "answer_num	INTEGER NOT NULL, " +
                    "points INTEGER NOT NULL DEFAULT 0," +
                    "PRIMARY KEY(id AUTOINCREMENT))"
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
                    "PRIMARY KEY(id AUTOINCREMENT))"
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

        database.execSQL(
            "INSERT OR IGNORE INTO scores (id, category, score) VALUES (4, \"video_questions\", 0)"
        )

        database.execSQL(
            "INSERT OR IGNORE INTO scores (id, category, score) VALUES (5, \"audio_questions\", 0)"
        )

        database.execSQL(
            "CREATE TABLE IF NOT EXISTS settings (" +
                    "id	INTEGER NOT NULL," +
                    "name	TEXT NOT NULL," +
                    "state	INTEGER NOT NULL," +
                    "PRIMARY KEY(id AUTOINCREMENT))"
        )

        database.execSQL(
            "INSERT OR IGNORE INTO settings (id, name, state) VALUES (1, \"difficulty\", 0)"
        )
    }
}

@Database(
    entities = [TextQuestion::class, ImageQuestion::class, VideoQuestion::class,
        AudioQuestion::class, Score::class, Setting::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun textQuestionDao(): TextQuestionDao
    abstract fun imageQuestionDao(): ImageQuestionDao
    abstract fun videoQuestionDao(): VideoQuestionDao
    abstract fun audioQuestionDao(): AudioQuestionDao
    abstract fun scoreDao(): ScoreDao
    abstract fun settingDao(): SettingDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context, AppDatabase::class.java, "db.db")
                    .createFromAsset("database/db.db")
                    .addMigrations(MIGRATION_1_2)
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}