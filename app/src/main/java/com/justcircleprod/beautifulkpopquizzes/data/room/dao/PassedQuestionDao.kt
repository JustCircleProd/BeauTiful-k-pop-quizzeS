package com.justcircleprod.beautifulkpopquizzes.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.justcircleprod.beautifulkpopquizzes.data.models.PassedQuestion
import com.justcircleprod.beautifulkpopquizzes.data.models.QuestionContentType

@Dao
interface PassedQuestionDao {
    @Query("SELECT question_id FROM passed_questions WHERE question_content_type = :questionContentType")
    suspend fun getIdsByContentType(questionContentType: QuestionContentType): List<Int>

    @Insert
    suspend fun insert(passedQuestions: PassedQuestion)

    @Query("DELETE FROM passed_questions")
    suspend fun deleteAll(): Int
}