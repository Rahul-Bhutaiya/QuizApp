package com.Quiz.QuizApp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user_list")
public class User {

    @Id
    @Column(name = "quiz_id")
    private long quizId;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<QuizData> quizDataList;

    public User(){}

    public User(long quizId, String name) {
        this.quizId = quizId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }
}
