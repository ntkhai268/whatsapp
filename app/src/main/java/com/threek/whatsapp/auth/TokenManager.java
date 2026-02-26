package com.threek.whatsapp.auth;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

/**
 * Manages OAuth2 tokens in SharedPreferences.
 * Stores access token, refresh token, ID token, and expiration time.
 */
public class TokenManager {

    private static final String PREFS_NAME = "whatsapp_auth_prefs";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";
    private static final String KEY_ID_TOKEN = "id_token";
    private static final String KEY_TOKEN_EXPIRY = "token_expiry";

    private static TokenManager instance;
    private final SharedPreferences prefs;

    private TokenManager(Context context) {
        prefs = context.getApplicationContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized TokenManager getInstance(Context context) {
        if (instance == null) {
            instance = new TokenManager(context);
        }
        return instance;
    }

    /** Save all tokens after successful auth */
    public void saveTokens(String accessToken, String refreshToken,
                           String idToken, long expiresAtMillis) {
        prefs.edit()
                .putString(KEY_ACCESS_TOKEN, accessToken)
                .putString(KEY_REFRESH_TOKEN, refreshToken)
                .putString(KEY_ID_TOKEN, idToken)
                .putLong(KEY_TOKEN_EXPIRY, expiresAtMillis)
                .apply();
    }

    @Nullable
    public String getAccessToken() {
        return prefs.getString(KEY_ACCESS_TOKEN, null);
    }

    @Nullable
    public String getRefreshToken() {
        return prefs.getString(KEY_REFRESH_TOKEN, null);
    }

    @Nullable
    public String getIdToken() {
        return prefs.getString(KEY_ID_TOKEN, null);
    }

    /** Check if there is a valid (non-expired) access token */
    public boolean isLoggedIn() {
        String token = getAccessToken();
        if (token == null) return false;
        long expiry = prefs.getLong(KEY_TOKEN_EXPIRY, 0);
        return System.currentTimeMillis() < expiry;
    }

    /** Check if token has expired but refresh token might still work */
    public boolean isTokenExpired() {
        long expiry = prefs.getLong(KEY_TOKEN_EXPIRY, 0);
        return System.currentTimeMillis() >= expiry;
    }

    /** Check if user has any stored tokens (even if expired) */
    public boolean hasTokens() {
        return getRefreshToken() != null;
    }

    /** Clear all tokens on logout */
    public void clearTokens() {
        prefs.edit().clear().apply();
    }
}
