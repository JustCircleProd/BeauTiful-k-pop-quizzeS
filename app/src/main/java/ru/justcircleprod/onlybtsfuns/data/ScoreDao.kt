package ru.justcircleprod.onlybtsfuns.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ScoreDao {
    @Query("SELECT * FROM scores")
    suspend fun getAll(): List<Score>

    @Query("SELECT * FROM scores WHERE id LIKE :id")
    suspend fun findById(id: Int): Score

    @Query("UPDATE scores set score = (:score) WHERE id = (:id)")
    suspend fun update(id: Int, score: Int)
}