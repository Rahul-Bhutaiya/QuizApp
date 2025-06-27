package com.Quiz.QuizApp.DTO.Response;

public class QuestionDTO {
    private long questionId;
    private String description;
    private String a;
    private String b;
    private String c;
    private String d;

    public QuestionDTO(long questionId, String description, String a, String b, String c, String d) {
        this.questionId = questionId;
        this.description = description;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
}
