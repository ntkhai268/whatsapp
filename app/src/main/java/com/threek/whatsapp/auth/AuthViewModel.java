package com.threek.whatsapp.auth;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * ViewModel for authentication state management.
 * Handles login result processing and auth state.
 */
public class AuthViewModel extends AndroidViewModel {

    private final AuthRepository authRepository;
    private final MutableLiveData<AuthState> authState = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public AuthViewModel(@NonNull Application application) {
        super(application);
        authRepository = AuthRepository.getInstance(application);
        checkAuthState();
    }

    public LiveData<AuthState> getAuthState() {
        return authState;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    /** Check initial auth state */
    public void checkAuthState() {
        if (authRepository.isLoggedIn()) {
            authState.setValue(AuthState.AUTHENTICATED);
        } else if (authRepository.hasTokens()) {
            // Token expired but refresh token exists → try refresh
            authState.setValue(AuthState.TOKEN_EXPIRED);
            refreshToken();
        } else {
            authState.setValue(AuthState.UNAUTHENTICATED);
        }
    }

    /** Exchange auth code for tokens after login redirect */
    public void handleAuthResponse(net.openid.appauth.AuthorizationResponse response) {
        authState.setValue(AuthState.LOADING);

        authRepository.exchangeCodeForTokens(response, new AuthRepository.TokenCallback() {
            @Override
            public void onSuccess(String accessToken) {
                authState.postValue(AuthState.AUTHENTICATED);
            }

            @Override
            public void onError(String error) {
                errorMessage.postValue(error);
                authState.postValue(AuthState.UNAUTHENTICATED);
            }
        });
    }

    /** Refresh expired token */
    public void refreshToken() {
        authState.setValue(AuthState.LOADING);

        authRepository.refreshAccessToken(new AuthRepository.TokenCallback() {
            @Override
            public void onSuccess(String accessToken) {
                authState.postValue(AuthState.AUTHENTICATED);
            }

            @Override
            public void onError(String error) {
                errorMessage.postValue(error);
                authState.postValue(AuthState.UNAUTHENTICATED);
            }
        });
    }

    /** Logout */
    public void logout() {
        authRepository.clearSession();
        authState.setValue(AuthState.UNAUTHENTICATED);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        authRepository.dispose();
    }

    /** Authentication states */
    public enum AuthState {
        LOADING,            // Đang xử lý (exchange code / refresh)
        AUTHENTICATED,      // Đã đăng nhập, token hợp lệ
        UNAUTHENTICATED,    // Chưa đăng nhập hoặc session hết hạn
        TOKEN_EXPIRED       // Token expired, cần refresh
    }
}
