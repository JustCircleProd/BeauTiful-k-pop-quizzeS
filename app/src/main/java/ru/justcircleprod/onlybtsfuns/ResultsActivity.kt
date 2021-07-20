package ru.justcircleprod.onlybtsfuns

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import ru.justcircleprod.onlybtsfuns.data.AppRepository
import ru.justcircleprod.onlybtsfuns.databinding.ActivityResultsBinding
import ru.justcircleprod.onlybtsfuns.viewModels.ResultsViewModel
import ru.justcircleprod.onlybtsfuns.viewModels.ResultsViewModelFactory

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

        setScoresObservers()
        binding.toCategories.setOnClickListener { super.onBackPressed() }
    }

    private fun setScoresObservers() {
        viewModel.noCategoryScore.observe(this, {
            binding.noCategoryScore.text = it.toString()
        })
        viewModel.textQuestionsScore.observe(this, {
            binding.textQuestionsScore.text = it.toString()
        })
        viewModel.imageQuestionsScore.observe(this, {
            binding.imageQuestionsScore.text = it.toString()
        })
    }
}