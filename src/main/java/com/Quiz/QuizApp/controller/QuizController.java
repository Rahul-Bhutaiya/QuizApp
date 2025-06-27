package com.Quiz.QuizApp.controller;

import com.Quiz.QuizApp.DTO.Request.AnswerDTO;
import com.Quiz.QuizApp.DTO.Response.*;
import com.Quiz.QuizApp.model.Question;
import com.Quiz.QuizApp.model.User;
import com.Quiz.QuizApp.service.QuizService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("add-questions")
    public AddQuestions addAllQuestions(@RequestBody List<Question> questionList){
        return quizService.addAllQuestions(questionList);
    }

    @PostMapping("start-quiz")
    public ResponseDTO<User> startQuiz(@PathParam("name") String name){
        return quizService.startQuiz(name);
    }

    @GetMapping("get-question")
    public ResponseDTO<QuestionDTO> getQuestion(@RequestHeader("quizId") long quizId){
        return quizService.getQuestion(quizId);
    }

    @PostMapping("submit-answer")
    public AnswerResponse submitAnswer(@RequestBody AnswerDTO answerDTO){
        return quizService.submitAnswer(answerDTO);
    }

    @GetMapping("get-result")
    public ResponseDTO<ResultDTO> getResult(@RequestHeader("quizId") long quizId){
        return quizService.getQuizResult(quizId);
    }

    @DeleteMapping("delete-user")
    public ResponseDTO<User> deleteUser(@RequestHeader("quizId") long quizId){
        return quizService.removeUser(quizId);
    }
}
