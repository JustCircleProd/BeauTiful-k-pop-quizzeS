package ru.mytest.onlybtsfuns.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TextQuestion::class, ImageQuestion::class, Score::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun textQuestionDao(): TextQuestionDao
    abstract fun imageQuestionDao(): ImageQuestionDao
    abstract fun scoreDao(): ScoreDao
}