package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void buttonOnClick(View view){
        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText("Some other string");
    }
    public void button2OnClick(View view){
        TextView textView = (TextView) findViewById(R.id.text_view);
        EditText editText = (EditText) findViewById(R.id.edit_text);
        String text = editText.getText().toString();
        textView.setText(text);
    }

    public void toggleButtonClick(View view){
        TextView textView = (TextView) findViewById(R.id.text_view);
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggle_button);
        boolean on = toggleButton.isChecked();
        if (on) {
            textView.setText("Toggle Button is On");
        }
        else{
            textView.setText("Toggle Button is Off");
        }
    }

    public void switchClick(View view){
        TextView textView = (TextView) findViewById(R.id.text_view);
        Switch switchView = (Switch) findViewById(R.id.switch_view);
        boolean on = switchView.isChecked();
        if (on) {
            textView.setText("Switch is On");
        }
        else{
            textView.setText("Switch is Off");
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
        CheckBox sugarCB = (CheckBox) findViewById(R.id.checkbox_sugar);
        boolean checked = sugarCB.isChecked();
        if (checked){
            textView.setText("Sugar Check Box is Checked");
        }
        else{
            textView.setText("Sugar Check Box is NOT Checked");
        }
    }

    public void radioButtonClick(View view){
        TextView textView = (TextView) findViewById(R.id.text_view);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        int id = radioGroup.getCheckedRadioButtonId();
        if (id==R.id.radio_cavemen) {
            textView.setText("Cavemen RB is selected");
        }
        if (id==R.id.radio_astronauts){
            textView.setText("Astronauts RB is selected");
        }
    }


    public void imageButtonClick(View view){
        String text = "I am a toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }
}