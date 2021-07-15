package ru.mytest.onlybtsfuns

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.lifecycle.ViewModelProvider
import ru.mytest.onlybtsfuns.data.AppRepository
import ru.mytest.onlybtsfuns.databinding.ActivityResultBinding
import ru.mytest.onlybtsfuns.viewModels.ResultViewModel
import ru.mytest.onlybtsfuns.viewModels.ResultViewModelFactory

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var viewModel: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryId = intent.getIntExtra("categoryId", 1)
        val score = intent.getIntExtra("score", 0)
        val appRepository = AppRepository(this)
        val factory = ResultViewModelFactory(appRepository, categoryId, score)
        viewModel = ViewModelProvider(this, factory).get(ResultViewModel::class.java)

        binding.toCategories.setOnClickListener { super.onBackPressed() }

        setScoresObservers()

        val timer = object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                checkScore()
                binding.loadLayout.visibility = View.GONE
                binding.contentLayout.visibility = View.VISIBLE
            }
        }
        timer.start()
    }

    private fun setScoresObservers() {
        viewModel.currentScore.observe(this, {
            binding.score.text = it.toString()
        })
        viewModel.bestScore.observe(this, {
            binding.bestScore.text = it.toString()
        })
    }

    private fun checkScore() {
        when {
            viewModel.currentScore.value!! > viewModel.bestScore.value!! -> {
                viewModel.updateScore()
                binding.resultText.text = resources.getString(R.string.text_for_best_result)
                binding.resultImage.setImageResource(R.drawable.best_image_result)
                MediaPlayer.create(this, R.raw.best_result).start()
            }
            viewModel.currentScore.value!! < 1000 -> {
                binding.resultText.text = resources.getString(R.string.text_for_bad_result)
                binding.resultImage.setImageResource(R.drawable.bad_image_result)
                MediaPlayer.create(this, R.raw.bad_result).start()
            }
            else -> {
                binding.resultText.text = resources.getString(R.string.text_for_good_result)
                binding.resultImage.setImageResource(R.drawable.good_image_result)
                MediaPlayer.create(this, R.raw.good_result).start()
            }
        }
    }
}