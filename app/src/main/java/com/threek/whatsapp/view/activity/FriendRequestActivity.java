package com.threek.whatsapp.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.threek.whatsapp.R;
import com.threek.whatsapp.view.adapter.FriendRequestAdapter;
import com.threek.whatsapp.view.adapter.UserAdapter;
import com.threek.whatsapp.viewmodel.FriendRequestViewModel;

public class FriendRequestActivity extends AppCompatActivity {

    private FriendRequestViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);

        viewModel = new ViewModelProvider(this).get(FriendRequestViewModel.class);

        // Pending requests RecyclerView
        RecyclerView recyclerPending = findViewById(R.id.recycler_pending);
        recyclerPending.setLayoutManager(new LinearLayoutManager(this));
        FriendRequestAdapter pendingAdapter = new FriendRequestAdapter();
        recyclerPending.setAdapter(pendingAdapter);

        // Suggested friends RecyclerView
        RecyclerView recyclerSuggested = findViewById(R.id.recycler_suggested);
        recyclerSuggested.setLayoutManager(new LinearLayoutManager(this));
        // Reuse UserAdapter for suggested friends or create a simple adapter
        UserAdapter suggestedAdapter = new UserAdapter();
        recyclerSuggested.setAdapter(suggestedAdapter);

        // Observe data
        viewModel.getPendingRequests().observe(this, requests -> {
            pendingAdapter.setRequests(requests);
            TextView countView = findViewById(R.id.txt_requests_count);
            countView.setText(getString(R.string.requests_count, requests.size()));
        });

        viewModel.getSuggestedFriends().observe(this, suggestions -> {
            // Map FriendRequest users to User list for adapter
            java.util.List<com.threek.whatsapp.model.User> users = new java.util.ArrayList<>();
            for (com.threek.whatsapp.model.FriendRequest fr : suggestions) {
                users.add(fr.getUser());
            }
            suggestedAdapter.setUsers(users);
        });

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
    }
}
