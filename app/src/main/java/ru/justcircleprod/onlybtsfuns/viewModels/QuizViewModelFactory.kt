package ru.justcircleprod.onlybtsfuns.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.justcircleprod.onlybtsfuns.data.AppRepository

class QuizViewModelFactory(private val repository: AppRepository, private val categoryId: Int) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuizViewModel(repository, categoryId) as T
    }
}