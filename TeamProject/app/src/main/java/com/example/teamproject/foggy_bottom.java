package com.example.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class foggy_bottom extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LinearLayout linear_list;
    LinearLayout linear_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fb_list);

        linear_map  = (LinearLayout) findViewById(R.id.layout_map_id);;
        linear_map.setVisibility(View.GONE);
        linear_list  = (LinearLayout) findViewById(R.id.list_items);;
        linear_list.setVisibility(View.VISIBLE);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);






        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.toggle);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = radioGroup.getCheckedRadioButtonId();
                if (id==R.id.list) {
                    linear_map.setVisibility(View.GONE);
                    linear_list.setVisibility(View.VISIBLE);
                }
                if (id==R.id.map){
                    linear_map.setVisibility(View.VISIBLE);
                    linear_list.setVisibility(View.GONE);
                }

            }
        });

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng G_Street = new LatLng(38.898158, -77.046047);
        LatLng Elliott_School = new LatLng(38.896220, -77.044580);
        LatLng Amsterdam_Hall = new LatLng(38.899330, -77.050660);
        LatLng Student_Center = new LatLng(38.8999811, -77.0493346);
        mMap.addMarker(new MarkerOptions()
                .position(G_Street)
                .title("G Street")
                .snippet("2028 G Street, NW"));
        mMap.addMarker(new MarkerOptions()
                .position(Elliott_School)
                .title("Elliott School")
                .snippet("1957 E Street, NW"));
        mMap.addMarker(new MarkerOptions()
                .position(Amsterdam_Hall)
                .title("Amsterdam Hall")
                .snippet("2350 H Street, NW"));
        mMap.addMarker(new MarkerOptions()
                .position(Student_Center)
                .title("University Student Center Garage")
                .snippet("800 21st Street, NW"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(G_Street));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Elliott_School));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Amsterdam_Hall));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Student_Center));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Student_Center,15.0f));


        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
        {
            @Override
            public void onInfoWindowClick(Marker arg0) {
                if(arg0 != null && arg0.getTitle().equals("G Street")){
                    Intent intent1 = new Intent(foggy_bottom.this, MainActivity.class);
                    startActivity(intent1);}

                if(arg0 != null && arg0.getTitle().equals("Elliott School")){
                    Intent intent2 = new Intent(foggy_bottom.this, MainActivity.class);
                    startActivity(intent2);}

                if(arg0 != null && arg0.getTitle().equals("Amsterdam Hall")){
                    Intent intent3 = new Intent(foggy_bottom.this, MainActivity.class);
                    startActivity(intent3);}

                if(arg0 != null && arg0.getTitle().equals("University Student Center Garage")){
                    Intent intent4 = new Intent(foggy_bottom.this, MainActivity.class);
                    startActivity(intent4);}
            }
        });

//            // below line is use to add marker to each location of our array list.
//            mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("Marker"));
//            // below lin is use to zoom our camera on map.
//            mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
//            // below line is use to move our camera to the specific location.
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationArrayList.get(i),5.0f));

    }

}
