package ru.mytest.onlybtsfuns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import ru.mytest.onlybtsfuns.data.AppDatabase
import ru.mytest.onlybtsfuns.databinding.ActivityResultsBinding

class ResultsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "db.db")
            .allowMainThreadQueries().build()
        val scores = db.scoreDao().getAll()

        binding.noCategoryScore.text = scores[0].score.toString()
        binding.textQuestionsScore.text = scores[1].score.toString()
        binding.imageQuestionsScore.text = scores[2].score.toString()

        binding.toCategories.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}