package com.threek.whatsapp.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.threek.whatsapp.R;
import com.threek.whatsapp.view.adapter.UserAdapter;
import com.threek.whatsapp.viewmodel.SearchUserViewModel;

public class SearchUserActivity extends AppCompatActivity {

    private SearchUserViewModel viewModel;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);

        viewModel = new ViewModelProvider(this).get(SearchUserViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recycler_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.getSearchResults().observe(this, users -> {
            adapter.setUsers(users);
        });

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
    }
}
