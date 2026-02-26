package com.threek.whatsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.threek.whatsapp.model.Conversation;
import com.threek.whatsapp.repository.ChatRepository;

import java.util.List;

public class ChatListViewModel extends ViewModel {

    private final ChatRepository chatRepository;
    private final MutableLiveData<List<Conversation>> conversations = new MutableLiveData<>();

    public ChatListViewModel() {
        chatRepository = ChatRepository.getInstance();
        loadConversations();
    }

    public LiveData<List<Conversation>> getConversations() {
        return conversations;
    }

    public void loadConversations() {
        conversations.setValue(chatRepository.getConversations());
    }
}
