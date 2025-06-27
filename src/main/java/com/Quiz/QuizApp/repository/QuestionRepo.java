package com.Quiz.QuizApp.repository;

import com.Quiz.QuizApp.DTO.Response.QuestionDTO;
import com.Quiz.QuizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Long> {
    @Query(value = """
    SELECT *
    FROM question_list
    WHERE question_id NOT IN (
        SELECT question_id FROM quiz_data
        WHERE quiz_id = :quizId
    )
    ORDER BY RAND()
    LIMIT 1
    """, nativeQuery = true)
    Question getRandomUnansweredQuestion(long quizId);


//    @Query(value = "select answer from question_list where question_list=?",nativeQuery = true)
//    String findAnswer(long questionId);
}
