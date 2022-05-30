package ru.justcircleprod.onlybtsfuns.data

import com.google.firebase.firestore.FirebaseFirestore
import ru.justcircleprod.onlybtsfuns.data.models.PassedQuestion
import ru.justcircleprod.onlybtsfuns.data.models.PassedQuestionContentType
import ru.justcircleprod.onlybtsfuns.data.models.Setting
import ru.justcircleprod.onlybtsfuns.data.models.TextQuestion
import ru.justcircleprod.onlybtsfuns.data.room.AppDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val db: AppDatabase,
    private val firestoreDb: FirebaseFirestore
) {
    suspend fun getRandomTextQuestions(
        countOfQuestions: Int,
        lowerPoints: Int,
        upperPoints: Int
    ): Array<TextQuestion> {
        val ids =
            db.textQuestionDao().getIds(lowerPoints, upperPoints).shuffled().take(countOfQuestions)

        return db.textQuestionDao().getByIds(ids.toIntArray())
    }

    fun getImageQuestionsTask() =
        firestoreDb.collection("questions").document("image_questions").get()

    fun getVideoQuestionsTask() =
        firestoreDb.collection("questions").document("video_questions").get()

    fun getAudioQuestionsTask() =
        firestoreDb.collection("questions").document("audio_questions").get()

    suspend fun getPassedQuestionsId(questionContentType: PassedQuestionContentType) =
        db.passedQuestionDao().getIdsByContentType(questionContentType)

    suspend fun insertPassedQuestion(passedQuestion: PassedQuestion) {
        db.passedQuestionDao().insert(passedQuestion)
    }

    suspend fun getSetting(id: Int) = db.settingDao().getById(id)

    suspend fun updateSetting(setting: Setting) {
        db.settingDao().update(setting)
    }

    fun getScores() = db.scoreDao().getAll()

    suspend fun getScore(id: Int) = db.scoreDao().getById(id)

    suspend fun updateScore(id: Int, score: Int) {
        db.scoreDao().update(id, score)
    }
}