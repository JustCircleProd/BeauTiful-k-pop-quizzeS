package ru.mytest.onlybtsfuns.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mytest.onlybtsfuns.data.AppRepository
import ru.mytest.onlybtsfuns.data.Score

class ResultsViewModel(private val repository: AppRepository) : ViewModel() {
    fun getScores(): Array<Score> {
        var scores = arrayOf<Score>()
        viewModelScope.launch {
            scores = repository.getScores()
        }
        return scores
    }
}