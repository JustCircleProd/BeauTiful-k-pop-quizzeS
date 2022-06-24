package ru.justcircleprod.onlybtsfuns.ui.settings

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.justcircleprod.onlybtsfuns.data.room.constants.DifficultyState
import ru.justcircleprod.onlybtsfuns.data.room.constants.QuestionsRepetitionState
import ru.justcircleprod.onlybtsfuns.databinding.ActivitySettingsBinding

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)

        setDifficultyObserver()
        setOnDifficultyChipsClickListeners()

        setQuestionsRepetitionObserver()
        makeResetPassedQuestionsClickable()

        binding.toMenuBtn.setOnClickListener { super.onBackPressed() }

        setContentView(binding.root)
    }

    private fun setDifficultyObserver() {
        viewModel.difficultyState.observe(this) { state ->
            when (state) {
                DifficultyState.RANDOM -> {
                    binding.chipRandom.isChecked = true
                }
                DifficultyState.USUAL -> {
                    binding.chipUsual.isChecked = true
                }
                DifficultyState.DIFFICULT -> {
                    binding.chipDifficult.isChecked = true
                }
                else -> {
                    binding.chipRandom.isChecked = true
                }
            }
        }
    }

    private fun setOnDifficultyChipsClickListeners() {
        binding.chipRandom.setOnClickListener {
            viewModel.updateDifficulty(
                this,
                DifficultyState.RANDOM
            )
        }
        binding.chipUsual.setOnClickListener {
            viewModel.updateDifficulty(
                this,
                DifficultyState.USUAL
            )
        }
        binding.chipDifficult.setOnClickListener {
            viewModel.updateDifficulty(
                this,
                DifficultyState.DIFFICULT
            )
        }
    }

    private fun setQuestionsRepetitionObserver() {
        viewModel.questionsRepetitionState.observe(this) { state ->
            binding.questionsRepetitionChip.setOnCheckedChangeListener { _, _ -> }
            when (state) {
                QuestionsRepetitionState.NO_REPETITION -> {
                    binding.questionsRepetitionChip.isChecked = true
                }
                else -> {
                    binding.questionsRepetitionChip.isChecked = false
                }
            }

            setOnQuestionRepetitionCheckedChangeListener()
        }
    }


    private fun setOnQuestionRepetitionCheckedChangeListener() {
        binding.questionsRepetitionChip.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.updateQuestionsRepetition(this, QuestionsRepetitionState.NO_REPETITION)
            } else {
                viewModel.updateQuestionsRepetition(this, QuestionsRepetitionState.REPETITION)
            }
        }
    }

    private fun makeResetPassedQuestionsClickable() {
        val spannableString = SpannableString(binding.resetPassedQuestionsHint.text)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                ResetPassedQuestionsConfirmationDialog().show(supportFragmentManager, null)
            }
        }

        spannableString.setSpan(
            clickableSpan,
            0,
            binding.resetPassedQuestionsHint.text.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.resetPassedQuestionsHint.text = spannableString
        binding.resetPassedQuestionsHint.movementMethod = LinkMovementMethod.getInstance()
    }
}