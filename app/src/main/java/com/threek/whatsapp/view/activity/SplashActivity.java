package com.threek.whatsapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.threek.whatsapp.R;
import com.threek.whatsapp.auth.AuthRepository;

/**
 * Splash screen — checks auth state and routes to Login or Main.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delay 1 second for branding, then check auth
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            AuthRepository authRepo = AuthRepository.getInstance(this);

            if (authRepo.isLoggedIn()) {
                // Token still valid → go to main
                navigateTo(MainActivity.class);
            } else if (authRepo.hasTokens()) {
                // Token expired but has refresh token → try refresh
                authRepo.refreshAccessToken(new AuthRepository.TokenCallback() {
                    @Override
                    public void onSuccess(String accessToken) {
                        runOnUiThread(() -> navigateTo(MainActivity.class));
                    }

                    @Override
                    public void onError(String error) {
                        runOnUiThread(() -> navigateTo(LoginActivity.class));
                    }
                });
            } else {
                // No tokens → go to login
                navigateTo(LoginActivity.class);
            }
        }, 1000);
    }

    private void navigateTo(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
