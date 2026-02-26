package com.threek.whatsapp.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.threek.whatsapp.R;
import com.threek.whatsapp.viewmodel.UserProfileViewModel;

public class UserProfileActivity extends AppCompatActivity {

    private UserProfileViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        viewModel = new ViewModelProvider(this).get(UserProfileViewModel.class);

        String userId = getIntent().getStringExtra("user_id");
        if (userId == null) userId = "u1";
        viewModel.loadUser(userId);

        viewModel.getUser().observe(this, user -> {
            if (user != null) {
                ((TextView) findViewById(R.id.user_name)).setText(user.getFullName());
                ((TextView) findViewById(R.id.user_username)).setText("@" + user.getUsername());
                ((TextView) findViewById(R.id.user_email)).setText(user.getEmail());
                ((TextView) findViewById(R.id.user_member_since)).setText(user.getMemberSince());
                ((TextView) findViewById(R.id.user_mutual_friends)).setText(
                        getString(R.string.connections, user.getMutualFriends()));

                if (user.isOnline()) {
                    ((TextView) findViewById(R.id.user_status)).setText(R.string.online);
                } else {
                    ((TextView) findViewById(R.id.user_status)).setText(user.getLastSeen());
                }
            }
        });

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
        findViewById(R.id.btn_add_friend).setOnClickListener(v -> { /* TODO */ });
        findViewById(R.id.btn_message).setOnClickListener(v -> { /* TODO */ });
        findViewById(R.id.btn_block).setOnClickListener(v -> { /* TODO */ });
        findViewById(R.id.btn_report).setOnClickListener(v -> { /* TODO */ });
    }
}
