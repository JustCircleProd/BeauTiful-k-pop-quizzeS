package ru.justcircleprod.onlybtsfuns.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.justcircleprod.onlybtsfuns.data.models.PassedQuestion
import ru.justcircleprod.onlybtsfuns.data.models.PassedQuestionContentType

@Dao
interface PassedQuestionDao {
    @Query("SELECT question_id FROM passed_questions WHERE question_content_type = :questionContentType")
    suspend fun getIdsByContentType(questionContentType: PassedQuestionContentType): List<Int>

    @Insert
    suspend fun insert(passedQuestions: PassedQuestion)

    @Query("DELETE FROM passed_questions")
    suspend fun deleteAll(): Int
}