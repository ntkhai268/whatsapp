package com.threek.whatsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.threek.whatsapp.model.User;
import com.threek.whatsapp.repository.UserRepository;

public class MyProfileViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final MutableLiveData<User> currentUser = new MutableLiveData<>();
    private final MutableLiveData<Boolean> activityStatusEnabled = new MutableLiveData<>(true);
    private final MutableLiveData<Boolean> profileVisibilityEnabled = new MutableLiveData<>(true);

    public MyProfileViewModel() {
        userRepository = UserRepository.getInstance();
        loadProfile();
    }

    public LiveData<User> getCurrentUser() {
        return currentUser;
    }

    public LiveData<Boolean> getActivityStatusEnabled() {
        return activityStatusEnabled;
    }

    public LiveData<Boolean> getProfileVisibilityEnabled() {
        return profileVisibilityEnabled;
    }

    public void loadProfile() {
        currentUser.setValue(userRepository.getCurrentUser());
    }

    public void setActivityStatus(boolean enabled) {
        activityStatusEnabled.setValue(enabled);
    }

    public void setProfileVisibility(boolean enabled) {
        profileVisibilityEnabled.setValue(enabled);
    }

    public void saveProfile(String fullName, String email) {
        // TODO: Implement save
    }
}
