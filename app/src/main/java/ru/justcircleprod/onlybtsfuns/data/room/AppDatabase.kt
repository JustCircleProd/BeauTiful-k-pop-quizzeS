package ru.justcircleprod.onlybtsfuns.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.justcircleprod.onlybtsfuns.data.models.*
import ru.justcircleprod.onlybtsfuns.data.room.convertes.Converters
import ru.justcircleprod.onlybtsfuns.data.room.dao.*
import ru.justcircleprod.onlybtsfuns.data.room.migrations.*


@Database(
    version = 7,
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
                .addMigrations(
                    MIGRATION_1_2,
                    MIGRATION_2_3,
                    MIGRATION_3_4,
                    MIGRATION_4_5,
                    MIGRATION_5_6,
                    MIGRATION_6_7
                )
                .build()
    }
}