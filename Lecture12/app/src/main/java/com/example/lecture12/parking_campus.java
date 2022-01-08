package com.example.lecture12;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class parking_campus extends AppCompatActivity {
    BottomNavigationView btnNavView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_campus);

        btnNavView = (BottomNavigationView) findViewById(R.id.bottomNavigation_id);
        btnNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.navigation_home:
                        Intent intent = new Intent(parking_campus.this, parking_campus.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        return true;
                }
                switch(id){
                    case R.id.navigation_profile:
                        Intent intent = new Intent(parking_campus.this, profile.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        return true;
                }
                switch(id){
                    case R.id.navigation_logout:
                        Intent intent = new Intent(parking_campus.this, login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });

        RelativeLayout fb_campus = (RelativeLayout) findViewById(R.id.click_FB);
        fb_campus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(parking_campus.this, fb_map_test.class);
                intent.putExtra("campus", "Foggy Bottom");
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        RelativeLayout mv_campus = (RelativeLayout) findViewById(R.id.click_MV);
        mv_campus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(parking_campus.this, fb_map_test.class);
                intent.putExtra("campus", "Mount Vernon Campus");
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        RelativeLayout vast_campus = (RelativeLayout) findViewById(R.id.click_VAST);
        vast_campus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(parking_campus.this, fb_map_test.class);
                intent.putExtra("campus", "VAST Campus");
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

            }
        });
    }


}
