package ru.mytest.onlybtsfuns

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import ru.mytest.onlybtsfuns.data.AppRepository
import ru.mytest.onlybtsfuns.databinding.ActivityQuizBinding
import ru.mytest.onlybtsfuns.viewModels.QuizViewModel
import ru.mytest.onlybtsfuns.viewModels.QuizViewModelFactory


class QuizActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var viewModel: QuizViewModel
    private lateinit var correctAnswerPlayer: MediaPlayer
    private lateinit var incorrectAnswerPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appRepository = AppRepository(this)
        val categoryId = intent.getIntExtra("categoryId", 1)
        val factory = QuizViewModelFactory(appRepository, categoryId)
        viewModel = ViewModelProvider(this, factory).get(QuizViewModel::class.java)

        binding.firstOption.setOnClickListener(this)
        binding.secondOption.setOnClickListener(this)
        binding.thirdOption.setOnClickListener(this)
        binding.fourthOption.setOnClickListener(this)

        correctAnswerPlayer = MediaPlayer.create(this, R.raw.correct_answer)
        incorrectAnswerPlayer = MediaPlayer.create(this, R.raw.incorrect_answer)

        textQuestionObserver()
        imageQuestionObserver()

        val timer = object : CountDownTimer(2000, 2000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                binding.loadLayout.visibility = View.GONE
                binding.contentLayout.visibility = View.VISIBLE
            }
        }
        timer.start()
    }

    private fun textQuestionObserver() {
        viewModel.textQuestion.observe(this, {
            binding.imageQuestion.visibility = View.GONE
            binding.textQuestion.visibility = View.VISIBLE

            binding.textQuestion.text = it.question
            binding.firstOption.text = it.firstOption
            binding.secondOption.text = it.secondOption
            binding.thirdOption.text = it.thirdOption
            binding.fourthOption.text = it.fourthOption
        })
    }

    private fun imageQuestionObserver() {
        viewModel.imageQuestion.observe(this, {
            binding.textQuestion.visibility = View.GONE
            binding.imageQuestion.visibility = View.VISIBLE

            binding.imageQuestion.setImageResource(
                resources.getIdentifier(
                    it.image_entry_name,
                    "drawable",
                    packageName
                )
            )
            binding.firstOption.text = it.firstOption
            binding.secondOption.text = it.secondOption
            binding.thirdOption.text = it.thirdOption
            binding.fourthOption.text = it.fourthOption
        })
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

    override fun onClick(v: View?) {
        if (v is MaterialButton) {
            disableButtons()
            val isAnswerRight = viewModel.checkAnswer(v.text.toString())
            if (isAnswerRight) {
                doOnRightAnswer(v)
            } else {
                doOnWrongAnswer(v)
            }
        }
    }

    private fun doOnRightAnswer(v: MaterialButton) {
        correctAnswerPlayer.start()
        v.setBackgroundColor(ContextCompat.getColor(this, R.color.correct_answer_color))
        val timer = object : CountDownTimer(2000, 2000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                updateQuestion()
            }
        }
        timer.start()
    }

    private fun doOnWrongAnswer(v: MaterialButton) {
        incorrectAnswerPlayer.start()
        v.setBackgroundColor(ContextCompat.getColor(this, R.color.incorrect_answer_color))
        val timer = object : CountDownTimer(2000, 250) {
            override fun onTick(millisUntilFinished: Long) {
                if (millisUntilFinished < 1750) {
                    showRightAnswer(viewModel.answer)
                }
            }

            override fun onFinish() {
                updateQuestion()
            }
        }
        timer.start()
    }

    private fun showRightAnswer(answer: String) {
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

    private fun updateQuestion() {
        if (binding.progress.progress < viewModel.countOfQuestions) {
            updateViews()
            viewModel.updateQuestion(binding.progress.progress)
        } else {
            startResultActivity()
        }
    }

    private fun updateViews() {
        binding.progress.progress++

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

    private fun startResultActivity() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("categoryId", viewModel.categoryId)
        intent.putExtra("score", viewModel.score)
        startActivity(intent)
        finish()
    }
}