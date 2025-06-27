package com.Quiz.QuizApp.service;

import com.Quiz.QuizApp.DTO.Request.AnswerDTO;
import com.Quiz.QuizApp.DTO.Response.*;
import com.Quiz.QuizApp.model.Question;
import com.Quiz.QuizApp.model.QuizData;
import com.Quiz.QuizApp.model.User;
import com.Quiz.QuizApp.repository.QuestionRepo;
import com.Quiz.QuizApp.repository.QuizRepo;
import com.Quiz.QuizApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuizService {
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    QuizRepo quizRepo;


    public AddQuestions addAllQuestions(List<Question> questionList) {
        try{
            questionRepo.saveAll(questionList);
            return new AddQuestions("All Questions Added Successfully",true);
        } catch (Exception e) {
            return new AddQuestions("Can't Add All Questions",false);
        }
    }


    public ResponseDTO<User> startQuiz(String name) {
        try{
            if (name == null || name.trim().isEmpty()) {
                return new ResponseDTO<>(null, "Username cannot be empty", false);
            }
            long quizId = ThreadLocalRandom.current().nextLong(1000, 1000000);
            User newUser = new User(quizId,name);
            userRepo.save(newUser);
            return new ResponseDTO<>(newUser,"Your Quiz is Started",true);
        } catch (Exception e) {
            return new ResponseDTO<>(null,"Can't Start A New Quiz",false);
        }
    }

    public ResponseDTO<QuestionDTO> getQuestion(long quizId) {
        try{
            User user = userRepo.findById(quizId).orElse(null);
            if(user==null){
                throw new Exception("Invalid Quiz Id");
            }
            int submittedQuestions = quizRepo.countByQuizId(quizId);
            if(submittedQuestions==5){
                throw new RuntimeException("Your Quiz is Completed. Check Result Now");
            }
            Question question = questionRepo.getRandomUnansweredQuestion(quizId);
            QuestionDTO questionDTO = new QuestionDTO(question.getQuestionId(),question.getDescription(),question.getA(), question.getB(), question.getC(), question.getD());
            return new ResponseDTO<>(questionDTO,"New Question Found Successfully",true);
        } catch (Exception e) {
            return new ResponseDTO<>(null,e.getMessage(),false);
        }
    }

    public AnswerResponse submitAnswer(AnswerDTO answerDTO) {
        try{
            int submittedQuestions = quizRepo.countByQuizId(answerDTO.getQuiz_id());
            if(submittedQuestions==5){
                throw new RuntimeException("Your Quiz is Completed. Check Result Now");
            }

            Question question = questionRepo.findById(answerDTO.getQuestion_id()).orElse(null);
            User user = userRepo.findById(answerDTO.getQuiz_id()).orElse(null);
            if(question==null){
                throw new Exception("Question not Found");
            }
            if(user==null){
                throw new Exception("User not Found");
            }
            boolean isCorrect = question.getAnswer().equalsIgnoreCase(answerDTO.getAnswer().trim().toLowerCase());


            QuizData quizData = new QuizData(user,question,answerDTO.getAnswer(),isCorrect);
            quizRepo.save(quizData);

            return new AnswerResponse(quizData,"Your Answer Submitted Successfully",submittedQuestions+1,true);

        } catch (Exception e) {
            return new AnswerResponse(null,e.getMessage(),false);
        }
    }


    public ResponseDTO<ResultDTO> getQuizResult(long quizId) {
        try{
            User user = userRepo.findById(quizId).orElse(null);
            if(user==null){
                throw new Exception("Invalid Quiz Id");
            }
            int submittedQuestions = quizRepo.countByQuizId(quizId);
            if(submittedQuestions<5){
                throw new RuntimeException("Please complete the quiz to see the result");
            }
            int correctAns = quizRepo.countCorrectAnswer(quizId);
            int wrongAns = quizRepo.countWrongAnswer(quizId);

            ResultDTO resultDTO = new ResultDTO(correctAns,wrongAns);
            return new ResponseDTO<>(resultDTO,"User Result Found Successfully",true);
        } catch (Exception e) {
            return new ResponseDTO<>(null,e.getMessage(),false);
        }
    }


    public ResponseDTO<User> removeUser(long quizId) {
        try{
            User user = userRepo.findById(quizId).orElse(null);
            if(user==null){
                throw new Exception("Invalid Quiz Id");
            }
            userRepo.deleteById(quizId);
            return new ResponseDTO<>(user,"User Deleted Successfully",true);
        } catch (Exception e) {
            return new ResponseDTO<>(null,e.getMessage(),false);
        }
    }
}
