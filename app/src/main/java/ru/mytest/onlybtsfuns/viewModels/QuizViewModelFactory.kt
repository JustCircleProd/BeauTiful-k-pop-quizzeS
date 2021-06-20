package ru.mytest.onlybtsfuns.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mytest.onlybtsfuns.data.AppRepository

class QuizViewModelFactory(private val repository: AppRepository, private val categoryId: Int) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuizViewModel(repository, categoryId) as T
    }
}