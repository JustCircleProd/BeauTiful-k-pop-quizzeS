package ru.justcircleprod.onlybtsfuns.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.justcircleprod.onlybtsfuns.R
import ru.justcircleprod.onlybtsfuns.databinding.ActivityMainBinding
import ru.justcircleprod.onlybtsfuns.ui.categories.CategoriesActivity
import ru.justcircleprod.onlybtsfuns.ui.results.ResultsActivity
import ru.justcircleprod.onlybtsfuns.ui.settings.SettingsActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_NoActionBar)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setOnClickListeners()

        setContentView(binding.root)
    }

    private fun setOnClickListeners() {
        binding.startQuiz.setOnClickListener { startCategoryActivity() }
        binding.settings.setOnClickListener { startSettingActivity() }
        binding.results.setOnClickListener { startResultsActivity() }
    }

    private fun startCategoryActivity() {
        val intent = Intent(this, CategoriesActivity::class.java)
        startActivity(intent)
    }

    private fun startSettingActivity() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun startResultsActivity() {
        val intent = Intent(this, ResultsActivity::class.java)
        startActivity(intent)
    }
}