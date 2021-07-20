package ru.justcircleprod.onlybtsfuns.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.justcircleprod.onlybtsfuns.data.AppRepository

class ResultViewModel(
    private val repository: AppRepository,
    private val categoryId: Int,
    score: Int
) : ViewModel() {
    val currentScore = MutableLiveData(score)
    val bestScore = MutableLiveData<Int>()

    init {
        viewModelScope.launch {
            bestScore.postValue(repository.getScore(categoryId).score)
        }
    }

    fun updateScore() {
        viewModelScope.launch {
            repository.updateScore(categoryId, currentScore.value!!)
        }
    }
}