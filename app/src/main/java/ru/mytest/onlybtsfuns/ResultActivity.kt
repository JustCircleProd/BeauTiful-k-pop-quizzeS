package ru.mytest.onlybtsfuns

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.mytest.onlybtsfuns.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countOfQuestions = intent.getIntExtra("countOfQuestions", 6)
        val correctAnswers = intent.getIntExtra("result", 0)

        binding.result.text = "$correctAnswers/$countOfQuestions"

        setImage(correctAnswers)

        binding.toCategories.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setImage(correctAnswers: Int) {
        when {
            correctAnswers <= 2 -> {
                binding.resultText.text = resources.getString(R.string.text_for_bad_result)
                binding.resultImage.setImageResource(R.drawable.bad_image_result)
                MediaPlayer.create(this, R.raw.bad_result).start()
            }
            correctAnswers <= 4 -> {
                binding.resultText.text = resources.getString(R.string.text_for_good_result)
                binding.resultImage.setImageResource(R.drawable.good_image_result)
                MediaPlayer.create(this, R.raw.good_result).start()
            }
            else -> {
                binding.resultText.text = resources.getString(R.string.text_for_best_result)
                binding.resultImage.setImageResource(R.drawable.best_image_result)
                MediaPlayer.create(this, R.raw.best_result).start()
            }
        }
    }
}