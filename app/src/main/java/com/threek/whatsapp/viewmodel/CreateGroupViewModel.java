package com.threek.whatsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.threek.whatsapp.model.User;
import com.threek.whatsapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final MutableLiveData<List<User>> availableFriends = new MutableLiveData<>();
    private final MutableLiveData<List<User>> selectedMembers = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<String> groupName = new MutableLiveData<>("");

    public CreateGroupViewModel() {
        userRepository = UserRepository.getInstance();
        loadAvailableFriends();
    }

    public LiveData<List<User>> getAvailableFriends() {
        return availableFriends;
    }

    public LiveData<List<User>> getSelectedMembers() {
        return selectedMembers;
    }

    public LiveData<String> getGroupName() {
        return groupName;
    }

    public void loadAvailableFriends() {
        availableFriends.setValue(userRepository.getUsers());
    }

    public void addMember(User user) {
        List<User> current = selectedMembers.getValue();
        if (current == null) current = new ArrayList<>();
        current.add(user);
        selectedMembers.setValue(current);
    }

    public void removeMember(User user) {
        List<User> current = selectedMembers.getValue();
        if (current != null) {
            current.remove(user);
            selectedMembers.setValue(current);
        }
    }

    public void setGroupName(String name) {
        groupName.setValue(name);
    }

    public void createGroup() {
        // TODO: Implement with API
    }
}
