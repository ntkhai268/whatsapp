package com.threek.whatsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.threek.whatsapp.model.User;
import com.threek.whatsapp.repository.UserRepository;

import java.util.List;

public class BlockedUsersViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final MutableLiveData<List<User>> blockedUsers = new MutableLiveData<>();

    public BlockedUsersViewModel() {
        userRepository = UserRepository.getInstance();
        loadBlockedUsers();
    }

    public LiveData<List<User>> getBlockedUsers() {
        return blockedUsers;
    }

    public void loadBlockedUsers() {
        blockedUsers.setValue(userRepository.getBlockedUsers());
    }

    public void unblockUser(String userId) {
        userRepository.unblockUser(userId);
    }
}
