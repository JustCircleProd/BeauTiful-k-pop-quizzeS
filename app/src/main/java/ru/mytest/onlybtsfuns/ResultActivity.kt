package ru.mytest.onlybtsfuns

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import ru.mytest.onlybtsfuns.data.AppDatabase
import ru.mytest.onlybtsfuns.data.Score
import ru.mytest.onlybtsfuns.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val score = intent.getIntExtra("score", 0)
        val bestScore = intent.getParcelableExtra<Score>("bestScore")!!

        updateViews(score, bestScore)
        checkScore(score, bestScore)

        binding.toCategories.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun updateViews(score: Int, bestScore: Score) {
        binding.score.text = score.toString()
        binding.bestScore.text = bestScore.score.toString()
    }

    private fun checkScore(score: Int, bestScore: Score) {
        when {
            score > bestScore.score -> {
                updateScore(score, bestScore)
                binding.resultText.text = resources.getString(R.string.text_for_best_result)
                binding.resultImage.setImageResource(R.drawable.best_image_result)
                MediaPlayer.create(this, R.raw.best_result).start()
            }
            score < 1000 -> {
                binding.resultText.text = resources.getString(R.string.text_for_bad_result)
                binding.resultImage.setImageResource(R.drawable.bad_image_result)
                MediaPlayer.create(this, R.raw.bad_result).start()
            }
            else -> {
                binding.resultText.text = resources.getString(R.string.text_for_good_result)
                binding.resultImage.setImageResource(R.drawable.good_image_result)
                MediaPlayer.create(this, R.raw.good_result).start()
            }
        }
    }

    private fun updateScore(score: Int, bestScore: Score) {
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "db.db")
            .allowMainThreadQueries().build()
        db.scoreDao().update(bestScore.id, score)
    }
}