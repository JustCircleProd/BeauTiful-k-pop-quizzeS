package ru.justcircleprod.onlybtsfuns.ui.quizResult

import android.animation.LayoutTransition
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.justcircleprod.onlybtsfuns.R
import ru.justcircleprod.onlybtsfuns.databinding.ActivityQuizResultBinding

@AndroidEntryPoint
class QuizResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizResultBinding
    private val viewModel: QuizResultViewModel by viewModels()

    companion object {
        const val CATEGORY_ID_ARGUMENT_NAME = "categoryId"
        const val SCORE_ARGUMENT_NAME = "score"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizResultBinding.inflate(layoutInflater)

        enableAnimations()

        binding.toCategories.setOnClickListener { super.onBackPressed() }

        setScoresObservers()
        setLoadingObserver()

        setContentView(binding.root)
    }

    private fun enableAnimations() {
        binding.contentLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
    }

    private fun setLoadingObserver() {
        viewModel.isLoading.observe(this) { isLoading ->
            if (!isLoading) {
                showResult()
                binding.loadLayout.visibility = View.GONE
                binding.contentLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun setScoresObservers() {
        viewModel.currentScore.observe(this) {
            binding.score.text = it.toString()
        }
        viewModel.lastScore.observe(this) {
            binding.bestScore.text = it.toString()
        }
    }

    private fun showResult() {
        when {
            viewModel.currentScore.value!! > viewModel.lastScore.value!! -> {
                binding.bestScoreLabel.visibility = View.GONE
                binding.bestScore.visibility = View.GONE

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