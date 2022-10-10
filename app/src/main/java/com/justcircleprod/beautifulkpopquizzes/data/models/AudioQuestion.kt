package com.justcircleprod.beautifulkpopquizzes.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audio_questions")
data class AudioQuestion(
    @PrimaryKey(autoGenerate = true) override val id: Int,
    @ColumnInfo(name = "audio_entry_name") val audio_entry_name: String,
    @ColumnInfo(name = "first_option") override val firstOption: String,
    @ColumnInfo(name = "second_option") override val secondOption: String,
    @ColumnInfo(name = "third_option") override val thirdOption: String,
    @ColumnInfo(name = "fourth_option") override val fourthOption: String,
    @ColumnInfo(name = "answer_num") override val answerNum: Int,
    @ColumnInfo(name = "points") override val points: Int
) : Question