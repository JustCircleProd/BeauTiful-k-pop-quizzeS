package ru.justcircleprod.onlybtsfuns.data

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "audio_questions")
data class AudioQuestion(
    @NonNull @PrimaryKey(autoGenerate = true) val id: Int,
    @NonNull @ColumnInfo(name = "audio_entry_name") val audio_entry_name: String,
    @NonNull @ColumnInfo(name = "first_option") val firstOption: String,
    @NonNull @ColumnInfo(name = "second_option") val secondOption: String,
    @NonNull @ColumnInfo(name = "third_option") val thirdOption: String,
    @NonNull @ColumnInfo(name = "fourth_option") val fourthOption: String,
    @NonNull @ColumnInfo(name = "answer_num") val answerNum: Int,
    @NonNull @ColumnInfo(name = "points") val points: Int
) : Parcelable