package ru.mytest.onlybtsfuns.data

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "scores")
data class Score(
    @NonNull @PrimaryKey(autoGenerate = true) val id: Int,
    @NonNull @ColumnInfo(name = "category") val category: String,
    @NonNull @ColumnInfo(name = "score") val score: Int
) : Parcelable