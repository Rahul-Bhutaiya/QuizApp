package com.Quiz.QuizApp.repository;

import com.Quiz.QuizApp.model.QuizData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepo extends JpaRepository<QuizData,Long> {
    @Query(value = "SELECT COUNT(*) FROM quiz_data WHERE quiz_id = ?",nativeQuery = true)
    int countByQuizId(long quizId);


    @Query(value = "SELECT COUNT(*) FROM quiz_data WHERE quiz_id=? AND answer_status=true",nativeQuery = true)
    int countCorrectAnswer(long quizId);

    @Query(value = "SELECT COUNT(*) FROM quiz_data WHERE quiz_id=? AND answer_status=false",nativeQuery = true)
    int countWrongAnswer(long quizId);
}
