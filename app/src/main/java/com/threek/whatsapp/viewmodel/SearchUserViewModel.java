package com.threek.whatsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.threek.whatsapp.model.User;
import com.threek.whatsapp.repository.UserRepository;

import java.util.List;

public class SearchUserViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final MutableLiveData<List<User>> searchResults = new MutableLiveData<>();

    public SearchUserViewModel() {
        userRepository = UserRepository.getInstance();
        loadSearchResults();
    }

    public LiveData<List<User>> getSearchResults() {
        return searchResults;
    }

    public void loadSearchResults() {
        searchResults.setValue(userRepository.searchUsers(""));
    }

    public void search(String query) {
        searchResults.setValue(userRepository.searchUsers(query));
    }
}
