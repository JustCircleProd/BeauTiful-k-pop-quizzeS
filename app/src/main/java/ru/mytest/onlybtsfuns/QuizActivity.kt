package ru.mytest.onlybtsfuns

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Parcelable
import android.util.Log
import android.view.View
import com.google.android.material.button.MaterialButton
import org.w3c.dom.Text
import ru.mytest.onlybtsfuns.dataClasses.ImageQuestion
import ru.mytest.onlybtsfuns.dataClasses.TextQuestion
import ru.mytest.onlybtsfuns.databinding.ActivityQuizBinding
import kotlin.reflect.typeOf

class QuizActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var questions: Array<*>
    private var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        questions = intent.getParcelableArrayExtra("questions") as Array<*>
        updateQuestion()
    }

    override fun onClick(v: View?) {
        if (v is MaterialButton) {

        }
    }

    private fun updateQuestion() {
        val currentQuestion = binding.progress.progress - 1

        when (questions[currentQuestion]) {
            is TextQuestion -> {
                val question = questions[currentQuestion] as TextQuestion

                binding.textQuestion.text = question.question
                binding.textQuestion.visibility = View.VISIBLE

                updateOptions(question.options)
            }
            is ImageQuestion -> {
                val question = questions[currentQuestion] as ImageQuestion

            }
        }

    }

    private fun updateOptions(options: Array<String>) {
        binding.firstOption.text = options[0]
        binding.secondOption.text = options[1]
        binding.thirdOption.text = options[2]
        binding.fourthOption.text = options[3]
    }

}