package ru.justcircleprod.onlybtsfuns.data.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video_questions")
data class VideoQuestion(
    @NonNull @PrimaryKey(autoGenerate = true) override val id: Int,
    @NonNull @ColumnInfo(name = "video_entry_name") val video_entry_name: String,
    @NonNull @ColumnInfo(name = "first_option") override val firstOption: String,
    @NonNull @ColumnInfo(name = "second_option") override val secondOption: String,
    @NonNull @ColumnInfo(name = "third_option") override val thirdOption: String,
    @NonNull @ColumnInfo(name = "fourth_option") override val fourthOption: String,
    @NonNull @ColumnInfo(name = "answer_num") override val answerNum: Int,
    @NonNull @ColumnInfo(name = "points") override val points: Int
) : Question