package com.threek.whatsapp.repository;

import com.threek.whatsapp.model.Conversation;
import com.threek.whatsapp.model.Message;
import com.threek.whatsapp.util.MockData;

import java.util.List;

public class ChatRepository {

    private static ChatRepository instance;

    private ChatRepository() {}

    public static ChatRepository getInstance() {
        if (instance == null) {
            instance = new ChatRepository();
        }
        return instance;
    }

    public List<Conversation> getConversations() {
        return MockData.getConversations();
    }

    public List<Message> getMessages(String conversationId) {
        // In a real app, filter by conversationId
        return MockData.getMessages();
    }

    public List<Message> getGroupMessages(String groupId) {
        return MockData.getGroupMessages();
    }

    public void sendMessage(String conversationId, Message message) {
        // TODO: Implement with Room/API
    }
}
