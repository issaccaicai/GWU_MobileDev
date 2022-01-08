package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class profile extends AppCompatActivity {
    BottomNavigationView btnNavView;
    SharedPreferences res;
    TextView tvCampus, tvGarage, tvName, tvDate, tvTime, tvHours, tvGWID, tvPhone, tvEmail, tvRole,tvInfo;
    Button btnCancel;

    private RecyclerView favoriteRV;
    private likeAdapter adapter;
    private ArrayList<Like> likeArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvCampus = (TextView) findViewById(R.id.ser_campus);
        tvGarage = (TextView) findViewById(R.id.ser_garage);
        tvDate = (TextView) findViewById(R.id.ser_date);
        tvTime = (TextView) findViewById(R.id.res_time);
        tvHours = (TextView) findViewById(R.id.res_hours);
        btnCancel = (Button) findViewById(R.id.Cancel);

        favoriteRV = findViewById(R.id.favorite);

        loadData();

        // user info retrieved
        SharedPreferences userinfo = getSharedPreferences("StoredInfo", MODE_PRIVATE);
        String name = userinfo.getString("name", "No name defined");
        String gwid = userinfo.getString("gwid", "No GWID defined");
        String phone = userinfo.getString("phone", "No phone defined");
        String email = userinfo.getString("email", "No email defined");
        String role = userinfo.getString("role", "No role defined");//"No defined" is the default value.
//        int idName = prefs.getInt("idName", 0); //0 is the default value.
        tvName = (TextView) findViewById(R.id.name);
        tvName.setText(name);
        tvGWID = (TextView) findViewById(R.id.tv_gwid);
        tvGWID.setText(gwid);
        tvPhone = (TextView) findViewById(R.id.phone);
        tvPhone.setText(phone);
        tvEmail = (TextView) findViewById(R.id.email);
        tvEmail.setText(email);
        tvRole = (TextView) findViewById(R.id.role);
        tvRole.setText(role);
        //
        SharedPreferences res = getSharedPreferences("Reservation", MODE_PRIVATE);
        // user Reservation retrieved
        String Campus = res.getString("Campus", "No reservations found!");
        String Garage = res.getString("Garage", "");
        String Date = res.getString("Date", "");
        String Time = res.getString("Time", "");
        String Hours = res.getString("Hours", "");

        if (!Garage.equals("")) {
            tvCampus.setText("Campus: " + Campus);
            tvGarage.setText("Garage: " + Garage);
            tvDate.setText("Date: " + Date);
            tvTime.setText("Time: " + Time);
            tvHours.setText("Hour(s): " + Hours);
            tvCampus.setVisibility(View.VISIBLE);
            tvGarage.setVisibility(View.VISIBLE);
            tvDate.setVisibility(View.VISIBLE);
            tvTime.setVisibility(View.VISIBLE);
            tvHours.setVisibility(View.VISIBLE);
            btnCancel.setVisibility(View.VISIBLE);
        } else {
            tvCampus.setText("No reservations found!");
            tvCampus.setVisibility(View.VISIBLE);
            tvGarage.setVisibility(View.GONE);
            tvDate.setVisibility(View.GONE);
            tvTime.setVisibility(View.GONE);
            tvHours.setVisibility(View.GONE);
            btnCancel.setVisibility(View.GONE);
        }

//        if (res.contains("Reservation")) {
//            if(Campus!="") {
//                tvCampus.setText("Campus: " + Campus);
//                tvGarage.setText("Garage: " + Garage);
//                tvDate.setText("Date: " + Date);
//                tvTime.setText("Time: " + Time);
//                tvHours.setText("Hour(s): " + Hours);
//                tvCampus.setVisibility(View.VISIBLE);
//                tvGarage.setVisibility(View.VISIBLE);
//                tvDate.setVisibility(View.VISIBLE);
//                tvTime.setVisibility(View.VISIBLE);
//                tvHours.setVisibility(View.VISIBLE);
//                btnCancel.setVisibility(View.VISIBLE);
//            }
//        } else {
//            tvCampus.setText("You don't have any reservations!");
//            tvCampus.setVisibility(View.VISIBLE);
//            tvGarage.setVisibility(View.GONE);
//            tvDate.setVisibility(View.GONE);
//            tvTime.setVisibility(View.GONE);
//            tvHours.setVisibility(View.GONE);
//            btnCancel.setVisibility(View.GONE);
//        }


        btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor editor2 = res.edit();
                        editor2.remove("Campus");
                        editor2.remove("Garage");
                        editor2.remove("Date");
                        editor2.remove("Time");
                        editor2.remove("Hours");
                        editor2.commit();
                        tvCampus.setText("You don't have any reservations!");
                        tvCampus.setVisibility(View.VISIBLE);
                        tvGarage.setVisibility(View.GONE);
                        tvTime.setVisibility(View.GONE);
                        tvDate.setVisibility(View.GONE);
                        tvHours.setVisibility(View.GONE);
                        btnCancel.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "You have canceled your reservation!", Toast.LENGTH_SHORT).show();
                    }
                });



        btnNavView = (BottomNavigationView) findViewById(R.id.bottomNavigation_id);
        btnNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.navigation_home:
                        Intent intent = new Intent(profile.this, campus.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        return true;
                }
                switch(id){
                    case R.id.navigation_profile:
                        Intent intent = new Intent(profile.this, profile.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        return true;
                }
                switch(id){
                    case R.id.navigation_logout:

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

                        Intent intent = new Intent(profile.this, login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
    }
    private void displayFavrite() {
        adapter = new likeAdapter(likeArrayList, profile.this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        favoriteRV.setHasFixedSize(true);
        favoriteRV.setLayoutManager(manager);
        favoriteRV.setAdapter(adapter);
    }
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("like", null);
        if (json != null){
            Type type = new TypeToken<ArrayList<Like>>() {}.getType();
            likeArrayList = gson.fromJson(json, type);
            boolean ans = likeArrayList.isEmpty();
            tvInfo = (TextView) findViewById(R.id.info);
            if (likeArrayList == null) {
                likeArrayList = new ArrayList<>();
                tvInfo.setVisibility(View.VISIBLE);
            }
            if (ans == true){
                tvInfo.setVisibility(View.VISIBLE);
            }
            else{
                displayFavrite();
                tvInfo.setVisibility(View.GONE);
            }
        }

    }
}