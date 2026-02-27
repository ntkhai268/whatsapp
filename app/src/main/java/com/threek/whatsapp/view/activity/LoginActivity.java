package com.threek.whatsapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.threek.whatsapp.R;
import com.threek.whatsapp.repository.AuthRepository;
import com.threek.whatsapp.viewmodel.AuthViewModel;

import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationResponse;

/**
 * Login screen — opens Keycloak login page via AppAuth.
 * After successful auth, exchanges code for tokens and navigates to MainActivity.
 */
public class LoginActivity extends AppCompatActivity {

    private AuthViewModel authViewModel;
    private ProgressBar progressBar;
    private TextView errorText;
    private TextView loginButton;

    /** Launcher for Keycloak login browser */
    private final ActivityResultLauncher<Intent> authLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        Intent data = result.getData();
                        if (data != null) {
                            AuthorizationResponse response = AuthorizationResponse.fromIntent(data);
                            AuthorizationException exception = AuthorizationException.fromIntent(data);

                            if (response != null) {
                                // Exchange authorization code for tokens
                                authViewModel.handleAuthResponse(response);
                            } else if (exception != null) {
                                showError("Login failed: " + exception.getMessage());
                            }
                        } else {
                            showError("Login cancelled");
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progress_bar);
        errorText = findViewById(R.id.txt_error);
        loginButton = findViewById(R.id.btn_login);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        // Observe auth state
        authViewModel.getAuthState().observe(this, state -> {
            switch (state) {
                case LOADING:
                    showLoading(true);
                    break;
                case AUTHENTICATED:
                    showLoading(false);
                    navigateToMain();
                    break;
                case UNAUTHENTICATED:
                    showLoading(false);
                    break;
                case TOKEN_EXPIRED:
                    showLoading(true);
                    // AuthViewModel will automatically try refresh
                    break;
            }
        });

        // Observe errors
        authViewModel.getErrorMessage().observe(this, this::showError);

        // Login button click → open Keycloak login page
        loginButton.setOnClickListener(v -> {
            try {
                AuthRepository authRepo = AuthRepository.getInstance(this);
                Intent authIntent = authRepo.getAuthIntent();
                authLauncher.launch(authIntent);
            } catch (Exception e) {
                showError("Cannot open login page: " + e.getMessage());
            }
        });
    }

    private void showLoading(boolean loading) {
        progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
        loginButton.setVisibility(loading ? View.GONE : View.VISIBLE);
        errorText.setVisibility(View.GONE);
    }

    private void showError(String message) {
        errorText.setText(message);
        errorText.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        loginButton.setVisibility(View.VISIBLE);
    }

    private void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
