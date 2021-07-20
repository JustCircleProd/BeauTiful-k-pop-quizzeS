package ru.justcircleprod.onlybtsfuns.data

import android.content.Context
import android.os.Parcelable

class AppRepository(context: Context) {
    private val textQuestionDao: TextQuestionDao
    private val imageQuestionDao: ImageQuestionDao
    private val scoreDao: ScoreDao

    init {
        val db = AppDatabase.getDatabase(context)
        textQuestionDao = db.textQuestionDao()
        imageQuestionDao = db.imageQuestionDao()
        scoreDao = db.scoreDao()
    }

    suspend fun getQuestions(categoryId: Int, countOfQuestions: Int): Array<*> {
        return when (categoryId) {
            1 -> getRandomQuestions(countOfQuestions)
            2 -> getTextQuestions(countOfQuestions)
            3 -> getImageQuestions(countOfQuestions)
            else -> getRandomQuestions(countOfQuestions)
        }
    }

    private suspend fun getRandomQuestions(countOfQuestions: Int): Array<Parcelable> {
        val countOfTextQuestion = (2..countOfQuestions - 2).random()
        val countOfImageQuestion = countOfQuestions - countOfTextQuestion

        val countOfTextRaw = textQuestionDao.getCount()
        val countOfImageRaw = imageQuestionDao.getCount()

        val textIds: MutableSet<Int> = mutableSetOf()
        while (textIds.size < countOfTextQuestion) {
            textIds.add((1..countOfTextRaw).random())
        }
        val textQuestions = textQuestionDao.loadAllByIds(textIds.toIntArray())

        val imageIds: MutableSet<Int> = mutableSetOf()
        while (imageIds.size < countOfImageQuestion) {
            imageIds.add((1..countOfImageRaw).random())
        }
        val imageQuestions = imageQuestionDao.loadAllByIds(imageIds.toIntArray())

        return (textQuestions.toList() + imageQuestions.toList()).shuffled().toTypedArray()
    }

    private suspend fun getTextQuestions(countOfQuestions: Int): Array<TextQuestion> {
        val countOfRaw = textQuestionDao.getCount()
        val ids: MutableSet<Int> = mutableSetOf()
        while (ids.size < countOfQuestions) {
            ids.add((1..countOfRaw).random())
        }

        return textQuestionDao.loadAllByIds(ids.toIntArray())
    }

    private suspend fun getImageQuestions(countOfQuestions: Int): Array<ImageQuestion> {
        val countOfRaw = imageQuestionDao.getCount()
        val ids: MutableSet<Int> = mutableSetOf()
        while (ids.size < countOfQuestions) {
            ids.add((1..countOfRaw).random())
        }

        return imageQuestionDao.loadAllByIds(ids.toIntArray())
    }

    suspend fun getScores() = scoreDao.getAll().toTypedArray()

    suspend fun getScore(id: Int) = scoreDao.findById(id)

    suspend fun updateScore(id: Int, score: Int) {
        scoreDao.update(id, score)
    }
}