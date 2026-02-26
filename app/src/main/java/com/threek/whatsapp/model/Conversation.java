package com.threek.whatsapp.model;

public class Conversation {
    private String id;
    private User user;
    private String lastMessage;
    private String lastMessageTime;
    private int unreadCount;
    private boolean isGroup;
    private String groupName;
    private int groupAvatarResId;

    public Conversation() {}

    public Conversation(String id, User user, String lastMessage, String lastMessageTime,
                        int unreadCount, boolean isGroup, String groupName) {
        this.id = id;
        this.user = user;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
        this.unreadCount = unreadCount;
        this.isGroup = isGroup;
        this.groupName = groupName;
    }

    // Getters
    public String getId() { return id; }
    public User getUser() { return user; }
    public String getLastMessage() { return lastMessage; }
    public String getLastMessageTime() { return lastMessageTime; }
    public int getUnreadCount() { return unreadCount; }
    public boolean isGroup() { return isGroup; }
    public String getGroupName() { return groupName; }
    public int getGroupAvatarResId() { return groupAvatarResId; }

    public String getDisplayName() {
        return isGroup ? groupName : (user != null ? user.getFullName() : "");
    }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setLastMessage(String lastMessage) { this.lastMessage = lastMessage; }
    public void setLastMessageTime(String lastMessageTime) { this.lastMessageTime = lastMessageTime; }
    public void setUnreadCount(int unreadCount) { this.unreadCount = unreadCount; }
    public void setGroup(boolean group) { isGroup = group; }
    public void setGroupName(String groupName) { this.groupName = groupName; }
    public void setGroupAvatarResId(int groupAvatarResId) { this.groupAvatarResId = groupAvatarResId; }
}
