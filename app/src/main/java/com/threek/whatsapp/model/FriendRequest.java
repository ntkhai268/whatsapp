package com.threek.whatsapp.model;

public class FriendRequest {
    private String id;
    private User user;
    private int mutualConnections;
    private boolean isPending;
    private String info; // Extra info like "Works at TechCorp"

    public FriendRequest() {}

    public FriendRequest(String id, User user, int mutualConnections, boolean isPending) {
        this.id = id;
        this.user = user;
        this.mutualConnections = mutualConnections;
        this.isPending = isPending;
    }

    // Getters
    public String getId() { return id; }
    public User getUser() { return user; }
    public int getMutualConnections() { return mutualConnections; }
    public boolean isPending() { return isPending; }
    public String getInfo() { return info; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setMutualConnections(int mutualConnections) { this.mutualConnections = mutualConnections; }
    public void setPending(boolean pending) { isPending = pending; }
    public void setInfo(String info) { this.info = info; }
}
