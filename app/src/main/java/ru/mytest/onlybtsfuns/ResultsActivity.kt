package ru.mytest.onlybtsfuns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import ru.mytest.onlybtsfuns.data.AppRepository
import ru.mytest.onlybtsfuns.data.Score
import ru.mytest.onlybtsfuns.databinding.ActivityResultsBinding
import ru.mytest.onlybtsfuns.viewModels.ResultsViewModel
import ru.mytest.onlybtsfuns.viewModels.ResultsViewModelFactory

class ResultsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultsBinding
    private lateinit var viewModel: ResultsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = AppRepository(this)
        val factory = ResultsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ResultsViewModel::class.java)

        updateViews(viewModel.getScores())

        binding.toCategories.setOnClickListener { super.onBackPressed() }
    }

    private fun updateViews(scores: Array<Score>) {
        binding.noCategoryScore.text = scores[0].score.toString()
        binding.textQuestionsScore.text = scores[1].score.toString()
        binding.imageQuestionsScore.text = scores[2].score.toString()
    }
}