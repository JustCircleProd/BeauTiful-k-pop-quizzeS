package ru.justcircleprod.onlybtsfuns

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import ru.justcircleprod.onlybtsfuns.data.AppRepository
import ru.justcircleprod.onlybtsfuns.databinding.ActivitySettingBinding
import ru.justcircleprod.onlybtsfuns.viewModels.SettingViewModel
import ru.justcircleprod.onlybtsfuns.viewModels.SettingViewModelFactory

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    private lateinit var viewModel: SettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appRepository = AppRepository(this)
        val factory = SettingViewModelFactory(appRepository)
        viewModel = ViewModelProvider(this, factory)[SettingViewModel::class.java]

        setObserver()

        binding.chipRandom.setOnClickListener { updateDifficulty(0) }
        binding.chipUsual.setOnClickListener { updateDifficulty(1) }
        binding.chipDifficult.setOnClickListener { updateDifficulty(2) }

        binding.toMenu.setOnClickListener { super.onBackPressed() }
    }

    private fun setObserver() {
        viewModel.difficultyState.observe(this) {
            when (viewModel.difficultyState.value) {
                0 -> binding.chipRandom.isChecked = true
                1 -> binding.chipUsual.isChecked = true
                2 -> binding.chipDifficult.isChecked = true
            }
        }
    }

    private fun updateDifficulty(state: Int) {
        viewModel.updateDifficulty(state)
        Toast.makeText(this, resources.getString(R.string.set_difficulty), Toast.LENGTH_SHORT)
            .show()
    }
}