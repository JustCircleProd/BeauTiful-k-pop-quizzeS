package ru.mytest.onlybtsfuns.viewModels

import androidx.lifecycle.ViewModel
import ru.mytest.onlybtsfuns.data.AppRepository

class ResultsViewModel(private val repository: AppRepository) : ViewModel() {
    fun getScores() = repository.getScores()
}