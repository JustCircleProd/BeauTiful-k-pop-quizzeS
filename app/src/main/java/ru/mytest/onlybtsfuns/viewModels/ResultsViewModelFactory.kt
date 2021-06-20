package ru.mytest.onlybtsfuns.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mytest.onlybtsfuns.data.AppRepository

class ResultsViewModelFactory(private val repository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ResultsViewModel(repository) as T
    }
}