package ru.justcircleprod.onlybtsfuns.data.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passed_questions")
data class PassedQuestion(
    @NonNull @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @NonNull @ColumnInfo(name = "question_id") val questionId: Int,
    @NonNull @ColumnInfo(name = "question_content_type") val questionContentType: PassedQuestionContentType,
) {
    companion object {
        fun Question.toPassedQuestion(): PassedQuestion {
            val contentType = when (this) {
                is TextQuestion -> {
                    PassedQuestionContentType.TEXT_CONTENT_TYPE
                }
                is ImageQuestion -> {
                    PassedQuestionContentType.IMAGE_CONTENT_TYPE
                }
                is VideoQuestion -> {
                    PassedQuestionContentType.VIDEO_CONTENT_TYPE
                }
                is AudioQuestion -> {
                    PassedQuestionContentType.AUDIO_CONTENT_TYPE
                }
                else -> {
                    PassedQuestionContentType.TEXT_CONTENT_TYPE
                }
            }

            return PassedQuestion(questionId = this.id, questionContentType = contentType)
        }
    }
}