package com.threek.whatsapp.repository;

import com.threek.whatsapp.model.Notification;
import com.threek.whatsapp.util.MockData;

import java.util.List;

public class NotificationRepository {

    private static NotificationRepository instance;

    private NotificationRepository() {}

    public static NotificationRepository getInstance() {
        if (instance == null) {
            instance = new NotificationRepository();
        }
        return instance;
    }

    public List<Notification> getNotifications() {
        return MockData.getNotifications();
    }

    public void markAllAsRead() {
        // TODO: Implement with API/Room
    }

    public void markAsRead(String notificationId) {
        // TODO: Implement
    }
}
