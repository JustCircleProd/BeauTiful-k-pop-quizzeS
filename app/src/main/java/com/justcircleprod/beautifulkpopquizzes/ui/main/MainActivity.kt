package com.justcircleprod.beautifulkpopquizzes.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.justcircleprod.beautifulkpopquizzes.R
import com.justcircleprod.beautifulkpopquizzes.databinding.ActivityMainBinding
import com.justcircleprod.beautifulkpopquizzes.ui.categories.CategoriesActivity
import com.justcircleprod.beautifulkpopquizzes.ui.extensions.makeBrandLabelColorful
import com.justcircleprod.beautifulkpopquizzes.ui.results.ResultsActivity
import com.justcircleprod.beautifulkpopquizzes.ui.settings.SettingsActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_NoActionBar)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.brandLabel.makeBrandLabelColorful()

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