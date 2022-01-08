package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class login extends AppCompatActivity {
    String id;
    String password;
    Button login;
    EditText etID, etPassword;

    TextView tvAttempts;
    int counter = 3;
    int wrong = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button)findViewById(R.id.button);
        etID = (EditText)findViewById(R.id.editText);
        etPassword = (EditText)findViewById(R.id.editText2);

        tvAttempts = (TextView)findViewById(R.id.textView2);
        tvAttempts.setVisibility(View.GONE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = etID.getText().toString();
                password = etPassword.getText().toString();
                try
                {
                    JSONObject object = new JSONObject(readJSON());
                    JSONArray array = object.getJSONArray("login_list");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        String GWID = jsonObject.getString("GWID");
                        String FirstName = jsonObject.getString("FirstName");
                        String LastName = jsonObject.getString("LastName");
                        String Password = jsonObject.getString("Password");
                        String Phone = jsonObject.getString("Phone");
                        String Email = jsonObject.getString("Email");
                        String Role = jsonObject.getString("Role");
                        if (GWID.equals(id)){
                            wrong--;
                            if (Password.equals(password)){
                                Toast.makeText(getApplicationContext(),
                                        "Successful login!",Toast.LENGTH_SHORT).show();
                                storeInfo(GWID,FirstName,LastName,Phone, Email, Role);
                                break;
                            } else{
                                Toast.makeText(getApplicationContext(), "Wrong credentials!",Toast.LENGTH_SHORT).show();
                                tvAttempts.setVisibility(View.VISIBLE);
                                tvAttempts.setBackgroundColor(Color.RED);
                                counter--;
                                tvAttempts.setText("Attempts Left: " + Integer.toString(counter));
                                if (counter == 0) {
                                    tvAttempts.setText("Locked! No attempts remained!");
                                    login.setEnabled(false);
                                }
                                break;
                            }
                        }
                        else {
                            wrong++;
                            continue;
                        }
                    }
                    if (wrong >=2){
                        Toast.makeText(getApplicationContext(), "No records found!",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void storeInfo(String gwId, String fName, String lName, String phone, String email,String role){
        Intent intent = new Intent(login.this,welcome.class);
        intent.putExtra("Firstname", fName);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("StoredInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", fName + " " + lName);
        editor.putString("gwid", gwId);
        editor.putString("phone", phone);
        editor.putString("email", email);
        editor.putString("role", role);
        editor.commit();
        startActivity(intent);
    }
    private boolean fetchDataFromJSON(String id,String password)
    {
        boolean auth = false;
        try
        {
            JSONObject object = new JSONObject(readJSON());
            JSONArray array = object.getJSONArray("login_list");

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                String GWID = jsonObject.getString("GWID");
                String FirstName = jsonObject.getString("FirstName");
                String LastName = jsonObject.getString("LastName");
                String Password = jsonObject.getString("Password");
                String Phone = jsonObject.getString("Phone");
                String Email = jsonObject.getString("Email");
                String Role = jsonObject.getString("Role");
                if (GWID.equals(id) && Password.equals(password)){
                    auth = true;
                    storeInfo(GWID,FirstName,LastName, Phone, Email, Role);
                    break;
                }
                auth = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return auth;
    }
    public String readJSON() {
        String json = null;
        try {
            // Open json file
            InputStream inputStream = getAssets().open("login.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            inputStream.read(buffer);
            inputStream.close();
            // convert byte to string
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return json;
        }
        return json;
    }
}