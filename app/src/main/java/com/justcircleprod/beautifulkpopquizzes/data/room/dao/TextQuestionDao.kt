package com.justcircleprod.beautifulkpopquizzes.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.justcircleprod.beautifulkpopquizzes.data.models.TextQuestion

@Dao
interface TextQuestionDao {
    @Query("SELECT * FROM text_questions WHERE id IN (:ids)")
    suspend fun getByIds(ids: IntArray): Array<TextQuestion>

    @Query("SELECT COUNT(id) FROM text_questions")
    suspend fun getCount(): Int

    @Query("SELECT id FROM text_questions WHERE points >= :lowerPoints AND points <= :upperPoints")
    suspend fun getIds(lowerPoints: Int, upperPoints: Int): List<Int>
}