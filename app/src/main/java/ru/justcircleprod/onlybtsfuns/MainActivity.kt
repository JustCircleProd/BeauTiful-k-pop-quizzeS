package ru.justcircleprod.onlybtsfuns

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.justcircleprod.onlybtsfuns.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_NoActionBar)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startQuiz.setOnClickListener { startCategoryActivity() }
        binding.settings.setOnClickListener { startSettingActivity() }
        binding.yourResults.setOnClickListener { startResultsActivity() }
    }

    private fun startCategoryActivity() {
        val intent = Intent(this, CategoryActivity::class.java)
        startActivity(intent)
    }

    private fun startSettingActivity() {
        val intent = Intent(this, SettingActivity::class.java)
        startActivity(intent)
    }

    private fun startResultsActivity() {
        val intent = Intent(this, ResultsActivity::class.java)
        startActivity(intent)
    }
}