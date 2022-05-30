package ru.justcircleprod.onlybtsfuns.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SettingDao {
    @Query("SELECT * FROM settings WHERE id = :id")
    suspend fun loadById(id: Int): Setting

    @Query("UPDATE settings set state = (:state) WHERE id = (:id)")
    suspend fun update(id: Int, state: Int)
}