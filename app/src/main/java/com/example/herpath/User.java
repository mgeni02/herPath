package com.example.herpath;

public class User {

    private String userId;
    private String username;

    private String email;
    // Add other properties if needed

    // Required empty constructor for Firebase
    public User() {}

    // Constructor with all properties
    public User(String userId, String email, String username) {
        this.username = username;
        this.email = email;
        this.userId=userId;
    }

    // Getters and setters for all properties

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

