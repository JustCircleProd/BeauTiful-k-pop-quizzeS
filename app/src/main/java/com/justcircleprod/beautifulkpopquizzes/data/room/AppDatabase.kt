package com.justcircleprod.beautifulkpopquizzes.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.justcircleprod.beautifulkpopquizzes.data.models.*
import com.justcircleprod.beautifulkpopquizzes.data.room.convertes.Converters
import com.justcircleprod.beautifulkpopquizzes.data.room.dao.*
import com.justcircleprod.beautifulkpopquizzes.data.room.migrations.MIGRATION_1_2
import com.justcircleprod.beautifulkpopquizzes.data.room.migrations.MIGRATION_2_3
import com.justcircleprod.beautifulkpopquizzes.data.room.migrations.MIGRATION_3_4


@Database(
    version = 4,
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
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                .build()
    }
}