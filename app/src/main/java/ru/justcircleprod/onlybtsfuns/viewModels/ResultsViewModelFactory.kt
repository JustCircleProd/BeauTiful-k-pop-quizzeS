package ru.justcircleprod.onlybtsfuns.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.justcircleprod.onlybtsfuns.data.AppRepository

class ResultsViewModelFactory(private val repository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ResultsViewModel(repository) as T
    }
}