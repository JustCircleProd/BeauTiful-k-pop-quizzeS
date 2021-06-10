package ru.mytest.onlybtsfuns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.mytest.onlybtsfuns.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countOfQuestions = intent.getIntExtra("countOfQuestions", 6)
        val correctAnswers = intent.getIntExtra("result", 0)

        binding.result.text = "$correctAnswers/$countOfQuestions"

        binding.toCategories.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}