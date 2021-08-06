package ru.justcircleprod.onlybtsfuns.data

import android.content.Context
import android.os.Parcelable

class AppRepository(context: Context) {
    private val textQuestionDao: TextQuestionDao
    private val imageQuestionDao: ImageQuestionDao
    private val videoQuestionDao: VideoQuestionDao
    private val audioQuestionDao: AudioQuestionDao
    private val scoreDao: ScoreDao
    private val settingDao: SettingDao

    init {
        val db = AppDatabase.getDatabase(context)
        textQuestionDao = db.textQuestionDao()
        imageQuestionDao = db.imageQuestionDao()
        videoQuestionDao = db.videoQuestionDao()
        audioQuestionDao = db.audioQuestionDao()
        scoreDao = db.scoreDao()
        settingDao = db.settingDao()
    }

    suspend fun getQuestions(categoryId: Int, countOfQuestions: Int): Array<*> {
        val difficultyState = getSetting(1).state

        val lowerPoints = when (difficultyState) {
            0 -> 400
            1 -> 400
            2 -> 500
            else -> 400
        }

        val upperPoints = when (difficultyState) {
            0 -> 600
            1 -> 500
            2 -> 600
            else -> 600
        }
        return when (categoryId) {
            1 -> getRandomQuestions(countOfQuestions, lowerPoints, upperPoints)
            2 -> getTextQuestions(countOfQuestions, lowerPoints, upperPoints)
            3 -> getImageQuestions(countOfQuestions, lowerPoints, upperPoints)
            4 -> getVideoQuestions(countOfQuestions, lowerPoints, upperPoints)
            5 -> getAudioQuestions(countOfQuestions, lowerPoints, upperPoints)
            else -> getRandomQuestions(countOfQuestions, lowerPoints, upperPoints)
        }
    }

    private suspend fun getRandomQuestions(
        countOfQuestions: Int,
        lowerPoints: Int,
        upperPoints: Int
    ): Array<Parcelable> {
        val countOfAudioQuestion = (1..2).random()
        val countOfImageQuestion = (1..2).random()
        val countOfVideoQuestion = 1
        val countOfTextQuestion =
            countOfQuestions - (countOfImageQuestion + countOfAudioQuestion) - countOfVideoQuestion


        val textQuestions = getTextQuestions(countOfTextQuestion, lowerPoints, upperPoints)
        val imageQuestions = getImageQuestions(countOfImageQuestion, lowerPoints, upperPoints)
        val videoQuestion = getVideoQuestions(countOfVideoQuestion, lowerPoints, upperPoints)
        val audioQuestion = getAudioQuestions(countOfAudioQuestion, lowerPoints, upperPoints)

        return (textQuestions.toList() + imageQuestions.toList()
                + videoQuestion.toList() + audioQuestion.toList())
            .shuffled().toTypedArray()
    }

    private suspend fun getTextQuestions(
        countOfQuestions: Int,
        lowerPoints: Int,
        upperPoints: Int
    ): Array<TextQuestion> {
        val ids = textQuestionDao.getIds(lowerPoints, upperPoints).shuffled().take(countOfQuestions)

        return textQuestionDao.loadAllByIds(ids.toIntArray())
    }

    private suspend fun getImageQuestions(
        countOfQuestions: Int,
        lowerPoints: Int,
        upperPoints: Int
    ): Array<ImageQuestion> {
        val ids =
            imageQuestionDao.getIds(lowerPoints, upperPoints).shuffled().take(countOfQuestions)

        return imageQuestionDao.loadAllByIds(ids.toIntArray())
    }

    private suspend fun getVideoQuestions(
        countOfQuestions: Int,
        lowerPoints: Int,
        upperPoints: Int
    ): Array<VideoQuestion> {
        val ids =
            videoQuestionDao.getIds(lowerPoints, upperPoints).shuffled().take(countOfQuestions)

        return videoQuestionDao.loadAllByIds(ids.toIntArray())
    }

    private suspend fun getAudioQuestions(
        countOfQuestions: Int,
        lowerPoints: Int,
        upperPoints: Int
    ): Array<AudioQuestion> {
        val ids =
            audioQuestionDao.getIds(lowerPoints, upperPoints).shuffled().take(countOfQuestions)

        return audioQuestionDao.loadAllByIds(ids.toIntArray())
    }

    suspend fun getScores() = scoreDao.getAll().toTypedArray()

    suspend fun getScore(id: Int) = scoreDao.findById(id)

    suspend fun updateScore(id: Int, score: Int) {
        scoreDao.update(id, score)
    }

    suspend fun getSetting(id: Int) = settingDao.loadById(id)

    suspend fun updateSetting(id: Int, state: Int) {
        settingDao.update(id, state)
    }
}