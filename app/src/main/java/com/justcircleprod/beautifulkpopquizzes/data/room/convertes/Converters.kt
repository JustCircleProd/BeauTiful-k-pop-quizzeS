package com.justcircleprod.beautifulkpopquizzes.data.room.convertes

import androidx.room.TypeConverter
import com.justcircleprod.beautifulkpopquizzes.data.models.QuestionContentType

class Converters {
    @TypeConverter
    fun toPassedQuestionContentType(value: String) = QuestionContentType.fromString(value)

    @TypeConverter
    fun fromPassedQuestionContentType(value: QuestionContentType) = value.toString()
}