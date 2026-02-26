package com.threek.whatsapp.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.threek.whatsapp.R;
import com.threek.whatsapp.view.adapter.GroupMemberAdapter;
import com.threek.whatsapp.viewmodel.GroupProfileViewModel;

public class GroupProfileActivity extends AppCompatActivity {

    private GroupProfileViewModel viewModel;
    private GroupMemberAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_profile);

        viewModel = new ViewModelProvider(this).get(GroupProfileViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recycler_members);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GroupMemberAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.getGroup().observe(this, group -> {
            if (group != null) {
                ((TextView) findViewById(R.id.group_name)).setText(group.getName());
                ((TextView) findViewById(R.id.group_created)).setText(
                        getString(R.string.created_on, group.getCreatedDate()));
                ((TextView) findViewById(R.id.txt_members_count)).setText(
                        getString(R.string.members_count, group.getMembers().size()));
                adapter.setMembers(group.getMembers());
            }
        });

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
        findViewById(R.id.btn_leave_group).setOnClickListener(v -> { /* TODO */ });
        findViewById(R.id.btn_delete_group).setOnClickListener(v -> { /* TODO */ });
    }
}
