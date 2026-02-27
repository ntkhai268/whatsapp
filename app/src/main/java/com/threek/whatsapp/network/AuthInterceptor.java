package com.threek.whatsapp.network;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp Interceptor that adds the Keycloak access token
 * to every API request as a Bearer token header.
 *
 * Usage with Retrofit:
 *   OkHttpClient client = new OkHttpClient.Builder()
 *       .addInterceptor(new AuthInterceptor(context))
 *       .build();
 *
 *   Retrofit retrofit = new Retrofit.Builder()
 *       .client(client)
 *       .build();
 */
public class AuthInterceptor implements Interceptor {

    private final TokenManager tokenManager;

    public AuthInterceptor(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();

        String token = tokenManager.getAccessToken();
        if (token != null) {
            Request authorized = original.newBuilder()
                    .header("Authorization", "Bearer " + token)
                    .build();
            return chain.proceed(authorized);
        }

        return chain.proceed(original);
    }
}
