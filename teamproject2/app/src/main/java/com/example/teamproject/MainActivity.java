package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private String name, password;
    private EditText etName, etPassword;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button)findViewById(R.id.button);
        etName = (EditText)findViewById(R.id.Name);
        etPassword = (EditText)findViewById(R.id.editText2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               signIn();
            }
        });
    }

    public void signIn() {
        name = etName.getText().toString().trim();
        Intent i = new Intent(MainActivity.this, home.class);
        i.putExtra(home.NAME, name);
        startActivity(i);
    }
}