package com.threek.whatsapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.threek.whatsapp.R;
import com.threek.whatsapp.view.adapter.MessageAdapter;
import com.threek.whatsapp.viewmodel.ConversationViewModel;

public class ConversationActivity extends AppCompatActivity {

    private ConversationViewModel viewModel;
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        viewModel = new ViewModelProvider(this).get(ConversationViewModel.class);

        String conversationId = getIntent().getStringExtra("conversation_id");
        String conversationName = getIntent().getStringExtra("conversation_name");
        boolean isGroup = getIntent().getBooleanExtra("is_group", false);

        // Set header
        TextView nameView = findViewById(R.id.conversation_name);
        nameView.setText(conversationName);

        TextView statusView = findViewById(R.id.conversation_status);
        statusView.setText(R.string.active_now);

        // Setup RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_messages);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MessageAdapter();
        recyclerView.setAdapter(adapter);

        // Load messages
        if (isGroup) {
            viewModel.loadGroupMessages(conversationId);
        } else {
            viewModel.loadMessages(conversationId);
        }

        viewModel.getMessages().observe(this, messages -> {
            adapter.setMessages(messages);
            recyclerView.scrollToPosition(messages.size() - 1);
        });

        // Back button
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        // User profile
        findViewById(R.id.btn_user_profile).setOnClickListener(v -> {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        });

        // Send button
        findViewById(R.id.btn_send).setOnClickListener(v -> {
            // TODO: Implement send message
        });
    }
}
