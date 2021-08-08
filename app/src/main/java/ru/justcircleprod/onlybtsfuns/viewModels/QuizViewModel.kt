package ru.justcircleprod.onlybtsfuns.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.justcircleprod.onlybtsfuns.data.*

class QuizViewModel(repository: AppRepository, val categoryId: Int) : ViewModel() {
    val countOfQuestions = 6
    private lateinit var questions: Array<*>

    val textQuestion = MutableLiveData<TextQuestion>()
    val imageQuestion = MutableLiveData<ImageQuestion>()
    val videoQuestion = MutableLiveData<VideoQuestion>()
    val audioQuestion = MutableLiveData<AudioQuestion>()

    val pointsForThisQuestion = MutableLiveData(0)
    val score = MutableLiveData(0)

    var answer = ""

    init {
        viewModelScope.launch {
            questions = repository.getQuestions(categoryId, countOfQuestions)
        }
    }

    fun updateQuestion(i: Int) {
        val index = i - 1
        when (questions[index]) {
            is TextQuestion -> {
                val question = questions[index] as TextQuestion
                textQuestion.postValue(question)
                setQuestionsData(
                    question.firstOption,
                    question.secondOption,
                    question.thirdOption,
                    question.fourthOption,
                    question.answerNum,
                    question.points
                )
            }
            is ImageQuestion -> {
                val question = questions[index] as ImageQuestion
                imageQuestion.postValue(question)
                setQuestionsData(
                    question.firstOption,
                    question.secondOption,
                    question.thirdOption,
                    question.fourthOption,
                    question.answerNum,
                    question.points
                )
            }
            is VideoQuestion -> {
                val question = questions[index] as VideoQuestion
                videoQuestion.postValue(question)
                setQuestionsData(
                    question.firstOption,
                    question.secondOption,
                    question.thirdOption,
                    question.fourthOption,
                    question.answerNum,
                    question.points
                )
            }
            is AudioQuestion -> {
                val question = questions[index] as AudioQuestion
                audioQuestion.postValue(question)
                setQuestionsData(
                    question.firstOption,
                    question.secondOption,
                    question.thirdOption,
                    question.fourthOption,
                    question.answerNum,
                    question.points
                )
            }
        }
    }

    private fun setQuestionsData(
        firstOption: String,
        secondOption: String,
        thirdOption: String,
        fourthOption: String,
        answerNum: Int,
        points: Int
    ) {
        answer = listOf(
            firstOption, secondOption, thirdOption, fourthOption
        )[answerNum - 1]
        pointsForThisQuestion.postValue(points)
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