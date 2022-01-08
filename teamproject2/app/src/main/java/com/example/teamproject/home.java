package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class home extends AppCompatActivity {
    public static final String NAME = "NAME";
    private TextView nameText;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nameText = findViewById(R.id.welcome_);
        Intent i = getIntent();
        name = i.getStringExtra(NAME);
        nameText.setText("Welcome," + name);
    }
}