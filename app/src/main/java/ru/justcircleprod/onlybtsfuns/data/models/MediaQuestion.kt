package ru.justcircleprod.onlybtsfuns.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MediaQuestion(
    override val id: Int,
    val mediaUrl: String = "",
    val type: MediaQuestionType = MediaQuestionType.IMAGE_QUESTION,
    override val firstOption: String = "",
    override val secondOption: String = "",
    override val thirdOption: String = "",
    override val fourthOption: String = "",
    override val answerNum: Int = 1,
    override val points: Int = 0
) : Question, Parcelable {
    companion object {
        fun Map<String, Any>.toMediaQuestions(): List<MediaQuestion> {
            val mediaQuestions = mutableListOf<MediaQuestion>()

            forEach {
                val entry = it.value as ArrayList<*>

                mediaQuestions.add(
                    MediaQuestion(
                        it.key.toInt(),
                        entry[0] as String,
                        MediaQuestionType.fromString(entry[1] as String),
                        entry[2] as String,
                        entry[3] as String,
                        entry[4] as String,
                        entry[5] as String,
                        (entry[6] as Long).toInt(),
                        (entry[7] as Long).toInt(),
                    )
                )
            }

            return mediaQuestions
        }
    }
}