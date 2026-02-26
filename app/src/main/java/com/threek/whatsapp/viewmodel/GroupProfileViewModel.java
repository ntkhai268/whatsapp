package com.threek.whatsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.threek.whatsapp.model.Group;
import com.threek.whatsapp.util.MockData;

public class GroupProfileViewModel extends ViewModel {

    private final MutableLiveData<Group> group = new MutableLiveData<>();

    public GroupProfileViewModel() {
        loadGroup();
    }

    public LiveData<Group> getGroup() {
        return group;
    }

    public void loadGroup() {
        group.setValue(MockData.getGroupInfo());
    }
}
