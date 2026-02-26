package com.threek.whatsapp.model;

public class User {
    private String id;
    private String fullName;
    private String username;
    private String email;
    private int avatarResId;
    private boolean isOnline;
    private String lastSeen;
    private String memberSince;
    private int mutualFriends;

    public User() {}

    public User(String id, String fullName, String username, String email, int avatarResId,
                boolean isOnline, String lastSeen, String memberSince, int mutualFriends) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.avatarResId = avatarResId;
        this.isOnline = isOnline;
        this.lastSeen = lastSeen;
        this.memberSince = memberSince;
        this.mutualFriends = mutualFriends;
    }

    // Getters
    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public int getAvatarResId() { return avatarResId; }
    public boolean isOnline() { return isOnline; }
    public String getLastSeen() { return lastSeen; }
    public String getMemberSince() { return memberSince; }
    public int getMutualFriends() { return mutualFriends; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setAvatarResId(int avatarResId) { this.avatarResId = avatarResId; }
    public void setOnline(boolean online) { isOnline = online; }
    public void setLastSeen(String lastSeen) { this.lastSeen = lastSeen; }
    public void setMemberSince(String memberSince) { this.memberSince = memberSince; }
    public void setMutualFriends(int mutualFriends) { this.mutualFriends = mutualFriends; }
}
