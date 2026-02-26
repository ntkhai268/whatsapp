package com.threek.whatsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.threek.whatsapp.model.Message;
import com.threek.whatsapp.repository.ChatRepository;

import java.util.List;

public class ConversationViewModel extends ViewModel {

    private final ChatRepository chatRepository;
    private final MutableLiveData<List<Message>> messages = new MutableLiveData<>();

    public ConversationViewModel() {
        chatRepository = ChatRepository.getInstance();
    }

    public LiveData<List<Message>> getMessages() {
        return messages;
    }

    public void loadMessages(String conversationId) {
        messages.setValue(chatRepository.getMessages(conversationId));
    }

    public void loadGroupMessages(String groupId) {
        messages.setValue(chatRepository.getGroupMessages(groupId));
    }

    public void sendMessage(String conversationId, Message message) {
        chatRepository.sendMessage(conversationId, message);
        // Reload messages
        loadMessages(conversationId);
    }
}
