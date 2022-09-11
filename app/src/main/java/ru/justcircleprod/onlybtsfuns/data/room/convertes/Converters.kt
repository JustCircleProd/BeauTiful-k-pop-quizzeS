package ru.justcircleprod.onlybtsfuns.data.room.convertes

import androidx.room.TypeConverter
import ru.justcircleprod.onlybtsfuns.data.models.QuestionContentType

class Converters {
    @TypeConverter
    fun toPassedQuestionContentType(value: String) = QuestionContentType.fromString(value)

    @TypeConverter
    fun fromPassedQuestionContentType(value: QuestionContentType) = value.toString()
}