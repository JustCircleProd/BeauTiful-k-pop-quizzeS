package ru.justcircleprod.onlybtsfuns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.justcircleprod.onlybtsfuns.databinding.ActivityCategoryBinding
import ru.justcircleprod.onlybtsfuns.databinding.ActivityMainBinding

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.randomQuestions.setOnClickListener { startQuizActivity(1) }
        binding.textQuestions.setOnClickListener { startQuizActivity(2) }
        binding.imageQuestions.setOnClickListener { startQuizActivity(3) }
        binding.videoQuestions.setOnClickListener { startQuizActivity(4) }
        binding.audioQuestions.setOnClickListener { startQuizActivity(5) }
    }

    private fun startQuizActivity(categoryId: Int) {
        val intent = Intent(this, QuizActivity::class.java)
        intent.putExtra("categoryId", categoryId)
        startActivity(intent)
    }
}