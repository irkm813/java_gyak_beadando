package com.example.java_gyak_beadando.Messages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageDto {
    private String userName;
    private String message;
    private String formattedDate;

    public MessageDto(String userName, String message, LocalDateTime createdAt) {
        this.userName = userName;
        this.message = message;
        this.formattedDate = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // Getters és Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }
}
