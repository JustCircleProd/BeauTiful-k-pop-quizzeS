package ru.justcircleprod.onlybtsfuns.data.models.oldModels

import androidx.room.Dao
import androidx.room.Query

@Dao
interface VideoQuestionDao {
    @Query("SELECT * FROM video_questions WHERE id IN (:ids)")
    suspend fun getByIds(ids: IntArray): Array<VideoQuestion>

    @Query("SELECT COUNT(id) FROM video_questions")
    suspend fun getCount(): Int

    @Query("SELECT id FROM video_questions WHERE points >= :lowerPoints AND points <= :upperPoints")
    suspend fun getIds(lowerPoints: Int, upperPoints: Int): List<Int>
}