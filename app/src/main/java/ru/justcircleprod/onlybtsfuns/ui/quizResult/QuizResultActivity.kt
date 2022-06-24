package ru.justcircleprod.onlybtsfuns.ui.quizResult

import android.animation.LayoutTransition
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.interstitial.InterstitialAd
import com.yandex.mobile.ads.interstitial.InterstitialAdEventListener
import dagger.hilt.android.AndroidEntryPoint
import ru.justcircleprod.onlybtsfuns.OnlyBTSFans
import ru.justcircleprod.onlybtsfuns.R
import ru.justcircleprod.onlybtsfuns.databinding.ActivityQuizResultBinding

@AndroidEntryPoint
class QuizResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizResultBinding
    private val viewModel: QuizResultViewModel by viewModels()

    private lateinit var interstitialAd: InterstitialAd

    private lateinit var resultPlayer: MediaPlayer

    companion object {
        const val CATEGORY_ID_ARGUMENT_NAME = "categoryId"
        const val SCORE_ARGUMENT_NAME = "score"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizResultBinding.inflate(layoutInflater)

        enableAnimations()

        binding.toCategories.setOnClickListener { super.onBackPressed() }

        setLoadingObserver()
        workWithInterstitialAd()
        setScoresObservers()

        setContentView(binding.root)
    }

    private fun enableAnimations() {
        binding.contentLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
    }

    private fun setLoadingObserver() {
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading.all { !it }) {
                showResult()
                binding.loadLayout.visibility = View.GONE
                binding.contentLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun workWithInterstitialAd() {
        // if an ad has already been shown
        if (!viewModel.isLoading.value!![1]) {
            return
        }

        (application as OnlyBTSFans).onTestPassed()

        val showAd =
            (application as OnlyBTSFans).passedTestNum >= (application as OnlyBTSFans).passedTestNumForShowingAd
        if (!showAd) {
            viewModel.isLoading.value = listOf(viewModel.isLoading.value!![0], false)
            return
        }

        interstitialAd = InterstitialAd(this)

        val adUnitId =
            String(Base64.decode("", Base64.DEFAULT), Charsets.UTF_8)
        interstitialAd.setAdUnitId(adUnitId)

        val adRequest = AdRequest.Builder().build()

        interstitialAd.setInterstitialAdEventListener(object : InterstitialAdEventListener {
            override fun onAdLoaded() {
                interstitialAd.show()
            }

            override fun onAdFailedToLoad(p0: AdRequestError) {
                viewModel.isLoading.value = listOf(viewModel.isLoading.value!![0], false)
            }

            override fun onAdShown() {}

            override fun onAdDismissed() {
                viewModel.isLoading.value = listOf(viewModel.isLoading.value!![0], false)
            }

            override fun onAdClicked() {}

            override fun onLeftApplication() {}

            override fun onReturnedToApplication() {}

            override fun onImpression(p0: ImpressionData?) {}
        })

        interstitialAd.loadAd(adRequest)
    }

    private fun setScoresObservers() {
        viewModel.currentScore.observe(this) {
            binding.score.text = it.toString()
        }
        viewModel.lastScore.observe(this) {
            binding.bestScore.text = it.toString()
        }
    }

    private fun showResult() {
        when {
            viewModel.currentScore.value!! > viewModel.lastScore.value!! -> {
                binding.bestScoreLabel.visibility = View.GONE
                binding.bestScore.visibility = View.GONE

                binding.resultText.text = resources.getString(R.string.text_for_best_result)
                binding.resultImage.setImageResource(R.drawable.best_image_result)
                playResultSound("best_result")
            }
            viewModel.currentScore.value!! < 1000 -> {
                binding.resultText.text = resources.getString(R.string.text_for_bad_result)
                binding.resultImage.setImageResource(R.drawable.bad_image_result)
                playResultSound("bad_result")
            }
            else -> {
                binding.resultText.text = resources.getString(R.string.text_for_good_result)
                binding.resultImage.setImageResource(R.drawable.good_image_result)
                playResultSound("good_result")
            }
        }
    }

    private fun playResultSound(audioName: String) {
        resultPlayer = MediaPlayer()

        resultPlayer.setOnPreparedListener {
            resultPlayer.start()
        }

        resultPlayer.setDataSource(
            this,
            Uri.parse("android.resource://$packageName/raw/$audioName")
        )
        resultPlayer.prepareAsync()
    }

    override fun onDestroy() {
        super.onDestroy()

        if (::interstitialAd.isInitialized) {
            interstitialAd.destroy()
        }
    }
}