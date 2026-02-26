package com.threek.whatsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.threek.whatsapp.model.Notification;
import com.threek.whatsapp.repository.NotificationRepository;

import java.util.List;

public class NotificationViewModel extends ViewModel {

    private final NotificationRepository notificationRepository;
    private final MutableLiveData<List<Notification>> notifications = new MutableLiveData<>();

    public NotificationViewModel() {
        notificationRepository = NotificationRepository.getInstance();
        loadNotifications();
    }

    public LiveData<List<Notification>> getNotifications() {
        return notifications;
    }

    public void loadNotifications() {
        notifications.setValue(notificationRepository.getNotifications());
    }

    public void markAllAsRead() {
        notificationRepository.markAllAsRead();
    }
}
