package com.threek.whatsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.threek.whatsapp.model.User;
import com.threek.whatsapp.repository.UserRepository;

public class UserProfileViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final MutableLiveData<User> user = new MutableLiveData<>();

    public UserProfileViewModel() {
        userRepository = UserRepository.getInstance();
    }

    public LiveData<User> getUser() {
        return user;
    }

    public void loadUser(String userId) {
        user.setValue(userRepository.getUserProfile(userId));
    }
}
