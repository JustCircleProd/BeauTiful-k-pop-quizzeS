package ru.justcircleprod.onlybtsfuns.ui.quiz

import android.animation.LayoutTransition
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.common.AdRequest
import dagger.hilt.android.AndroidEntryPoint
import ru.justcircleprod.onlybtsfuns.R
import ru.justcircleprod.onlybtsfuns.appConstants.QuizCategory
import ru.justcircleprod.onlybtsfuns.data.models.MediaQuestion
import ru.justcircleprod.onlybtsfuns.data.models.MediaQuestionType
import ru.justcircleprod.onlybtsfuns.data.models.TextQuestion
import ru.justcircleprod.onlybtsfuns.databinding.ActivityQuizBinding
import ru.justcircleprod.onlybtsfuns.ui.quizResult.QuizResultActivity

@AndroidEntryPoint
class QuizActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityQuizBinding
    private val viewModel: QuizViewModel by viewModels()

    private lateinit var correctAnswerPlayer: MediaPlayer
    private lateinit var incorrectAnswerPlayer: MediaPlayer
    private var musicPlayer: MediaPlayer? = null

    private var positionOfVideoPlayer = 0

    companion object {
        const val CATEGORY_ID_ARGUMENT_NAME = "categoryId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLoadingText()
        enableAnimation()
        initAd()
        initPlayers()
        setOnOptionsClickListeners()
        setLoadingObserver()
    }

    override fun onStart() {
        super.onStart()
        resumePlayers()
    }

    override fun onPause() {
        super.onPause()
        pausePlayers()
    }

    private fun resumePlayers() {
        when {
            binding.videoQuestionLayout.visibility == View.VISIBLE -> {
                binding.videoQuestion.seekTo(positionOfVideoPlayer)
                binding.videoQuestion.start()
            }
            // if the application was minimized when downloading the video
            viewModel.question.value is MediaQuestion &&
                    (viewModel.question.value as MediaQuestion).type == MediaQuestionType.VIDEO_QUESTION &&
                    binding.videoQuestionLayout.visibility == View.INVISIBLE -> {
                setVideoQuestionData(viewModel.question.value!! as MediaQuestion)
            }
            musicPlayer != null && binding.audioQuestion.visibility == View.VISIBLE -> {
                musicPlayer?.start()
            }
            // if the application was minimized when downloading the audio
            viewModel.question.value is MediaQuestion &&
                    (viewModel.question.value as MediaQuestion).type == MediaQuestionType.AUDIO_QUESTION &&
                    binding.audioQuestion.visibility == View.INVISIBLE -> {
                setAudioQuestionData(viewModel.question.value!! as MediaQuestion)
            }
        }
    }

    private fun pausePlayers() {
        when {
            binding.videoQuestionLayout.visibility == View.VISIBLE -> {
                binding.videoQuestion.pause()
                positionOfVideoPlayer = binding.videoQuestion.currentPosition
            }
            // if the application was minimized when downloading the audio
            viewModel.question.value is MediaQuestion &&
                    (viewModel.question.value as MediaQuestion).type == MediaQuestionType.AUDIO_QUESTION &&
                    binding.audioQuestion.visibility == View.INVISIBLE -> {
                musicPlayer = null
            }
            musicPlayer != null && binding.audioQuestion.visibility == View.VISIBLE -> {
                musicPlayer?.pause()
            }
        }
    }

    override fun onClick(view: View?) {
        if (view is MaterialButton) {
            disableButtons()
            stopAndResetPlayers()
            if (viewModel.checkAnswer(view.text.toString())) {
                onRightAnswer(view)
            } else {
                onWrongAnswer(view)
            }
        }
    }

    private fun setLoadingText() {
        if (viewModel.categoryId == QuizCategory.TEXT_CATEGORY.value) {
            binding.loadHint.text = getString(R.string.tv_loading_quiz_text)
        } else {
            binding.loadHint.text = getString(R.string.tv_loading_quiz_internet_hint_text)
        }
    }

    private fun enableAnimation() {
        binding.contentLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
    }

    private fun initAd() {
        binding.bannerAdView.setAdUnitId("R-M-DEMO-320x50-app_install")
        binding.bannerAdView.setAdSize(AdSize.BANNER_320x50)
        val adRequest = AdRequest.Builder().build()
        binding.bannerAdView.loadAd(adRequest)
    }

    private fun initPlayers() {
        correctAnswerPlayer = MediaPlayer.create(this, R.raw.correct_answer)
        incorrectAnswerPlayer = MediaPlayer.create(this, R.raw.incorrect_answer)
    }

    private fun setOnOptionsClickListeners() {
        binding.firstOption.setOnClickListener(this)
        binding.secondOption.setOnClickListener(this)
        binding.thirdOption.setOnClickListener(this)
        binding.fourthOption.setOnClickListener(this)
    }

    private fun setLoadingObserver() {
        viewModel.isLoading.observe(this) { isLoading ->
            if (viewModel.isFirstStart) {
                viewModel.isFirstStart = false
            } else {
                if (isLoading.all { !it }) {
                    if (viewModel.questions.size == viewModel.countOfQuestions) {
                        viewModel.updateQuestion()

                        setScoreObserver()
                        setQuestionsObserver()

                        binding.loadLayout.visibility = View.GONE
                        binding.contentLayout.visibility = View.VISIBLE
                    } else {
                        // if the download was successful, but because of the "no repeat" option,
                        // there are no questions left for the user
                        binding.loadLayout.visibility = View.GONE
                        setOutOfQuestionsLayout()
                    }
                }
            }
        }
    }

    private fun setOutOfQuestionsLayout() {
        binding.toCategories.setOnClickListener { super.onBackPressed() }
        binding.outOfQuestionsLayout.visibility = View.VISIBLE
    }

    private fun setScoreObserver() {
        viewModel.score.observe(this) {
            binding.score.text = getString(R.string.tv_score_label, it)
        }
    }

    private fun setQuestionsObserver() {
        viewModel.question.observe(this) { question ->
            if (question == null) {
                startResultActivity()
                return@observe
            }

            hidePreviousQuestion()
            updateViews()

            binding.points.text = getString(R.string.tv_points_label, question.points)

            if (question is TextQuestion) {
                setTextQuestionData(question)
                enableButtons()
            } else {
                binding.contentLoadingProgress.visibility = View.VISIBLE

                when ((question as MediaQuestion).type) {
                    MediaQuestionType.IMAGE_QUESTION -> {
                        setImageQuestionData(question)
                    }
                    MediaQuestionType.VIDEO_QUESTION -> {
                        setVideoQuestionData(question)
                    }
                    MediaQuestionType.AUDIO_QUESTION -> {
                        setAudioQuestionData(question)
                    }
                }
            }

            binding.firstOption.text = question.firstOption
            binding.secondOption.text = question.secondOption
            binding.thirdOption.text = question.thirdOption
            binding.fourthOption.text = question.fourthOption
        }
    }

    private fun hidePreviousQuestion() {
        binding.textQuestion.visibility = View.INVISIBLE
        binding.imageQuestion.visibility = View.INVISIBLE
        binding.audioQuestion.visibility = View.INVISIBLE
        binding.videoQuestionLayout.visibility = View.INVISIBLE
    }

    private fun updateViews() {
        binding.progress.progress++

        binding.firstOption.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        binding.secondOption.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        binding.thirdOption.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        binding.fourthOption.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
    }

    private fun setTextQuestionData(question: TextQuestion) {
        binding.textQuestion.visibility = View.VISIBLE
        binding.textQuestion.text = question.question
    }

    private fun setImageQuestionData(question: MediaQuestion) {
        binding.imageQuestion.visibility = View.INVISIBLE

        Picasso.get().load(question.mediaUrl).into(binding.imageQuestion, object : Callback {
            override fun onSuccess() {
                binding.contentLoadingProgress.visibility = View.GONE
                binding.imageQuestion.visibility = View.VISIBLE
                enableButtons()
            }

            override fun onError(e: Exception?) {}
        })
    }

    private fun setVideoQuestionData(question: MediaQuestion) {
        binding.videoQuestion.setOnPreparedListener {
            it.setOnInfoListener { _, info, _ ->
                if (info == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                    binding.contentLoadingProgress.visibility = View.GONE
                    binding.videoQuestionLayout.visibility = View.VISIBLE
                    enableButtons()

                    return@setOnInfoListener true
                }
                return@setOnInfoListener false
            }
        }
        binding.videoQuestion.setVideoURI(Uri.parse(question.mediaUrl))
        binding.videoQuestion.start()

    }

    private fun setAudioQuestionData(question: MediaQuestion) {
        musicPlayer = MediaPlayer()

        musicPlayer?.setOnPreparedListener {
            if (musicPlayer != null) {
                binding.contentLoadingProgress.visibility = View.GONE
                binding.audioQuestion.visibility = View.VISIBLE
                musicPlayer!!.start()
                enableButtons()
            }
        }

        musicPlayer?.setDataSource(question.mediaUrl)
        musicPlayer?.prepareAsync()
    }

    private fun enableButtons() {
        binding.firstOption.isEnabled = true
        binding.secondOption.isEnabled = true
        binding.thirdOption.isEnabled = true
        binding.fourthOption.isEnabled = true
    }

    private fun disableButtons() {
        binding.firstOption.isEnabled = false
        binding.secondOption.isEnabled = false
        binding.thirdOption.isEnabled = false
        binding.fourthOption.isEnabled = false
    }

    private fun stopAndResetPlayers() {
        when {
            binding.videoQuestionLayout.visibility == View.VISIBLE -> {
                binding.videoQuestion.stopPlayback()
                positionOfVideoPlayer = 0
                binding.videoQuestion.setOnPreparedListener(null)
            }
            binding.audioQuestion.visibility == View.VISIBLE -> {
                musicPlayer?.stop()
                musicPlayer?.release()
                musicPlayer?.setOnPreparedListener(null)
                musicPlayer = null
            }
        }
    }

    private fun onRightAnswer(btn: MaterialButton) {
        correctAnswerPlayer.start()
        btn.setBackgroundColor(ContextCompat.getColor(this, R.color.correct_answer_color))

        val timer = object : CountDownTimer(2000, 2000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                viewModel.updateQuestion()
            }
        }
        timer.start()
    }

    private fun onWrongAnswer(btn: MaterialButton) {
        incorrectAnswerPlayer.start()
        btn.setBackgroundColor(ContextCompat.getColor(this, R.color.incorrect_answer_color))

        val timer = object : CountDownTimer(2000, 250) {
            override fun onTick(millisUntilFinished: Long) {
                if (millisUntilFinished <= 1750) {
                    showRightAnswer(viewModel.question.value!!.answerNum)
                }
            }

            override fun onFinish() {
                viewModel.updateQuestion()
            }
        }
        timer.start()
    }

    private fun showRightAnswer(answerNum: Int) {
        when (answerNum) {
            1 ->
                binding.firstOption.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.correct_answer_color
                    )
                )
            2 ->
                binding.secondOption.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.correct_answer_color
                    )
                )
            3 ->
                binding.thirdOption.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.correct_answer_color
                    )
                )
            4 ->
                binding.fourthOption.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.correct_answer_color
                    )
                )
        }
    }

    private fun startResultActivity() {
        val intent = Intent(this, QuizResultActivity::class.java)
        intent.putExtra(QuizResultActivity.CATEGORY_ID_ARGUMENT_NAME, viewModel.categoryId)
        intent.putExtra(QuizResultActivity.SCORE_ARGUMENT_NAME, viewModel.score.value)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.bannerAdView.destroy()
    }
}