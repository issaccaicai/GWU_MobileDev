package com.example.project;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class welcome extends AppCompatActivity {
    Animation logo_anim, text_anim;
    ImageView logo;
    TextView text1,text2;

    String welcomeName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.welcome);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            welcomeName = bundle.getString("Firstname") + "!";
        }

        logo = findViewById(R.id.logo);
        text1 = findViewById(R.id.welcome_);
        text2 = findViewById(R.id.welcome_text);
        logo_anim = AnimationUtils.loadAnimation(this, R.anim.logo);
        text_anim = AnimationUtils.loadAnimation(this, R.anim.welcome_text);
        text_anim = AnimationUtils.loadAnimation(this, R.anim.welcome_text);
        logo.setAnimation(logo_anim);
        text1.setText("Welcome, " + welcomeName);
        text1.setAnimation(text_anim);
        text2.setAnimation(text_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(welcome.this, campus.class);

                // MY_PREFS_NAME - a static String variable like:
                //public static final String MY_PREFS_NAME = "MyPrefsFile";
//                SharedPreferences pref = getApplicationContext().getSharedPreferences("StoredInfo", MODE_PRIVATE);
//                SharedPreferences.Editor editor = pref.edit();
//                editor.putString("name", "Ying");
//                editor.putString("gwid", "Ying");
//                editor.putBoolean("key_name1", true);           // Saving boolean - true/false
//                editor.putInt("key_name2", "int value");        // Saving integer
//                editor.putFloat("key_name3", "float value");    // Saving float
//                editor.putLong("key_name4", "long value");      // Saving long
//                editor.putString("key_name5", "string value");  // Saving string
//                editor.putInt("idName", 12);
//                editor.apply();

                startActivity(intent);
                finish();
            }
        }, 3000);
    }

}