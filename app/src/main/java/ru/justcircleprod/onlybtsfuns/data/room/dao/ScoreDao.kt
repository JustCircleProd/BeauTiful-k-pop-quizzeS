package ru.justcircleprod.onlybtsfuns.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ru.justcircleprod.onlybtsfuns.data.models.Score

@Dao
interface ScoreDao {
    @Query("SELECT * FROM scores")
    fun getAll(): LiveData<List<Score>>

    @Query("SELECT * FROM scores WHERE id LIKE :id")
    suspend fun getById(id: Int): Score

    @Query("UPDATE scores set score = (:score) WHERE id = (:id)")
    suspend fun update(id: Int, score: Int)
}