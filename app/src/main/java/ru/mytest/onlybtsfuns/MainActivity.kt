package ru.mytest.onlybtsfuns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import ru.mytest.onlybtsfuns.dataClasses.ImageQuestionStorage
import ru.mytest.onlybtsfuns.dataClasses.TextQuestionStorage
import ru.mytest.onlybtsfuns.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countOfQuestions = 6

        binding.randomQuestions.setOnClickListener {
            val countOfTextQuestion = (0..countOfQuestions).random()
            val countOfImageQuestion = countOfQuestions - countOfTextQuestion

            val textQuestions = TextQuestionStorage.getQuestions(countOfTextQuestion)
            val imageQuestions = ImageQuestionStorage.getQuestions(countOfImageQuestion)

            val questions =
                (textQuestions.toList() + imageQuestions.toList()).shuffled() as Array<*>
            startQuizActivity(questions, countOfQuestions)
        }

        binding.textQuestions.setOnClickListener {
            val questions = TextQuestionStorage.getQuestions(countOfQuestions)
            startQuizActivity(questions, countOfQuestions)
        }

        binding.imageQuestions.setOnClickListener {
            val questions = ImageQuestionStorage.getQuestions(1)
            startQuizActivity(questions, countOfQuestions)
        }
    }

    private fun startQuizActivity(questions: Array<*>, countOfQuestions: Int) {
        val intent = Intent(this, QuizActivity::class.java)
        intent.putExtra("questions", questions)
        intent.putExtra("countOfQuestions", countOfQuestions)
        startActivity(intent)
        finish()
    }
}