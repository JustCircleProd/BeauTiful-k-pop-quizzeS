package ru.justcircleprod.onlybtsfuns.ui.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.justcircleprod.onlybtsfuns.appConstants.QuizCategory
import ru.justcircleprod.onlybtsfuns.data.AppRepository
import ru.justcircleprod.onlybtsfuns.data.models.MediaQuestion.Companion.toMediaQuestions
import ru.justcircleprod.onlybtsfuns.data.models.PassedQuestion.Companion.toPassedQuestion
import ru.justcircleprod.onlybtsfuns.data.models.PassedQuestionContentType
import ru.justcircleprod.onlybtsfuns.data.models.Question
import ru.justcircleprod.onlybtsfuns.data.room.constants.AppDatabaseConstants
import ru.justcircleprod.onlybtsfuns.data.room.constants.DifficultyState
import ru.justcircleprod.onlybtsfuns.data.room.constants.QuestionsRepetitionState
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: AppRepository,
    state: SavedStateHandle
) : ViewModel() {
    // list of boolean values, each of which indicates the loading status from different sources
    // (the size may vary depending on the category)
    val isLoading = MutableLiveData(mutableListOf(true, true, true, true))

    // value to prevent the observer from triggering when setting the default value
    var isFirstStart = true

    val countOfQuestions = 6
    val categoryId = state.get<Int>(QuizActivity.CATEGORY_ID_ARGUMENT_NAME)!!

    // the list of questions (public, because its size is involved in some checks)
    var questions = mutableListOf<Question>()

    private var questionPosition = 0

    // the current question or a sign of the end of questions is placed here
    val question = MutableLiveData<Question?>()

    private var noQuestionRepetition = false

    val score = MutableLiveData(0)

    init {
        viewModelScope.launch(Dispatchers.Default) {
            noQuestionRepetition = getNoQuestionsRepetitionValue()
            val (lowerPoints, upperPoints) = getPointBorders()

            when (categoryId) {
                QuizCategory.TEXT_CATEGORY.value -> {
                    setTextQuestions(
                        countOfQuestions,
                        lowerPoints,
                        upperPoints
                    )
                }
                QuizCategory.IMAGE_CATEGORY.value -> {
                    setImageQuestions(
                        countOfQuestions,
                        lowerPoints,
                        upperPoints
                    )
                }
                QuizCategory.VIDEO_CATEGORY.value -> {
                    setVideoQuestions(
                        countOfQuestions,
                        lowerPoints,
                        upperPoints
                    )
                }
                QuizCategory.AUDIO_CATEGORY.value -> {
                    setAudioQuestions(
                        countOfQuestions,
                        lowerPoints,
                        upperPoints
                    )
                }
                else -> {
                    setRandomQuestions(
                        lowerPoints,
                        upperPoints
                    )
                }
            }
        }
    }

    private suspend fun getNoQuestionsRepetitionValue(): Boolean {
        return withContext(Dispatchers.IO) {
            QuestionsRepetitionState.fromInt(
                repository.getSetting(AppDatabaseConstants.QUESTIONS_REPETITION_SETTING_ID).state
            ) == QuestionsRepetitionState.NO_REPETITION
        }
    }

    private suspend fun getPointBorders(): List<Int> {
        return withContext(Dispatchers.IO) {
            val difficultySetting =
                repository.getSetting(AppDatabaseConstants.DIFFICULTY_SETTING_ID)

            when (DifficultyState.fromInt(difficultySetting.state)) {
                DifficultyState.USUAL -> {
                    listOf(300, 499)
                }
                DifficultyState.DIFFICULT -> {
                    listOf(500, 600)
                }
                else -> {
                    listOf(300, 600)
                }
            }
        }
    }

    private suspend fun setRandomQuestions(
        lowerPoints: Int,
        upperPoints: Int
    ) {
        val countOfAudioQuestion = (1..2).random()
        val countOfImageQuestion = if (upperPoints >= 500) (1..2).random() else (2..3).random()
        val countOfVideoQuestion = if (upperPoints >= 500) 1 else 0
        val countOfTextQuestion =
            countOfQuestions - (countOfImageQuestion + countOfAudioQuestion) - countOfVideoQuestion

        setTextQuestions(countOfTextQuestion, lowerPoints, upperPoints)
        setImageQuestions(countOfImageQuestion, lowerPoints, upperPoints)
        setAudioQuestions(countOfAudioQuestion, lowerPoints, upperPoints)

        if (countOfVideoQuestion != 0) {
            setVideoQuestions(countOfVideoQuestion, lowerPoints, upperPoints)
        } else {
            isLoading.value!![2] = false
        }
    }

    private suspend fun setTextQuestions(
        countOfQuestions: Int,
        lowerPoints: Int,
        upperPoints: Int
    ) {
        questions.addAll(
            repository.getRandomTextQuestions(
                countOfQuestions,
                lowerPoints,
                upperPoints
            ).apply {
                if (noQuestionRepetition) {
                    val passedQuestionsId = withContext(Dispatchers.IO) {
                        repository.getPassedQuestionsId(PassedQuestionContentType.TEXT_CONTENT_TYPE)
                    }
                    filter { it.id !in passedQuestionsId }
                }
            }
        )

        onLoadingEnd(0)
    }

    private fun setImageQuestions(countOfQuestions: Int, lowerPoints: Int, upperPoints: Int) {
        repository.getImageQuestionsTask().addOnSuccessListener { documents ->
            if (documents.data != null) {
                viewModelScope.launch(Dispatchers.Default) {
                    var imageQuestions = documents.data!!.toMediaQuestions()

                    imageQuestions = imageQuestions.filter { it.points in lowerPoints..upperPoints }
                    if (noQuestionRepetition) {
                        val passedQuestionsId = withContext(Dispatchers.IO) {
                            repository.getPassedQuestionsId(PassedQuestionContentType.IMAGE_CONTENT_TYPE)
                        }
                        imageQuestions = imageQuestions.filter { it.id !in passedQuestionsId }
                    }
                    imageQuestions = imageQuestions.shuffled().take(countOfQuestions)

                    questions.addAll(imageQuestions)

                    onLoadingEnd(1)
                }
            }
        }
    }

    private fun setVideoQuestions(
        countOfQuestions: Int,
        lowerPoints: Int,
        upperPoints: Int
    ) {
        repository.getVideoQuestionsTask().addOnSuccessListener { documents ->
            if (documents.data != null) {
                viewModelScope.launch(Dispatchers.Default) {
                    var videoQuestions = documents.data!!.toMediaQuestions()

                    videoQuestions = videoQuestions.filter { it.points in lowerPoints..upperPoints }
                    if (noQuestionRepetition) {
                        val passedQuestionsId = withContext(Dispatchers.IO) {
                            repository.getPassedQuestionsId(PassedQuestionContentType.VIDEO_CONTENT_TYPE)
                        }
                        videoQuestions = videoQuestions.filter { it.id !in passedQuestionsId }
                    }
                    videoQuestions = videoQuestions.shuffled().take(countOfQuestions)

                    questions.addAll(videoQuestions)

                    onLoadingEnd(2)
                }
            }
        }
    }

    private fun setAudioQuestions(
        countOfQuestions: Int,
        lowerPoints: Int,
        upperPoints: Int
    ) {
        repository.getAudioQuestionsTask().addOnSuccessListener { documents ->
            if (documents.data != null) {
                viewModelScope.launch(Dispatchers.Default) {
                    var audioQuestions = documents.data!!.toMediaQuestions()

                    audioQuestions = audioQuestions.filter { it.points in lowerPoints..upperPoints }
                    if (noQuestionRepetition) {
                        val passedQuestionsId = withContext(Dispatchers.IO) {
                            repository.getPassedQuestionsId(PassedQuestionContentType.AUDIO_CONTENT_TYPE)
                        }
                        audioQuestions = audioQuestions.filter { it.id !in passedQuestionsId }
                    }
                    audioQuestions = audioQuestions.shuffled().take(countOfQuestions)

                    questions.addAll(audioQuestions)

                    onLoadingEnd(3)
                }
            }
        }
    }

    // a method that updates the download status
    // and shuffles them when the desired number of questions is reached
    private fun onLoadingEnd(loadingIndex: Int) {
        if (categoryId == QuizCategory.RANDOM_CATEGORY.value) {
            if (questions.size == countOfQuestions) {
                questions = questions.shuffled() as MutableList<Question>
            }
            isLoading.value!![loadingIndex] = false
            isLoading.postValue(isLoading.value!!)
        } else {
            questions = questions.shuffled() as MutableList<Question>
            isLoading.postValue(mutableListOf(false))
        }
    }

    fun setQuestionOnCurrentPosition() {
        if (questions.size <= countOfQuestions) {
            question.value = questions[questionPosition]
        } else {
            question.value = null
        }
    }

    fun setQuestionOnNextPosition() {
        if (noQuestionRepetition && question.value != null) {
            addToPassedQuestions()
        }

        questionPosition++
        if (questions.size <= countOfQuestions) {
            question.value = questions[questionPosition]
        } else {
            question.value = null
        }
    }

    private fun addToPassedQuestions() {
        val passedQuestion = question.value!!.toPassedQuestion()

        viewModelScope.launch(Dispatchers.IO) {
            repository.insertPassedQuestion(passedQuestion)
        }
    }

    fun checkAnswer(userAnswer: String): Boolean {
        val rightAnswer = listOf(
            question.value!!.firstOption,
            question.value!!.secondOption,
            question.value!!.thirdOption,
            question.value!!.fourthOption,
        )[question.value!!.answerNum - 1]

        return if (userAnswer == rightAnswer) {
            score.value = score.value!! + question.value!!.points
            true
        } else {
            false
        }
    }
}