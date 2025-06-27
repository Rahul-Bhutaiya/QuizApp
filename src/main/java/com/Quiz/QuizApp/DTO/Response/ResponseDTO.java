package com.Quiz.QuizApp.DTO.Response;

import com.Quiz.QuizApp.model.User;

public class ResponseDTO<T> {
    private T response;
    private String message;
    private boolean success;

    public ResponseDTO(T user, String message, boolean success) {
        this.response = user;
        this.message = message;
        this.success = success;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
