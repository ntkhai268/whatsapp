package com.threek.whatsapp.repository;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.threek.whatsapp.network.KeycloakConfig;
import com.threek.whatsapp.network.TokenManager;

import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.EndSessionRequest;
import net.openid.appauth.ResponseTypeValues;
import net.openid.appauth.TokenRequest;
import net.openid.appauth.TokenResponse;

/**
 * Repository handling Keycloak OAuth2/OIDC authentication flows.
 * Uses AppAuth library for Authorization Code Flow with PKCE.
 */
public class AuthRepository {

    private static final String TAG = "AuthRepository";
    private static AuthRepository instance;

    private final TokenManager tokenManager;
    private AuthorizationService authService;

    private AuthRepository(Context context) {
        this.tokenManager = TokenManager.getInstance(context);
        this.authService = new AuthorizationService(context);
    }

    public static synchronized AuthRepository getInstance(Context context) {
        if (instance == null) {
            instance = new AuthRepository(context);
        }
        return instance;
    }

    // ========================================================
    // Service Configuration
    // ========================================================

    /** Build OIDC service config from Keycloak endpoints */
    public AuthorizationServiceConfiguration getServiceConfig() {
        return new AuthorizationServiceConfiguration(
                Uri.parse(KeycloakConfig.getAuthorizationEndpoint()),
                Uri.parse(KeycloakConfig.getTokenEndpoint()),
                null,
                Uri.parse(KeycloakConfig.getEndSessionEndpoint())
        );
    }

    // ========================================================
    // Login Flow
    // ========================================================

    /**
     * Build an Authorization Intent to open Keycloak login page.
     * Use this with startActivityForResult() in LoginActivity.
     */
    public Intent getAuthIntent() {
        AuthorizationServiceConfiguration config = getServiceConfig();

        AuthorizationRequest request = new AuthorizationRequest.Builder(
                config,
                KeycloakConfig.CLIENT_ID,
                ResponseTypeValues.CODE,
                Uri.parse(KeycloakConfig.REDIRECT_URI)
        )
                .setScope("openid profile email")
                .build();

        return authService.getAuthorizationRequestIntent(request);
    }

    /**
     * Exchange authorization code for tokens.
     * Call this after receiving the auth response in onActivityResult.
     */
    public void exchangeCodeForTokens(@NonNull AuthorizationResponse authResponse,
                                       @NonNull TokenCallback callback) {
        TokenRequest tokenRequest = authResponse.createTokenExchangeRequest();

        authService.performTokenRequest(tokenRequest, (response, exception) -> {
            if (response != null) {
                // Save tokens
                long expiresAt = response.accessTokenExpirationTime != null
                        ? response.accessTokenExpirationTime : 0;

                tokenManager.saveTokens(
                        response.accessToken,
                        response.refreshToken,
                        response.idToken,
                        expiresAt
                );

                Log.d(TAG, "Token exchange successful");
                callback.onSuccess(response.accessToken);
            } else {
                Log.e(TAG, "Token exchange failed", exception);
                callback.onError(exception != null ? exception.getMessage() : "Token exchange failed");
            }
        });
    }

    // ========================================================
    // Token Refresh
    // ========================================================

    /** Refresh the access token using the stored refresh token */
    public void refreshAccessToken(@NonNull TokenCallback callback) {
        String refreshToken = tokenManager.getRefreshToken();
        if (refreshToken == null) {
            callback.onError("No refresh token available");
            return;
        }

        AuthorizationServiceConfiguration config = getServiceConfig();

        TokenRequest tokenRequest = new TokenRequest.Builder(config, KeycloakConfig.CLIENT_ID)
                .setGrantType("refresh_token")
                .setRefreshToken(refreshToken)
                .build();

        authService.performTokenRequest(tokenRequest, (response, exception) -> {
            if (response != null) {
                long expiresAt = response.accessTokenExpirationTime != null
                        ? response.accessTokenExpirationTime : 0;

                tokenManager.saveTokens(
                        response.accessToken,
                        response.refreshToken,
                        response.idToken,
                        expiresAt
                );

                callback.onSuccess(response.accessToken);
            } else {
                Log.e(TAG, "Token refresh failed", exception);
                tokenManager.clearTokens();
                callback.onError("Session expired, please login again");
            }
        });
    }

    // ========================================================
    // Logout Flow
    // ========================================================

    /** Build an End Session Intent for Keycloak logout */
    @Nullable
    public Intent getLogoutIntent() {
        String idToken = tokenManager.getIdToken();
        if (idToken == null) {
            tokenManager.clearTokens();
            return null;
        }

        AuthorizationServiceConfiguration config = getServiceConfig();

        EndSessionRequest endSessionRequest = new EndSessionRequest.Builder(config)
                .setIdTokenHint(idToken)
                .setPostLogoutRedirectUri(Uri.parse(KeycloakConfig.REDIRECT_URI))
                .build();

        return authService.getEndSessionRequestIntent(endSessionRequest);
    }

    /** Clear local tokens (call after logout completes) */
    public void clearSession() {
        tokenManager.clearTokens();
    }

    // ========================================================
    // State Checks
    // ========================================================

    public boolean isLoggedIn() {
        return tokenManager.isLoggedIn();
    }

    public boolean hasTokens() {
        return tokenManager.hasTokens();
    }

    @Nullable
    public String getAccessToken() {
        return tokenManager.getAccessToken();
    }

    /** Dispose auth service when no longer needed */
    public void dispose() {
        if (authService != null) {
            authService.dispose();
        }
    }

    // ========================================================
    // Callback Interface
    // ========================================================

    public interface TokenCallback {
        void onSuccess(String accessToken);
        void onError(String error);
    }
}
