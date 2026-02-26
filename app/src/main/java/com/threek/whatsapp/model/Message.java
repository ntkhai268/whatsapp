package com.threek.whatsapp.model;

public class Message {

    public enum Type { TEXT, FILE, IMAGE }
    public enum Status { SENT, DELIVERED, READ }

    private String id;
    private String senderId;
    private String content;
    private String timestamp;
    private Type type;
    private String fileName;
    private String fileSize;
    private boolean isEdited;
    private Status status;

    public Message() {}

    public Message(String id, String senderId, String content, String timestamp, Type type, Status status) {
        this.id = id;
        this.senderId = senderId;
        this.content = content;
        this.timestamp = timestamp;
        this.type = type;
        this.status = status;
    }

    // Getters
    public String getId() { return id; }
    public String getSenderId() { return senderId; }
    public String getContent() { return content; }
    public String getTimestamp() { return timestamp; }
    public Type getType() { return type; }
    public String getFileName() { return fileName; }
    public String getFileSize() { return fileSize; }
    public boolean isEdited() { return isEdited; }
    public Status getStatus() { return status; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setSenderId(String senderId) { this.senderId = senderId; }
    public void setContent(String content) { this.content = content; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public void setType(Type type) { this.type = type; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public void setFileSize(String fileSize) { this.fileSize = fileSize; }
    public void setEdited(boolean edited) { isEdited = edited; }
    public void setStatus(Status status) { this.status = status; }
}
