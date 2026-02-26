package com.threek.whatsapp.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.threek.whatsapp.R;
import com.threek.whatsapp.view.adapter.BlockedUserAdapter;
import com.threek.whatsapp.viewmodel.BlockedUsersViewModel;

public class BlockedUsersActivity extends AppCompatActivity {

    private BlockedUsersViewModel viewModel;
    private BlockedUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocked_users);

        viewModel = new ViewModelProvider(this).get(BlockedUsersViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recycler_blocked_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BlockedUserAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.getBlockedUsers().observe(this, users -> {
            adapter.setBlockedUsers(users);
            TextView countText = findViewById(R.id.txt_blocked_count);
            countText.setText(getString(R.string.blocked_count, users.size()));
        });

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
    }
}
