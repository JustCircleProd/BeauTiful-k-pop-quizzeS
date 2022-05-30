package ru.justcircleprod.onlybtsfuns.data.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class Setting(
    @NonNull @PrimaryKey(autoGenerate = true) val id: Int,
    @NonNull @ColumnInfo(name = "state") val state: Int
)