package ru.justcircleprod.onlybtsfuns.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.justcircleprod.onlybtsfuns.data.AppRepository
import ru.justcircleprod.onlybtsfuns.data.ImageQuestion
import ru.justcircleprod.onlybtsfuns.data.TextQuestion

class QuizViewModel(repository: AppRepository, val categoryId: Int) : ViewModel() {
    val countOfQuestions = 6
    private lateinit var questions: Array<*>

    val textQuestion = MutableLiveData<TextQuestion>()
    val imageQuestion = MutableLiveData<ImageQuestion>()

    val pointsForThisQuestion = MutableLiveData(0)
    val score = MutableLiveData(0)

    var answer = ""

    init {
        viewModelScope.launch {
            questions = repository.getQuestions(categoryId, countOfQuestions)
            updateQuestion(1)
        }
    }

    fun updateQuestion(i: Int) {
        val index = i - 1
        when (questions[index]) {
            is TextQuestion -> {
                val question = questions[index] as TextQuestion
                textQuestion.postValue(question)
                answer = listOf(
                    question.firstOption,
                    question.secondOption,
                    question.thirdOption,
                    question.fourthOption
                )[question.answerNum - 1]
                pointsForThisQuestion.postValue(question.points)
            }
            is ImageQuestion -> {
                val question = questions[index] as ImageQuestion
                imageQuestion.postValue(question)
                answer = listOf(
                    question.firstOption,
                    question.secondOption,
                    question.thirdOption,
                    question.fourthOption
                )[question.answerNum - 1]
                pointsForThisQuestion.postValue(question.points)
            }
        }
    }

    fun checkAnswer(userAnswer: String): Boolean {
        return if (userAnswer == answer) {
            score.postValue(score.value?.plus(pointsForThisQuestion.value!!))
            true
        } else {
            false
        }
    }
}