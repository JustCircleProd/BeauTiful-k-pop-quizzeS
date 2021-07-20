package ru.justcircleprod.onlybtsfuns.data

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "image_questions")
data class ImageQuestion(
    @NonNull @PrimaryKey(autoGenerate = true) val id: Int,
    @NonNull @ColumnInfo(name = "image_entry_name") val image_entry_name: String,
    @NonNull @ColumnInfo(name = "first_option") val firstOption: String,
    @NonNull @ColumnInfo(name = "second_option") val secondOption: String,
    @NonNull @ColumnInfo(name = "third_option") val thirdOption: String,
    @NonNull @ColumnInfo(name = "fourth_option") val fourthOption: String,
    @NonNull @ColumnInfo(name = "answer_num") val answerNum: Int,
    @NonNull @ColumnInfo(name = "points") val points: Int
) : Parcelable