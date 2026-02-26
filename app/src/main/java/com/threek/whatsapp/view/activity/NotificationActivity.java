package com.threek.whatsapp.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.threek.whatsapp.R;
import com.threek.whatsapp.view.adapter.NotificationAdapter;
import com.threek.whatsapp.viewmodel.NotificationViewModel;

public class NotificationActivity extends AppCompatActivity {

    private NotificationViewModel viewModel;
    private NotificationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        viewModel = new ViewModelProvider(this).get(NotificationViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recycler_notifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotificationAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.getNotifications().observe(this, notifications -> {
            adapter.setNotifications(notifications);
        });

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
        findViewById(R.id.btn_mark_all_read).setOnClickListener(v -> {
            viewModel.markAllAsRead();
        });
    }
}
