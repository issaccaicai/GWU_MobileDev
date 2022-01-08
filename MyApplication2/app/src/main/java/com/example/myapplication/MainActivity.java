package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickFindBear(View view){
        TextView brands = (TextView) findViewById(R.id.textView);
        Spinner color = (Spinner) findViewById(R.id.spinner2);
        String beerType = String.valueOf(color.getSelectedItem());
        brands.setText(beerType);
    }
}