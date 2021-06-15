package ru.mytest.onlybtsfuns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import ru.mytest.onlybtsfuns.data.AppDatabase
import ru.mytest.onlybtsfuns.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countOfQuestions = 6
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "db.db")
            .createFromAsset("database/db.db").allowMainThreadQueries().build()

        binding.randomQuestions.setOnClickListener {
            val countOfTextQuestion = (2..countOfQuestions - 2).random()
            val countOfImageQuestion = countOfQuestions - countOfTextQuestion


//            val textQuestions = TextQuestionStorage.getQuestions(countOfTextQuestion)
//            val imageQuestions = ImageQuestionStorage.getQuestions(countOfImageQuestion)
//
//            val test = (textQuestions.toList() + imageQuestions.toList()).shuffled().toTypedArray()
//
//            val questions =
//                (textQuestions.toList() + imageQuestions.toList()).shuffled().toTypedArray()
//
//            startQuizActivity(questions, countOfQuestions)
        }

        binding.textQuestions.setOnClickListener {
//            val questions = TextQuestionStorage.getQuestions(countOfQuestions)
//            startQuizActivity(questions, countOfQuestions)
        }

        binding.imageQuestions.setOnClickListener {
            val countOfRaw = db.imageQuestionDao().getCount()
            Log.d("Tag", countOfRaw.toString())
            val s: MutableSet<Int> = mutableSetOf()
            while (s.size < countOfQuestions) { s.add((1..countOfRaw).random()) }
            val ids = s.toIntArray()

            val questions = db.imageQuestionDao().loadAllByIds(ids)
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