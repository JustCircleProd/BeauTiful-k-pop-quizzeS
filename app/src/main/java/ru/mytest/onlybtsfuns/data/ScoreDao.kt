package ru.mytest.onlybtsfuns.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ScoreDao {
    @Query("SELECT * FROM scores")
    fun getAll(): List<Score>

    @Query("SELECT * FROM scores WHERE id LIKE :id")
    fun findById(id: Int): Score

    @Query("UPDATE scores set score = (:score) WHERE id = (:id)")
    fun update(id: Int, score: Int)
}