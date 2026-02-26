package com.threek.whatsapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.threek.whatsapp.R;
import com.threek.whatsapp.viewmodel.MyProfileViewModel;

public class MyProfileFragment extends Fragment {

    private MyProfileViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(MyProfileViewModel.class);

        viewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            // Profile data is set in layout XML as defaults
            // In production, update EditText values from user object
        });

        view.findViewById(R.id.btn_save_changes).setOnClickListener(v -> {
            // TODO: Save profile changes
        });
    }
}
