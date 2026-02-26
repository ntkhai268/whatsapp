package com.threek.whatsapp.model;

import java.util.List;

public class Group {
    private String id;
    private String name;
    private int avatarResId;
    private String createdDate;
    private List<User> members;
    private String pinnedMessage;
    private boolean notificationsEnabled;

    public Group() {}

    public Group(String id, String name, int avatarResId, String createdDate,
                 List<User> members, String pinnedMessage, boolean notificationsEnabled) {
        this.id = id;
        this.name = name;
        this.avatarResId = avatarResId;
        this.createdDate = createdDate;
        this.members = members;
        this.pinnedMessage = pinnedMessage;
        this.notificationsEnabled = notificationsEnabled;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public int getAvatarResId() { return avatarResId; }
    public String getCreatedDate() { return createdDate; }
    public List<User> getMembers() { return members; }
    public String getPinnedMessage() { return pinnedMessage; }
    public boolean isNotificationsEnabled() { return notificationsEnabled; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAvatarResId(int avatarResId) { this.avatarResId = avatarResId; }
    public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }
    public void setMembers(List<User> members) { this.members = members; }
    public void setPinnedMessage(String pinnedMessage) { this.pinnedMessage = pinnedMessage; }
    public void setNotificationsEnabled(boolean notificationsEnabled) { this.notificationsEnabled = notificationsEnabled; }
}
