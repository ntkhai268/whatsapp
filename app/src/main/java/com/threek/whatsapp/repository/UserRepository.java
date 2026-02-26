package com.threek.whatsapp.repository;

import com.threek.whatsapp.model.FriendRequest;
import com.threek.whatsapp.model.User;
import com.threek.whatsapp.util.MockData;

import java.util.List;

public class UserRepository {

    private static UserRepository instance;

    private UserRepository() {}

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public User getCurrentUser() {
        return MockData.getCurrentUser();
    }

    public List<User> getUsers() {
        return MockData.getUsers();
    }

    public List<User> searchUsers(String query) {
        return MockData.getSearchUsers();
    }

    public User getUserProfile(String userId) {
        List<User> users = MockData.getUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return users.get(0);
    }

    public List<FriendRequest> getPendingRequests() {
        return MockData.getPendingRequests();
    }

    public List<FriendRequest> getSuggestedFriends() {
        return MockData.getSuggestedFriends();
    }

    public List<User> getBlockedUsers() {
        return MockData.getBlockedUsers();
    }

    public void addFriend(String userId) {
        // TODO: Implement with API
    }

    public void blockUser(String userId) {
        // TODO: Implement with API
    }

    public void unblockUser(String userId) {
        // TODO: Implement with API
    }
}
