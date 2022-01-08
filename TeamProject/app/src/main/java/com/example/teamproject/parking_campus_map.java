package com.example.teamproject;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class parking_campus_map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fb_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);

        final Button button = (Button) findViewById(R.id.ClickMe);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                LatLng smithsonian = new LatLng(38.8860, -77.0213);
                mMap.addMarker(new MarkerOptions().position(smithsonian).title("Smithsonian Museum"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(smithsonian.latitude, smithsonian.longitude), 12.0f));
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

        // Add a marker in Sydney and move the camera
        LatLng gwu = new LatLng(38.8997, -77.0486);
        mMap.addMarker(new MarkerOptions().position(gwu).title("George Washington University"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gwu));
    }
}