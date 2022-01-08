package com.example.lecture12;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class fb_map_test extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ListView list_items;
    LinearLayout linear_map;

    private String selected_campus;
    ListView listView;
    ArrayList<itemModel> arrayList;
    CustomAdapter adapter;
    itemModel model;

    TextView tvTitle;
    BottomNavigationView btnNavView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        btnNavView = (BottomNavigationView) findViewById(R.id.bottomNavigation_id);
        btnNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.navigation_home:
                        Intent intent = new Intent(fb_map_test.this, parking_campus.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        return true;
                }
                switch(id){
                    case R.id.navigation_profile:
                        Intent intent = new Intent(fb_map_test.this, profile.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        return true;
                }
                switch(id){
                    case R.id.navigation_logout:
                        Intent intent = new Intent(fb_map_test.this, login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            selected_campus = bundle.getString("campus");

            Toast.makeText(getBaseContext(), selected_campus, Toast.LENGTH_LONG).show();
        }
        tvTitle = (TextView) findViewById(R.id.campus_title);
        tvTitle.setText(selected_campus);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);


        linear_map  = (LinearLayout) findViewById(R.id.layout_map_id);;
        linear_map.setVisibility(View.GONE);
        list_items  = (ListView) findViewById(R.id.listview);;
        list_items.setVisibility(View.VISIBLE);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.toggle);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = radioGroup.getCheckedRadioButtonId();
                if (id==R.id.list) {
                    linear_map.setVisibility(View.GONE);
                    list_items.setVisibility(View.VISIBLE);
                    Toast.makeText(getBaseContext(), "List view", Toast.LENGTH_SHORT).show();

                }
                if (id==R.id.map){
                    linear_map.setVisibility(View.VISIBLE);
                    list_items.setVisibility(View.GONE);
                    Toast.makeText(getBaseContext(), "Map view", Toast.LENGTH_SHORT).show();
                }

            }
        });

        listView = (ListView) findViewById(R.id.listview);
        arrayList = new ArrayList<>();


        try {
            JSONObject object = new JSONObject(readJSON());
            JSONArray array = object.getJSONArray("parking_list");
            Resources resources = this.getResources();
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                String campus = jsonObject.getString("campus");
                String garage = jsonObject.getString("garage");
                String address = jsonObject.getString("address");
                String note = jsonObject.getString("note");
                String permit = jsonObject.getString("permit");
                String latitude = jsonObject.getString("latitude");
                String longitude = jsonObject.getString("longitude");
                String rating = jsonObject.getString("rating");
                // get image name from JSON
                String imageName = jsonObject.getString("image");
                // get resource id by image name
                final int resourceId = resources.getIdentifier(imageName, "drawable", this.getPackageName());

                if (campus.equals(selected_campus)){

                    model = new itemModel();
                    model.setGarage(garage);
                    model.setAddress(address);
                    model.setNote(note);
                    model.setPermit(permit);
                    model.setLatitude(latitude);
                    model.setLongitude(longitude);
                    model.setRating(Float.parseFloat(rating));
                    model.setImage(resourceId);
                    arrayList.add(model);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new CustomAdapter(this, arrayList);
        listView.setAdapter(adapter);

//        OnItemClickListener itemClickListener = new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
//                // Getting the Container Layout of the ListView
//                LinearLayout linearLayoutParent = (LinearLayout) container;
//
//                // Getting the garage_name TextView
//                TextView tvGarage = (TextView) linearLayoutParent.getChildAt(0);
//
//                Toast.makeText(getBaseContext(), tvGarage.getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        };
//        listView.setOnItemClickListener(itemClickListener);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {

//                    Intent intent = new Intent(fb_map_test.this,MapsActivity.class);
//                    String result = (String) listView.getItemAtPosition(position).toString();
//                    intent.putExtra("get",result);
                LinearLayout linearLayoutParent = (LinearLayout) v;

                // Getting the garage_name TextView
                TextView tvGarage = (TextView) linearLayoutParent.getChildAt(0);
//
                if (tvGarage.getText().toString().equals("G Street")) {
                    forward(position);
                }
                else if (tvGarage.getText().toString().equals("Elliott School")) {
                    forward(position);
                }
                else if (tvGarage.getText().toString().equals("Amsterdam Hall")) {
                    forward(position);
                }
                else if (tvGarage.getText().toString().equals("University Student Center Garage")) {
                    forward(position);
                }
                else if (tvGarage.getText().toString().equals("Media and Public Affairs Building")) {
                    forward(position);
                }
                else if (tvGarage.getText().toString().equals("Funger/Duques")) {
                    forward(position);
                }
                else if (tvGarage.getText().toString().equals("Ross Hall")) {
                    forward(position);
                }
                else if (tvGarage.getText().toString().equals("1922 F Street, NW")) {
                    forward(position);
                }
                else if (tvGarage.getText().toString().equals("Shenkman Hall**")) {
                    forward(position);
                }
                else if (tvGarage.getText().toString().equals("South Hall**")) {
                    forward(position);
                }
                else if (tvGarage.getText().toString().equals("The Dakota")) {
                    forward(position);
                }
                else if (tvGarage.getText().toString().equals("Lerner Health & Wellness Center")) {
                    forward(position);
                }
                else if (tvGarage.getText().toString().equals("Square 54")) {
                    forward(position);
                }
                else {
                    forward(position);
                }

            }
        });
    }

    public void forward(int position){
        String garage = arrayList.get(position).getGarage();
        String address = arrayList.get(position).getAddress();
        String note = arrayList.get(position).getNote();
        String permit = arrayList.get(position).getPermit();
        String latitude = arrayList.get(position).getLatitude();
        String longitude = arrayList.get(position).getLongitude();
        float rating = arrayList.get(position).getRating();
        int image = arrayList.get(position).getImage();

        Intent intent = new Intent(fb_map_test.this,MapsActivity.class);
        intent.putExtra("garage", garage);
        intent.putExtra("address", address);
        intent.putExtra("note", note);
        intent.putExtra("permit", permit);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        intent.putExtra("rating", rating);
        intent.putExtra("image", image);
        intent.putExtra("campus", selected_campus);
        startActivity(intent);
    }

    public void forward_map(String garage){
        try
        {
            JSONObject object = new JSONObject(readJSON());
            JSONArray array = object.getJSONArray("parking_list");
            Resources resources = this.getResources();

            // Looping through all info and show on map
            for (int i = 0; i < array.length(); i++) {

                JSONObject jsonObject = array.getJSONObject(i);
                String garage_name = jsonObject.getString("garage");
                String address = jsonObject.getString("address");
                String latitude = jsonObject.getString("latitude");
                String longitude = jsonObject.getString("longitude");
                String note = jsonObject.getString("note");
                String permit = jsonObject.getString("permit");
                String rates = jsonObject.getString("rating");
                float rating = Float.parseFloat(rates);
                String imageName = jsonObject.getString("image");

                final int resourceId = resources.getIdentifier(imageName, "drawable", this.getPackageName());

                if (garage_name.equals(garage)){
                    Intent intent = new Intent(fb_map_test.this, MapsActivity.class);
                    intent.putExtra("campus", selected_campus);
                    intent.putExtra("garage", garage);
                    intent.putExtra("address", address);
                    intent.putExtra("note", note);
                    intent.putExtra("permit", permit);
                    intent.putExtra("rating", rating);
                    intent.putExtra("image", resourceId);
                    intent.putExtra("latitude", latitude);
                    intent.putExtra("longitude", longitude);
                    startActivity(intent);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void addMarkerToMap(String latitude, String longitude, String address, String garage)
    {
        double lat = Double.parseDouble(latitude);
        double lng = Double.parseDouble(longitude);

        // create marker
        MarkerOptions marker = new MarkerOptions().position(new LatLng(lat, lng))
                .title(garage)
                .snippet(address);

        // Marker icon
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        // Add marker to map
        mMap.addMarker(marker);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng),15.0f));
    }
    private void fetchDataFromJSON()
    {
        try
        {
            JSONObject object = new JSONObject(readJSON());
            JSONArray array = object.getJSONArray("parking_list");

            // Clear old markers
            mMap.clear();

            // Looping through all info and show on map
            for (int i = 0; i < array.length(); i++) {

                JSONObject jsonObject = array.getJSONObject(i);
                String campus = jsonObject.getString("campus");
                String garage = jsonObject.getString("garage");
                String address = jsonObject.getString("address");
                String latitude = jsonObject.getString("latitude");
                String longitude = jsonObject.getString("longitude");
                // Add marker
                if (campus.equals(selected_campus)){
                    addMarkerToMap(latitude, longitude, address, garage);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in GWU and move the camera
//        LatLng gwu = new LatLng(38.8997, -77.0486);
//        mMap.addMarker(new MarkerOptions().position(gwu).title("George Washington University"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(gwu));
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
        {
            @Override
            public void onInfoWindowClick(Marker arg0) {
                if(arg0 != null && arg0.getTitle().equals("G Street")){
                    String garage = "G Street";
                    forward_map(garage);
                }

                if(arg0 != null && arg0.getTitle().equals("Elliott School")){
                    String garage = "Elliott School";
                    forward_map(garage);
                }

                if(arg0 != null && arg0.getTitle().equals("Amsterdam Hall")){
                    String garage = "Amsterdam Hall";
                    forward_map(garage);
                }

                if(arg0 != null && arg0.getTitle().equals("University Student Center Garage")){
                    String garage = "University Student Center Garage";
                    forward_map(garage);

                }
                if(arg0 != null && arg0.getTitle().equals("Media and Public Affairs Building")){
                    String garage = "Media and Public Affairs Building";
                    forward_map(garage);
                }

                if(arg0 != null && arg0.getTitle().equals("Funger/Duques")){
                    String garage = "Funger/Duques";
                    forward_map(garage);
                }

                if(arg0 != null && arg0.getTitle().equals("Ross Hall")){
                    String garage = "Ross Hall";
                    forward_map(garage);
                }

                if(arg0 != null && arg0.getTitle().equals("1922 F Street, NW")){
                    String garage = "1922 F Street, NW";
                    forward_map(garage);
                }
                if(arg0 != null && arg0.getTitle().equals("Shenkman Hall**")){
                    String garage = "Shenkman Hall**";
                    forward_map(garage);
                }

                if(arg0 != null && arg0.getTitle().equals("South Hall**")){
                    String garage = "South Hall**";
                    forward_map(garage);
                }

                if(arg0 != null && arg0.getTitle().equals("The Dakota")){
                    String garage = "The Dakota";
                    forward_map(garage);
                }

                if(arg0 != null && arg0.getTitle().equals("Lerner Health & Wellness Center")){
                    String garage = "Lerner Health & Wellness Center";
                    forward_map(garage);
                }

                if(arg0 != null && arg0.getTitle().equals("Square 54")){
                    String garage = "Square 54";
                    forward_map(garage);
                }

                if(arg0 != null && arg0.getTitle().equals("Academic Center")){
                    String garage = "Academic Center";
                    forward_map(garage);
                }
                if(arg0 != null && arg0.getTitle().equals("Mount Vernon")){
                    String garage = "Mount Vernon";
                    forward_map(garage);
                }
                if(arg0 != null && arg0.getTitle().equals("Virginia Science and Technology")){
                    String garage = "Virginia Science and Technology";
                    forward_map(garage);
                }
            }
        });
        fetchDataFromJSON();

    }

    public String readJSON() {
        String json = null;
        try {
            // Open json file
            InputStream inputStream = getAssets().open("parking_list.json");
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