package com.example.demo.dto;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    private String message;
    private T data;
    private LocalDateTime datetime;

    public ApiResponse(T data, String message) {
        this.data = data;
        this.message = message;
        this.datetime = LocalDateTime.now();
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, message);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(null, message);
    }

    // Gettery a settery
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public LocalDateTime getDatetime() { return datetime; }
    public void setDatetime(LocalDateTime datetime) { this.datetime = datetime; }
}
