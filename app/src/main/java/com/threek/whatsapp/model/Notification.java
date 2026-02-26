package com.threek.whatsapp.model;

public class Notification {

    public enum Type { MESSAGE, FRIEND_REQUEST, GROUP_INVITE, SYSTEM }

    private String id;
    private String title;
    private String subtitle;
    private String timeAgo;
    private Type type;
    private boolean isRead;

    public Notification() {}

    public Notification(String id, String title, String subtitle, String timeAgo, Type type, boolean isRead) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.timeAgo = timeAgo;
        this.type = type;
        this.isRead = isRead;
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
    public String getTimeAgo() { return timeAgo; }
    public Type getType() { return type; }
    public boolean isRead() { return isRead; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }
    public void setTimeAgo(String timeAgo) { this.timeAgo = timeAgo; }
    public void setType(Type type) { this.type = type; }
    public void setRead(boolean read) { isRead = read; }
}
