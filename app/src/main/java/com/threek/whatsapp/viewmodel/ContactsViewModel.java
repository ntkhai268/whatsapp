package com.threek.whatsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.threek.whatsapp.model.User;
import com.threek.whatsapp.repository.UserRepository;

import java.util.List;

public class ContactsViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final MutableLiveData<List<User>> contacts = new MutableLiveData<>();

    public ContactsViewModel() {
        userRepository = UserRepository.getInstance();
        loadContacts();
    }

    public LiveData<List<User>> getContacts() {
        return contacts;
    }

    public void loadContacts() {
        contacts.setValue(userRepository.getUsers());
    }
}
