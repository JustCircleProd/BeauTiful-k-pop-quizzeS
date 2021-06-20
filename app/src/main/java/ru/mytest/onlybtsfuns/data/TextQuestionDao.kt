package ru.mytest.onlybtsfuns.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TextQuestionDao {
    @Query("SELECT * FROM text_questions WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): Array<TextQuestion>

    @Query("SELECT COUNT(id) FROM text_questions")
    fun getCount(): Int
}