package ru.mytest.onlybtsfuns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import ru.mytest.onlybtsfuns.dataClasses.TextQuestionStorage
import ru.mytest.onlybtsfuns.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.randomQuestions.setOnClickListener {

        }

        binding.textQuestions.setOnClickListener {
            val questions = TextQuestionStorage.getTextQuestions(2)
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("questions", questions)
            startActivity(intent)
        }

        binding.imageQuestions.setOnClickListener {

        }
    }
}