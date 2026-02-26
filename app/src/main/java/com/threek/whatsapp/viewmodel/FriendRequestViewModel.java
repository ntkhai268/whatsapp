package com.threek.whatsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.threek.whatsapp.model.FriendRequest;
import com.threek.whatsapp.repository.UserRepository;

import java.util.List;

public class FriendRequestViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final MutableLiveData<List<FriendRequest>> pendingRequests = new MutableLiveData<>();
    private final MutableLiveData<List<FriendRequest>> suggestedFriends = new MutableLiveData<>();

    public FriendRequestViewModel() {
        userRepository = UserRepository.getInstance();
        loadData();
    }

    public LiveData<List<FriendRequest>> getPendingRequests() {
        return pendingRequests;
    }

    public LiveData<List<FriendRequest>> getSuggestedFriends() {
        return suggestedFriends;
    }

    public void loadData() {
        pendingRequests.setValue(userRepository.getPendingRequests());
        suggestedFriends.setValue(userRepository.getSuggestedFriends());
    }

    public void acceptRequest(String requestId) {
        // TODO: Implement
    }

    public void declineRequest(String requestId) {
        // TODO: Implement
    }
}
