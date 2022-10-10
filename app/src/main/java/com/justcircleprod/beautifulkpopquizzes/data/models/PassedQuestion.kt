package com.justcircleprod.beautifulkpopquizzes.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passed_questions")
data class PassedQuestion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "question_id") val questionId: Int,
    @ColumnInfo(name = "question_content_type") val questionContentType: QuestionContentType,
) {
    companion object {
        fun Question.toPassedQuestion(): PassedQuestion {
            val contentType = when (this) {
                is TextQuestion -> {
                    QuestionContentType.TEXT_CONTENT_TYPE
                }
                is ImageQuestion -> {
                    QuestionContentType.IMAGE_CONTENT_TYPE
                }
                is VideoQuestion -> {
                    QuestionContentType.VIDEO_CONTENT_TYPE
                }
                is AudioQuestion -> {
                    QuestionContentType.AUDIO_CONTENT_TYPE
                }
                else -> {
                    QuestionContentType.TEXT_CONTENT_TYPE
                }
            }

            return PassedQuestion(questionId = this.id, questionContentType = contentType)
        }
    }
}