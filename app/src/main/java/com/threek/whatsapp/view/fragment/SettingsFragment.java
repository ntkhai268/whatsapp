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

import com.threek.whatsapp.R;
import com.threek.whatsapp.repository.AuthRepository;
import com.threek.whatsapp.view.activity.LoginActivity;
import com.threek.whatsapp.viewmodel.SettingsViewModel;

public class SettingsFragment extends Fragment {

    private SettingsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        view.findViewById(R.id.btn_change_password).setOnClickListener(v -> {
            // TODO: Open change password dialog
        });

        view.findViewById(R.id.btn_export_data).setOnClickListener(v -> {
            // TODO: Trigger data export
        });

        view.findViewById(R.id.btn_delete_account).setOnClickListener(v -> {
            // TODO: Show delete account confirmation dialog
        });

        // Logout
        view.findViewById(R.id.btn_logout).setOnClickListener(v -> {
            AuthRepository authRepo = AuthRepository.getInstance(requireContext());
            authRepo.clearSession();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            requireActivity().finish();
        });
    }
}
