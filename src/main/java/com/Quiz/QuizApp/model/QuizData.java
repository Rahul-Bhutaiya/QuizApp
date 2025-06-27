package com.Quiz.QuizApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "quiz_data")
public class QuizData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private String answer;

    @Column(name = "answer_status")
    private boolean answerStatus;

    public QuizData(){}

    public QuizData(User user, Question question, String answer, boolean answerStatus) {
        this.user = user;
        this.question = question;
        this.answer = answer;
        this.answerStatus = answerStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(boolean answerStatus) {
        this.answerStatus = answerStatus;
    }
}
