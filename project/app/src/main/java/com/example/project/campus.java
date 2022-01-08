package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class campus extends AppCompatActivity {
    BottomNavigationView btnNavView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus);

        btnNavView = (BottomNavigationView) findViewById(R.id.bottomNavigation_id);
        btnNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.navigation_home:
                        Intent intent = new Intent(campus.this, campus.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        return true;
                }
                switch(id){
                    case R.id.navigation_profile:
                        Intent intent = new Intent(campus.this, profile.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        return true;
                }
                switch(id){
                    case R.id.navigation_logout:
                        SharedPreferences userinfo = getSharedPreferences("StoredInfo", MODE_PRIVATE);
                        SharedPreferences res = getSharedPreferences("Reservation", MODE_PRIVATE);
                        SharedPreferences.Editor editor = userinfo.edit();
                        SharedPreferences.Editor editor2 = res.edit();
                        editor2.remove("Campus");
                        editor2.remove("Garage");
                        editor2.remove("Date");
                        editor2.remove("Time");
                        editor2.remove("Hours");

                        editor2.commit();
                        editor.remove("name");
                        editor.remove("gwid");
                        editor.remove("phone");
                        editor.remove("email");
                        editor.remove("role");
                        editor.commit();

                        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
                        SharedPreferences.Editor editor3 = sharedPreferences.edit();
                        editor3.remove("like");
                        editor3.commit();
                        Intent intent = new Intent(campus.this, login.class);
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
                Intent intent=new Intent(campus.this, list_map_view.class);
                intent.putExtra("campus", "Foggy Bottom");
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        RelativeLayout mv_campus = (RelativeLayout) findViewById(R.id.click_MV);
        mv_campus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(campus.this, list_map_view.class);
                intent.putExtra("campus", "Mount Vernon Campus");
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        RelativeLayout vast_campus = (RelativeLayout) findViewById(R.id.click_VAST);
        vast_campus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(campus.this, list_map_view.class);
                intent.putExtra("campus", "VAST Campus");
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

            }
        });
    }


}
