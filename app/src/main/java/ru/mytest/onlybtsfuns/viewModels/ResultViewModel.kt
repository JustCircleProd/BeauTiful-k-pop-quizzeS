package ru.mytest.onlybtsfuns.viewModels

import androidx.lifecycle.ViewModel
import ru.mytest.onlybtsfuns.data.AppRepository

class ResultViewModel(
    private val repository: AppRepository,
    private val categoryId: Int,
    val score: Int
) : ViewModel() {
    val bestScore: Int = repository.getScore(categoryId).score

    fun updateScore() {
        repository.updateScore(categoryId, score)
    }
}