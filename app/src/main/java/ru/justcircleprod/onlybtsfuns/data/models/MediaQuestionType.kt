package ru.justcircleprod.onlybtsfuns.data.models

enum class MediaQuestionType {
    IMAGE_QUESTION,
    AUDIO_QUESTION,
    VIDEO_QUESTION;

    companion object {
        fun fromString(value: String) = values().first { it.toString() == value }
    }
}