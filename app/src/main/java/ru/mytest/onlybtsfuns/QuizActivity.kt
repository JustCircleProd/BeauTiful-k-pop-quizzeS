package ru.mytest.onlybtsfuns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import ru.mytest.onlybtsfuns.dataClasses.ImageQuestion
import ru.mytest.onlybtsfuns.dataClasses.TextQuestion
import ru.mytest.onlybtsfuns.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var questions: Array<*>
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
        updateQuestionsData()
    }


    private fun updateQuestionsData() {
        val currentQuestion = binding.progress.progress - 1

        when (questions[currentQuestion]) {
            is TextQuestion -> {
                val question = questions[currentQuestion] as TextQuestion
                answer = question.options[question.answerNum - 1]

                binding.textQuestion.text = question.question
                binding.textQuestion.visibility = View.VISIBLE
                updateOptions(question.options)
            }
            is ImageQuestion -> {
                val question = questions[currentQuestion] as ImageQuestion
                answer = question.options[question.answerNum - 1]

                binding.imageQuestion.setImageResource(question.image)
                binding.imageQuestion.visibility = View.VISIBLE
                updateOptions(question.options)
            }
        }
    }

    private fun updateOptions(options: Array<String>) {
        binding.firstOption.text = options[0]
        binding.secondOption.text = options[1]
        binding.thirdOption.text = options[2]
        binding.fourthOption.text = options[3]
    }

    override fun onClick(v: View?) {
        if (v is MaterialButton) {
            showRightAndWrong(v)
            if (binding.progress.progress < 6) {
                val timer = object : CountDownTimer(2000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}
                    override fun onFinish() {
                        updateViews()
                        updateQuestionsData()
                    }
                }
                timer.start()
            } else {
                val context = this
                val timer = object : CountDownTimer(2000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}
                    override fun onFinish() {
                        val intent = Intent(context, ResultActivity::class.java)
                        intent.putExtra("result", correctAnswers)
                        startActivity(intent)
                        finish()
                    }
                }
                timer.start()
            }
        }
    }

    private fun showRightAndWrong(v: MaterialButton) {
        binding.firstOption.isEnabled = false
        binding.firstOption.isClickable = false
        binding.secondOption.isEnabled = false
        binding.secondOption.isClickable = false
        binding.thirdOption.isEnabled = false
        binding.thirdOption.isClickable = false
        binding.fourthOption.isEnabled = false
        binding.fourthOption.isClickable = false

        if (v.text == answer) {
            correctAnswers++
            v.setBackgroundColor(ContextCompat.getColor(this, R.color.correct_answer_color))
        } else {
            v.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.incorrect_answer_color
                )
            )
            when (answer) {
                binding.firstOption.text.toString() ->
                    binding.firstOption.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.correct_answer_color
                        )
                    )
                binding.secondOption.text.toString() ->
                    binding.secondOption.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.correct_answer_color
                        )
                    )
                binding.thirdOption.text.toString() ->
                    binding.thirdOption.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.correct_answer_color
                        )
                    )
                binding.fourthOption.text.toString() ->
                    binding.fourthOption.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.correct_answer_color
                        )
                    )
            }
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