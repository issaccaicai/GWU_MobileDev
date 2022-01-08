package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_campus);

        RelativeLayout relative1 = (RelativeLayout) findViewById(R.id.click_FB);
        relative1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //you can pass image & text over here using putExtra()
                Intent intent=new Intent(MainActivity.this, foggy_bottom.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                startActivity(intent);

            }
        });
    }
}