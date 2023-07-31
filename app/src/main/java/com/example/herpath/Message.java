package com.example.herpath;

public class Message {
    private String messageId;
    private String senderId;
    private String sender;
    private String receiverId;
    private String content;
    private long timestamp;
    // Add other properties if needed

    // Required empty constructor for Firebase
    public Message() {}

    // Constructor with all properties
    public Message(String messageId, String senderId, String receiverId, String content, long timestamp) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.timestamp = timestamp;
    }
    // New constructor for conversation activity
    public Message(String senderId, String sender, String content, long timestamp) {
        this.senderId=senderId;
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
    }
    // Getters and setters for all properties

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
