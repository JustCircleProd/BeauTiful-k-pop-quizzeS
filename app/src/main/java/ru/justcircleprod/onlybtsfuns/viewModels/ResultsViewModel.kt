package ru.justcircleprod.onlybtsfuns.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.justcircleprod.onlybtsfuns.data.AppRepository

class ResultsViewModel(repository: AppRepository) : ViewModel() {
    val noCategoryScore = MutableLiveData<Int>()
    val textQuestionsScore = MutableLiveData<Int>()
    val imageQuestionsScore = MutableLiveData<Int>()
    val videoQuestionsScore = MutableLiveData<Int>()
    val audioQuestionScore = MutableLiveData<Int>()

    init {
        viewModelScope.launch {
            val scores = repository.getScores()
            noCategoryScore.postValue(scores[0].score)
            textQuestionsScore.postValue(scores[1].score)
            imageQuestionsScore.postValue(scores[2].score)
            videoQuestionsScore.postValue(scores[3].score)
            audioQuestionScore.postValue(scores[4].score)
        }
    }
}