package com.Quiz.QuizApp.DTO.Response;

import com.Quiz.QuizApp.model.QuizData;

public class AnswerResponse {
    private QuizData quizData;
    private String message;
    private int questionCount;
    private boolean success;

    public AnswerResponse(QuizData quizData, String message, int questionCount, boolean success) {
        this.quizData = quizData;
        this.message = message;
        this.questionCount = questionCount;
        this.success = success;
    }
    public AnswerResponse(QuizData quizData, String message,  boolean success) {
        this.quizData = quizData;
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public QuizData getQuizData() {
        return quizData;
    }

    public void setQuizData(QuizData quizData) {
        this.quizData = quizData;
    }


    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
