package ru.justcircleprod.onlybtsfuns.data.room

import androidx.room.TypeConverter
import ru.justcircleprod.onlybtsfuns.data.models.PassedQuestionContentType

class Converters {
    @TypeConverter
    fun toPassedQuestionContentType(value: String) = PassedQuestionContentType.fromString(value)

    @TypeConverter
    fun fromPassedQuestionContentType(value: PassedQuestionContentType) = value.toString()
}