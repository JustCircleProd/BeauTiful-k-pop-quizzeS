package ru.justcircleprod.onlybtsfuns.ui.categories

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.justcircleprod.onlybtsfuns.R
import ru.justcircleprod.onlybtsfuns.appConstants.QuizCategory
import ru.justcircleprod.onlybtsfuns.data.room.constants.DifficultyState
import ru.justcircleprod.onlybtsfuns.databinding.ActivityCategoriesBinding
import ru.justcircleprod.onlybtsfuns.ui.quiz.QuizActivity


@AndroidEntryPoint
class CategoriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoriesBinding
    private val viewModel: CategoriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.randomQuestions.setOnClickListener { startQuizActivity(QuizCategory.RANDOM_CATEGORY.value) }
        binding.textQuestions.setOnClickListener { startQuizActivity(QuizCategory.TEXT_CATEGORY.value) }
        binding.imageQuestions.setOnClickListener { startQuizActivity(QuizCategory.IMAGE_CATEGORY.value) }
        binding.videoQuestions.setOnClickListener { onVideoCategoryPressed() }
        binding.audioQuestions.setOnClickListener { startQuizActivity(QuizCategory.AUDIO_CATEGORY.value) }
    }

    private fun onVideoCategoryPressed() {
        lifecycleScope.launch {
            when (viewModel.getDifficultyState()) {
                DifficultyState.RANDOM, DifficultyState.DIFFICULT -> {
                    startQuizActivity(QuizCategory.VIDEO_CATEGORY.value)
                }
                DifficultyState.USUAL -> {
                    Toast.makeText(
                        this@CategoriesActivity,
                        resources.getString(R.string.difficulty_warning),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun startQuizActivity(categoryId: Int) {
        val intent = Intent(this, QuizActivity::class.java)
        intent.putExtra(QuizActivity.CATEGORY_ID_ARGUMENT_NAME, categoryId)
        startActivity(intent)
    }
}