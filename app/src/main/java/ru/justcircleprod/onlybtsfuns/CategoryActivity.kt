package ru.justcircleprod.onlybtsfuns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import ru.justcircleprod.onlybtsfuns.data.AppRepository
import ru.justcircleprod.onlybtsfuns.databinding.ActivityCategoryBinding
import ru.justcircleprod.onlybtsfuns.databinding.ActivityMainBinding
import ru.justcircleprod.onlybtsfuns.viewModels.*

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    private lateinit var viewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appRepository = AppRepository(this)
        val factory = CategoryViewModelFactory(appRepository)
        viewModel = ViewModelProvider(this, factory)[CategoryViewModel::class.java]

        binding.randomQuestions.setOnClickListener { startQuizActivity(1) }
        binding.textQuestions.setOnClickListener { startQuizActivity(2) }
        binding.imageQuestions.setOnClickListener { startQuizActivity(3) }
        binding.videoQuestions.setOnClickListener { onVideoCategoryPressed(4) }
        binding.audioQuestions.setOnClickListener { startQuizActivity(5) }
    }

    private fun onVideoCategoryPressed(categoryId: Int) {
        when (viewModel.difficultyState) {
            0, 2 -> startQuizActivity(categoryId)
            1 ->  {
                Toast.makeText(
                        this,
                        resources.getString(R.string.difficulty_warning),
                        Toast.LENGTH_LONG
                    ).show()
            }
        }
    }

    private fun startQuizActivity(categoryId: Int) {
        val intent = Intent(this, QuizActivity::class.java)
        intent.putExtra("categoryId", categoryId)
        startActivity(intent)
    }
}