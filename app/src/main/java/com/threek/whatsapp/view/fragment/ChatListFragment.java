package com.threek.whatsapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.threek.whatsapp.R;
import com.threek.whatsapp.model.Conversation;
import com.threek.whatsapp.view.activity.ConversationActivity;
import com.threek.whatsapp.view.activity.CreateGroupActivity;
import com.threek.whatsapp.view.activity.NotificationActivity;
import com.threek.whatsapp.view.activity.SearchUserActivity;
import com.threek.whatsapp.view.adapter.ConversationAdapter;
import com.threek.whatsapp.viewmodel.ChatListViewModel;

public class ChatListFragment extends Fragment {

    private ChatListViewModel viewModel;
    private ConversationAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ChatListViewModel.class);

        // Setup RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_conversations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ConversationAdapter();
        recyclerView.setAdapter(adapter);

        // Setup tabs
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_inbox));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_unread));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_follow_ups));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_archived));

        // Observe conversations
        viewModel.getConversations().observe(getViewLifecycleOwner(), conversations -> {
            adapter.setConversations(conversations);
        });

        // Click handlers
        adapter.setOnConversationClickListener(conversation -> {
            Intent intent = new Intent(getContext(), ConversationActivity.class);
            intent.putExtra("conversation_id", conversation.getId());
            intent.putExtra("conversation_name", conversation.getDisplayName());
            intent.putExtra("is_group", conversation.isGroup());
            startActivity(intent);
        });

        view.findViewById(R.id.btn_create_group).setOnClickListener(v -> {
            startActivity(new Intent(getContext(), CreateGroupActivity.class));
        });

        view.findViewById(R.id.btn_notification).setOnClickListener(v -> {
            startActivity(new Intent(getContext(), NotificationActivity.class));
        });

        view.findViewById(R.id.btn_search_user).setOnClickListener(v -> {
            startActivity(new Intent(getContext(), SearchUserActivity.class));
        });
    }
}
