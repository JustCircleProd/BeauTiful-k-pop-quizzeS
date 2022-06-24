package ru.justcircleprod.onlybtsfuns.ui.quizResult

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.justcircleprod.onlybtsfuns.data.AppRepository
import javax.inject.Inject

@HiltViewModel
class QuizResultViewModel @Inject constructor(
    private val repository: AppRepository,
    state: SavedStateHandle
) : ViewModel() {
    // 0 - loading and calculating scores
    // 1 - working with interstitial ad
    val isLoading = MutableLiveData(listOf(true, true))

    private val categoryId = state.get<Int>("categoryId")!!

    val currentScore = MutableLiveData(state.get<Int>("score"))
    val lastScore = MutableLiveData<Int>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val lastScoreValue = repository.getScore(categoryId).score
            lastScore.postValue(lastScoreValue)

            if (currentScore.value!! > lastScoreValue) {
                repository.updateScore(categoryId, currentScore.value!!)
                isLoading.postValue(listOf(false, isLoading.value!![1]))
                return@launch
            }

            isLoading.postValue(listOf(false, isLoading.value!![1]))
        }
    }
}