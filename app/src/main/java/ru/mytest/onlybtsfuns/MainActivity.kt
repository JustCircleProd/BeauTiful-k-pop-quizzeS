package ru.mytest.onlybtsfuns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import ru.mytest.onlybtsfuns.data.AppDatabase
import ru.mytest.onlybtsfuns.data.Score
import ru.mytest.onlybtsfuns.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var countOfQuestions = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "db.db")
            .createFromAsset("database/db.db").allowMainThreadQueries().build()

        binding.randomQuestions.setOnClickListener { showRandomQuestions(db) }
        binding.textQuestions.setOnClickListener { showTextQuestions(db) }
        binding.imageQuestions.setOnClickListener { showImageQuestions(db) }
        binding.yourResults.setOnClickListener { showResults() }
    }

    private fun showRandomQuestions(db: AppDatabase) {
        val countOfTextQuestion = (2..countOfQuestions - 2).random()
        val countOfImageQuestion = countOfQuestions - countOfTextQuestion

        val countOfTextRaw = db.textQuestionDao().getCount()
        val countOfImageRaw = db.imageQuestionDao().getCount()

        val textIds: MutableSet<Int> = mutableSetOf()
        while (textIds.size < countOfTextQuestion) { textIds.add((1..countOfTextRaw).random()) }
        val textQuestions = db.textQuestionDao().loadAllByIds(textIds.toIntArray())

        val imageIds: MutableSet<Int> = mutableSetOf()
        while (imageIds.size < countOfImageQuestion) { imageIds.add((1..countOfImageRaw).random()) }
        val imageQuestions = db.imageQuestionDao().loadAllByIds(imageIds.toIntArray())

        val questions =
            (textQuestions.toList() + imageQuestions.toList()).shuffled().toTypedArray()
        val bestScore = db.scoreDao().findById(1)

        startQuizActivity(questions, bestScore)
    }

    private fun showTextQuestions(db: AppDatabase) {
        val countOfRaw = db.textQuestionDao().getCount()
        val ids: MutableSet<Int> = mutableSetOf()
        while (ids.size < countOfQuestions) { ids.add((1..countOfRaw).random()) }

        val questions = db.textQuestionDao().loadAllByIds(ids.toIntArray())
        val bestScore = db.scoreDao().findById(2)
        startQuizActivity(questions, bestScore)
    }

    private fun showImageQuestions(db: AppDatabase) {
        val countOfRaw = db.imageQuestionDao().getCount()
        val ids: MutableSet<Int> = mutableSetOf()
        while (ids.size < countOfQuestions) { ids.add((1..countOfRaw).random()) }

        val questions = db.imageQuestionDao().loadAllByIds(ids.toIntArray())
        val bestScore = db.scoreDao().findById(3)
        startQuizActivity(questions, bestScore)
    }

    private fun startQuizActivity(questions: Array<*>, bestScore: Score) {
        val intent = Intent(this, QuizActivity::class.java)
        intent.putExtra("countOfQuestions", countOfQuestions)
        intent.putExtra("questions", questions)
        intent.putExtra("bestScore", bestScore)
        startActivity(intent)
    }

    private fun showResults() {
        val intent = Intent(this, ResultsActivity::class.java)
        startActivity(intent)
    }
}