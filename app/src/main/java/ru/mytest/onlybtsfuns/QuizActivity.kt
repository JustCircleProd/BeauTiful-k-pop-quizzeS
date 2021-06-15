package ru.mytest.onlybtsfuns

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import ru.mytest.onlybtsfuns.data.ImageQuestion
import ru.mytest.onlybtsfuns.data.TextQuestion
import ru.mytest.onlybtsfuns.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var questions: Array<*>
    private var countOfQuestions = 0
    private var answer = ""
    private var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.firstOption.setOnClickListener(this)
        binding.secondOption.setOnClickListener(this)
        binding.thirdOption.setOnClickListener(this)
        binding.fourthOption.setOnClickListener(this)

        questions = intent.getParcelableArrayExtra("questions") as Array<*>
        countOfQuestions = intent.getIntExtra("countOfQuestions", 6)
        updateQuestionsData()
    }


    private fun updateQuestionsData() {
        val currentQuestion = binding.progress.progress - 1

        when (questions[currentQuestion]) {
            is TextQuestion -> {
                val question = questions[currentQuestion] as TextQuestion
                val options = convertOptions(
                    question.firstOption,
                    question.secondOption,
                    question.thirdOption,
                    question.fourthOption
                )

                answer = options[question.answerNum - 1]

                binding.textQuestion.text = question.question
                binding.textQuestion.visibility = View.VISIBLE
                updateOptions(options)
            }
            is ImageQuestion -> {
                val question = questions[currentQuestion] as ImageQuestion
                val options = convertOptions(
                    question.firstOption,
                    question.secondOption,
                    question.thirdOption,
                    question.fourthOption
                )

                answer = options[question.answerNum - 1]

                binding.imageQuestion.setImageResource(
                    resources.getIdentifier(
                        question.image_entry_name,
                        "drawable",
                        packageName
                    )
                )
                binding.imageQuestion.visibility = View.VISIBLE
                updateOptions(options)
            }
        }
    }

    private fun convertOptions(
        firstOption: String,
        secondOption: String,
        thirdOption: String,
        fourthOption: String
    ): List<String> {
        return listOf(
            firstOption,
            secondOption,
            thirdOption,
            fourthOption
        )
    }

    private fun updateOptions(options: List<String>) {
        binding.firstOption.text = options[0]
        binding.secondOption.text = options[1]
        binding.thirdOption.text = options[2]
        binding.fourthOption.text = options[3]
    }

    override fun onClick(v: View?) {
        if (v is MaterialButton) {
            disableButtons()
            val isAnswerRight = if (v.text == answer) {
                correctAnswers++
                MediaPlayer.create(this, R.raw.correct_answer).start()
                setRightAnswerColor(v)
                true
            } else {
                MediaPlayer.create(this, R.raw.incorrect_answer).start()
                setWrongAnswerColor(v)
                false
            }
            showRightAndWrong(isAnswerRight)
        }
    }

    private fun disableButtons() {
        binding.firstOption.isEnabled = false
        binding.firstOption.isClickable = false
        binding.secondOption.isEnabled = false
        binding.secondOption.isClickable = false
        binding.thirdOption.isEnabled = false
        binding.thirdOption.isClickable = false
        binding.fourthOption.isEnabled = false
        binding.fourthOption.isClickable = false
    }

    private fun showRightAndWrong(isAnswerRight: Boolean) {
        if (binding.progress.progress < countOfQuestions) {
            val timer = object : CountDownTimer(2000, 250) {
                override fun onTick(millisUntilFinished: Long) {
                    if (!isAnswerRight && millisUntilFinished < 1750) {
                        showRightAnswer()
                    }
                }

                override fun onFinish() {
                    updateViews()
                    updateQuestionsData()
                }
            }
            timer.start()
        } else {
            val timer = object : CountDownTimer(2000, 250) {
                override fun onTick(millisUntilFinished: Long) {
                    if (!isAnswerRight && millisUntilFinished < 1750) {
                        showRightAnswer()
                    }
                }

                override fun onFinish() {
                    val intent = Intent(this@QuizActivity, ResultActivity::class.java)
                    intent.putExtra("countOfQuestions", countOfQuestions)
                    intent.putExtra("result", correctAnswers)
                    startActivity(intent)
                    finish()
                }
            }
            timer.start()
        }
    }

    private fun setRightAnswerColor(v: MaterialButton) {
        v.setBackgroundColor(ContextCompat.getColor(this, R.color.correct_answer_color))
    }

    private fun setWrongAnswerColor(v: MaterialButton) {
        v.setBackgroundColor(ContextCompat.getColor(this, R.color.incorrect_answer_color))
    }

    private fun showRightAnswer() {
        when (answer) {
            binding.firstOption.text.toString() ->
                setRightAnswerColor(binding.firstOption)
            binding.secondOption.text.toString() ->
                setRightAnswerColor(binding.secondOption)
            binding.thirdOption.text.toString() ->
                setRightAnswerColor(binding.thirdOption)
            binding.fourthOption.text.toString() ->
                setRightAnswerColor(binding.fourthOption)
        }
    }

    private fun updateViews() {
        binding.progress.progress++

        binding.textQuestion.visibility = View.GONE
        binding.imageQuestion.visibility = View.GONE

        binding.firstOption.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        binding.secondOption.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        binding.thirdOption.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        binding.fourthOption.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

        binding.firstOption.isEnabled = true
        binding.firstOption.isClickable = true
        binding.secondOption.isEnabled = true
        binding.secondOption.isClickable = true
        binding.thirdOption.isEnabled = true
        binding.thirdOption.isClickable = true
        binding.fourthOption.isEnabled = true
        binding.fourthOption.isClickable = true
    }
}