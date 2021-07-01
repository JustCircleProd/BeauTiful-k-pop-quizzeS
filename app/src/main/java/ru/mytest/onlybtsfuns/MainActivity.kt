package ru.mytest.onlybtsfuns

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mytest.onlybtsfuns.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.randomQuestions.setOnClickListener { startQuizActivity(1) }
        binding.textQuestions.setOnClickListener { startQuizActivity(2) }
        binding.imageQuestions.setOnClickListener { startQuizActivity(3) }
        binding.yourResults.setOnClickListener { showResults() }
    }

    private fun startQuizActivity(categoryId: Int) {
        val intent = Intent(this, QuizActivity::class.java)
        intent.putExtra("categoryId", categoryId)
        startActivity(intent)
    }

    private fun showResults() {
        val intent = Intent(this, ResultsActivity::class.java)
        startActivity(intent)
    }
}