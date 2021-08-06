package ru.justcircleprod.onlybtsfuns.data

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "settings")
data class Setting(
    @NonNull @PrimaryKey(autoGenerate = true) val id: Int,
    @NonNull @ColumnInfo(name = "name") val name: String,
    @NonNull @ColumnInfo(name = "state") val state: Int
) : Parcelable