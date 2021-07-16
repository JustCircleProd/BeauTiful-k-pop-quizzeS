package ru.mytest.onlybtsfuns.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mytest.onlybtsfuns.data.AppRepository
import ru.mytest.onlybtsfuns.data.ImageQuestion
import ru.mytest.onlybtsfuns.data.TextQuestion

class QuizViewModel(repository: AppRepository, val categoryId: Int) : ViewModel() {
    val countOfQuestions = 6
    private lateinit var questions: Array<*>

    var textQuestion = MutableLiveData<TextQuestion>()
    var imageQuestion = MutableLiveData<ImageQuestion>()

    var answer = ""
    private var pointsForThisQuestion = 0
    private var countOfCorrect = 0
    var score = 0

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
                pointsForThisQuestion = question.points
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
                pointsForThisQuestion = question.points
            }
        }
    }

    fun checkAnswer(userAnswer: String): Boolean {
        return if (userAnswer == answer) {
            countOfCorrect++
            score += pointsForThisQuestion * (1 + countOfCorrect / 15)
            true
        } else {
            false
        }
    }
}