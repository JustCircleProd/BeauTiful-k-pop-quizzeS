package ru.justcircleprod.onlybtsfuns.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import ru.justcircleprod.onlybtsfuns.data.models.AudioQuestion

@Dao
interface AudioQuestionDao {
    @Query("SELECT * FROM audio_questions WHERE id IN (:ids)")
    suspend fun getByIds(ids: IntArray): Array<AudioQuestion>

    @Query("SELECT COUNT(id) FROM audio_questions")
    suspend fun getCount(): Int

    @Query("SELECT id FROM audio_questions WHERE points >= :lowerPoints AND points <= :upperPoints")
    suspend fun getIds(lowerPoints: Int, upperPoints: Int): List<Int>
}