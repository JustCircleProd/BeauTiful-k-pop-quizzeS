package ru.mytest.onlybtsfuns.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mytest.onlybtsfuns.data.AppRepository

class ResultViewModelFactory(
    private val repository: AppRepository,
    private val categoryId: Int,
    private val score: Int
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ResultViewModel(repository, categoryId, score) as T
    }
}