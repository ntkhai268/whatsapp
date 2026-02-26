package com.threek.whatsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {

    private final MutableLiveData<Boolean> pushNotifications = new MutableLiveData<>(true);
    private final MutableLiveData<Boolean> emailNotifications = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> soundAlerts = new MutableLiveData<>(true);
    private final MutableLiveData<Boolean> showLastSeen = new MutableLiveData<>(true);
    private final MutableLiveData<Boolean> readReceipts = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> allowFriendRequests = new MutableLiveData<>(true);

    public LiveData<Boolean> getPushNotifications() { return pushNotifications; }
    public LiveData<Boolean> getEmailNotifications() { return emailNotifications; }
    public LiveData<Boolean> getSoundAlerts() { return soundAlerts; }
    public LiveData<Boolean> getShowLastSeen() { return showLastSeen; }
    public LiveData<Boolean> getReadReceipts() { return readReceipts; }
    public LiveData<Boolean> getAllowFriendRequests() { return allowFriendRequests; }

    public void setPushNotifications(boolean enabled) { pushNotifications.setValue(enabled); }
    public void setEmailNotifications(boolean enabled) { emailNotifications.setValue(enabled); }
    public void setSoundAlerts(boolean enabled) { soundAlerts.setValue(enabled); }
    public void setShowLastSeen(boolean enabled) { showLastSeen.setValue(enabled); }
    public void setReadReceipts(boolean enabled) { readReceipts.setValue(enabled); }
    public void setAllowFriendRequests(boolean enabled) { allowFriendRequests.setValue(enabled); }
}
