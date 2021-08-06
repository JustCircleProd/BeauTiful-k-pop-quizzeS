package ru.justcircleprod.onlybtsfuns.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.justcircleprod.onlybtsfuns.data.AppRepository

class SettingViewModel(private val repository: AppRepository) : ViewModel() {
    val difficultyState = MutableLiveData<Int>()

    init {
        viewModelScope.launch {
            difficultyState.postValue(repository.getSetting(1).state)
        }
    }

    fun updateDifficulty(state: Int) {
        viewModelScope.launch {
            repository.updateSetting(1, state)
        }
    }
}