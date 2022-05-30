package ru.justcircleprod.onlybtsfuns.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.justcircleprod.onlybtsfuns.data.AppRepository

class CategoryViewModel(repository: AppRepository) : ViewModel() {
    var difficultyState = 0

    init {
        viewModelScope.launch {
            difficultyState = repository.getSetting(1).state
        }
    }
}