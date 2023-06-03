package com.justcircleprod.beautifulkpopquizzes.ui.results

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import com.justcircleprod.beautifulkpopquizzes.databinding.ActivityResultsBinding
import com.justcircleprod.beautifulkpopquizzes.ui.extensions.makeBrandLabelColorful
import com.justcircleprod.beautifulkpopquizzes.util.areEnglishResourcesUsed

@AndroidEntryPoint
class ResultsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultsBinding
    private val viewModel: ResultsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsBinding.inflate(layoutInflater)

        binding.brandLabel.makeBrandLabelColorful()

        if (areEnglishResourcesUsed()) {
            binding.textQuestionsScoreLabel.visibility = View.GONE
            binding.textQuestionsScore.visibility = View.GONE
            binding.line2.visibility = View.GONE
        }

        enableAnimation()
        binding.backBtn.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        setScoresObserver()

        setContentView(binding.root)
    }

    private fun enableAnimation() {
        binding.cardviewLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
    }

    private fun setScoresObserver() {
        viewModel.scores.observe(this) { scores ->
            binding.noCategoryScore.text = scores[0].score.toString()
            binding.textQuestionsScore.text = scores[1].score.toString()
            binding.imageQuestionsScore.text = scores[2].score.toString()
            binding.videoQuestionsScore.text = scores[3].score.toString()
            binding.audioQuestionsScore.text = scores[4].score.toString()
        }
    }
}