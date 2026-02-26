package com.threek.whatsapp.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.threek.whatsapp.R;

public class ViewMediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: Create activity_view_media.xml layout
        // For now, use a simple view
        setContentView(R.layout.activity_conversation);
    }
}
