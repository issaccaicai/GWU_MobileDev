package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void toggleButtonClick(View view){
        TextView textView = (TextView) findViewById(R.id.text_view);
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggle_button);
        boolean on = toggleButton.isChecked();
        if (on) {
            textView.setText("T-Button is On");
        }
        else{
            textView.setText("T-Button is Off");
        }
    }
    public void milkCBClick(View view){
        TextView textView = (TextView) findViewById(R.id.text_view);
        CheckBox milkCB = (CheckBox) findViewById(R.id.checkbox_milk);
        boolean checked = milkCB.isChecked();
        if (checked){
            textView.setText("Milk Check Box is Checked");
        }
        else{
            textView.setText("Milk Check Box is NOT Checked");
        }
    }
    public void sugarCBClick(View view){
        TextView textView = (TextView) findViewById(R.id.text_view);
        CheckBox milkCB = (CheckBox) findViewById(R.id.checkbox_milk);
        boolean checked = milkCB.isChecked();
        if (checked){
            textView.setText("Sugar Check Box is Checked");
        }
        else{
            textView.setText("Sugar Check Box is NOT Checked");
        }
    }
    public void lemonCBClick(View view){
        TextView textView = (TextView) findViewById(R.id.text_view);
        CheckBox milkCB = (CheckBox) findViewById(R.id.checkbox_milk);
        boolean checked = milkCB.isChecked();
        if (checked){
            textView.setText("Lemon Check Box is Checked");
        }
        else{
            textView.setText("Lemon Check Box is NOT Checked");
        }
    }
}