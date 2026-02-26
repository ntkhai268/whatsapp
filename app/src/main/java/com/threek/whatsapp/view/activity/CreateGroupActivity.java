package com.threek.whatsapp.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.threek.whatsapp.R;
import com.threek.whatsapp.view.adapter.UserAdapter;
import com.threek.whatsapp.viewmodel.CreateGroupViewModel;

public class CreateGroupActivity extends AppCompatActivity {

    private CreateGroupViewModel viewModel;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        viewModel = new ViewModelProvider(this).get(CreateGroupViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recycler_available_friends);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.getAvailableFriends().observe(this, users -> {
            adapter.setUsers(users);
        });

        viewModel.getSelectedMembers().observe(this, members -> {
            android.widget.TextView selectedText = findViewById(R.id.txt_selected_members);
            selectedText.setText(getString(R.string.selected_members, members.size()));
        });

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
        findViewById(R.id.btn_cancel).setOnClickListener(v -> finish());
        findViewById(R.id.btn_create_group).setOnClickListener(v -> {
            viewModel.createGroup();
            finish();
        });
    }
}
