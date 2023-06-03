package com.justcircleprod.beautifulkpopquizzes.ui.categories

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.justcircleprod.beautifulkpopquizzes.R
import com.justcircleprod.beautifulkpopquizzes.appConstants.QuizCategory
import com.justcircleprod.beautifulkpopquizzes.data.room.constants.DifficultyState
import com.justcircleprod.beautifulkpopquizzes.databinding.ActivityCategoriesBinding
import com.justcircleprod.beautifulkpopquizzes.ui.extensions.makeBrandLabelColorful
import com.justcircleprod.beautifulkpopquizzes.ui.quiz.QuizActivity
import com.justcircleprod.beautifulkpopquizzes.util.areEnglishResourcesUsed


@AndroidEntryPoint
class CategoriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoriesBinding
    private val viewModel: CategoriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)

        binding.brandLabel.makeBrandLabelColorful()

        if (areEnglishResourcesUsed()) {
            binding.textQuestions.visibility = View.GONE
        }

        binding.backBtn.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        setOnClickListeners()

        setContentView(binding.root)
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