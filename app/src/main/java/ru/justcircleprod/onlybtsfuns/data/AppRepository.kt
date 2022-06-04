package ru.justcircleprod.onlybtsfuns.data

import ru.justcircleprod.onlybtsfuns.data.models.*
import ru.justcircleprod.onlybtsfuns.data.room.AppDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val db: AppDatabase
) {
    suspend fun getRandomTextQuestions(
        countOfQuestions: Int,
        lowerPoints: Int,
        upperPoints: Int,
        noQuestionRepetition: Boolean
    ): Array<TextQuestion> {
        var ids = db.textQuestionDao().getIds(lowerPoints, upperPoints)

        if (noQuestionRepetition) {
            val passedQuestionsId = getPassedQuestionsId(PassedQuestionContentType.TEXT_CONTENT_TYPE)
            ids = ids.filter { it !in passedQuestionsId }
        }

        ids = ids.shuffled().take(countOfQuestions)

        return db.textQuestionDao().getByIds(ids.toIntArray())
    }

    suspend fun getRandomImageQuestions(
        countOfQuestions: Int,
        lowerPoints: Int,
        upperPoints: Int,
        noQuestionRepetition: Boolean
    ): Array<ImageQuestion> {
        var ids = db.imageQuestionDao().getIds(lowerPoints, upperPoints)

        if (noQuestionRepetition) {
            val passedQuestionsId = getPassedQuestionsId(PassedQuestionContentType.IMAGE_CONTENT_TYPE)
            ids = ids.filter { it !in passedQuestionsId }
        }

        ids = ids.shuffled().take(countOfQuestions)

        return db.imageQuestionDao().getByIds(ids.toIntArray())
    }

    suspend fun getRandomAudioQuestions(
        countOfQuestions: Int,
        lowerPoints: Int,
        upperPoints: Int,
        noQuestionRepetition: Boolean
    ): Array<AudioQuestion> {
        var ids = db.audioQuestionDao().getIds(lowerPoints, upperPoints)

        if (noQuestionRepetition) {
            val passedQuestionsId = getPassedQuestionsId(PassedQuestionContentType.AUDIO_CONTENT_TYPE)
            ids = ids.filter { it !in passedQuestionsId }
        }

        ids = ids.shuffled().take(countOfQuestions)

        return db.audioQuestionDao().getByIds(ids.toIntArray())
    }

    suspend fun getRandomVideoQuestions(
        countOfQuestions: Int,
        lowerPoints: Int,
        upperPoints: Int,
        noQuestionRepetition: Boolean
    ): Array<VideoQuestion> {
        var ids = db.videoQuestionDao().getIds(lowerPoints, upperPoints)

        if (noQuestionRepetition) {
            val passedQuestionsId = getPassedQuestionsId(PassedQuestionContentType.VIDEO_CONTENT_TYPE)
            ids = ids.filter { it !in passedQuestionsId }
        }

        ids = ids.shuffled().take(countOfQuestions)

        return db.videoQuestionDao().getByIds(ids.toIntArray())
    }

    suspend fun getPassedQuestionsId(questionContentType: PassedQuestionContentType) =
        db.passedQuestionDao().getIdsByContentType(questionContentType)

    suspend fun insertPassedQuestion(passedQuestion: PassedQuestion) {
        db.passedQuestionDao().insert(passedQuestion)
    }

    suspend fun getSetting(id: Int) = db.settingDao().getById(id)

    suspend fun updateSetting(setting: Setting) {
        db.settingDao().update(setting)
    }

    fun getScores() = db.scoreDao().getAll()

    suspend fun getScore(id: Int) = db.scoreDao().getById(id)

    suspend fun updateScore(id: Int, score: Int) {
        db.scoreDao().update(id, score)
    }
}