package com.justcircleprod.beautifulkpopquizzes.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.justcircleprod.beautifulkpopquizzes.data.models.Setting

@Dao
interface SettingDao {
    @Query("SELECT * FROM settings WHERE id = :id")
    suspend fun getById(id: Int): Setting

    @Update
    suspend fun update(setting: Setting)
}