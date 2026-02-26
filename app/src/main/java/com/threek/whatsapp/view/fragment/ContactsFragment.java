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

import com.threek.whatsapp.R;
import com.threek.whatsapp.model.User;
import com.threek.whatsapp.view.activity.FriendRequestActivity;
import com.threek.whatsapp.view.activity.UserProfileActivity;
import com.threek.whatsapp.view.adapter.UserAdapter;
import com.threek.whatsapp.viewmodel.ContactsViewModel;

public class ContactsFragment extends Fragment {

    private ContactsViewModel viewModel;
    private UserAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ContactsViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_contacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.getContacts().observe(getViewLifecycleOwner(), users -> {
            adapter.setUsers(users);
        });

        adapter.setOnUserClickListener(new UserAdapter.OnUserClickListener() {
            @Override
            public void onUserClick(User user) {
                Intent intent = new Intent(getContext(), UserProfileActivity.class);
                intent.putExtra("user_id", user.getId());
                startActivity(intent);
            }

            @Override
            public void onActionClick(User user) {
                // TODO: Handle add friend action
            }
        });

        view.findViewById(R.id.btn_friend_requests).setOnClickListener(v -> {
            startActivity(new Intent(getContext(), FriendRequestActivity.class));
        });
    }
}
