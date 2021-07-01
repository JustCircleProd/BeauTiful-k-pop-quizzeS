package ru.mytest.onlybtsfuns.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ImageQuestionDao {
    @Query("SELECT * FROM image_questions WHERE id IN (:ids)")
    suspend fun loadAllByIds(ids: IntArray): Array<ImageQuestion>

    @Query("SELECT COUNT(id) FROM image_questions")
    suspend fun getCount(): Int
}