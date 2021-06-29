package ru.mytest.onlybtsfuns.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mytest.onlybtsfuns.data.AppRepository
import kotlin.properties.Delegates

class ResultViewModel(
    private val repository: AppRepository,
    private val categoryId: Int,
    val score: Int
) : ViewModel() {
    var bestScore by Delegates.notNull<Int>()

    init {
        viewModelScope.launch {
            bestScore = repository.getScore(categoryId).score
        }
    }

    fun updateScore() {
        viewModelScope.launch {
            repository.updateScore(categoryId, score)
        }
    }
}