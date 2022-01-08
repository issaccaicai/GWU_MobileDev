package com.example.project;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView tvGarage, tvAddress, tvNote, tvPermit, tvCampus, tvRates1, tvNotes;
    private ImageView ivImage;
    RatingBar rbRating;
    private String campus_location;
    String garage;
    LinearLayout linear_notes;
    LinearLayout linear_rates;

    BottomNavigationView btnNavView;

    private Button reservation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        linear_rates  = (LinearLayout) findViewById(R.id.rate_layout);;
        linear_rates.setVisibility(View.GONE);

        RatingBar rb = (RatingBar) findViewById(R.id.Garage1_RatingBar);

        btnNavView = (BottomNavigationView) findViewById(R.id.bottomNavigation_id);

        reservation = (Button) findViewById(R.id.reservation) ;
        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(garage);
            }
        });
        btnNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.navigation_home:
                        Intent intent = new Intent(MapsActivity.this, campus.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        return true;
                }
                switch(id){
                    case R.id.navigation_profile:
                        Intent intent = new Intent(MapsActivity.this, profile.class);
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
                        Intent intent = new Intent(MapsActivity.this, login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            garage = bundle.getString("garage");
            String address = bundle.getString("address");
            String note = bundle.getString("note");
            String permit = bundle.getString("permit");
            campus_location = bundle.getString("campus");
            float rating = bundle.getFloat("rating");
            int image = bundle.getInt("image");

            tvGarage = (TextView) findViewById(R.id.Garage1);
            tvGarage.setText(garage);
            tvAddress = (TextView) findViewById(R.id.Garage1_address_details1);
            tvAddress.setText(address);
            tvNote = (TextView) findViewById(R.id.Garage1_address_details2);
            tvNote.setText(note);
            tvPermit = (TextView) findViewById(R.id.Garage1_Permit_details);
            tvPermit.setText(permit);
            rbRating = (RatingBar) findViewById(R.id.Garage1_RatingBar);
            rbRating.setRating(rating);
            ivImage = (ImageView) findViewById(R.id.garage1_img);
            ivImage.setImageResource(image);

            tvNotes = (TextView) findViewById(R.id.notes_details);

            if (campus_location.equals("Foggy Bottom")){
                tvCampus = (TextView) findViewById(R.id.Garage1_rate_details);
                tvCampus.setText(campus_location);
                tvRates1 = (TextView) findViewById(R.id.Garage1_rates1);
                tvRates1.setText("Daily Rate (6–2am): $16.50" + "\n"+
                        "Evening (after 5pm): $12" + "\n"+
                        "Weekend Daily Rate: $12" + "\n"+
                        "Monthly Permit: $230" + "\n"+
                        "Semester Permit: $920"  + "\n"+
                        "\n" +
                        "*Included 18% parking tax.");
                linear_notes  = (LinearLayout) findViewById(R.id.notes_title);;
                linear_notes.setVisibility(View.GONE);
                if (garage.equals("Shenkman Hall**") || garage.equals("South Hall**")) {
                    linear_notes.setVisibility(View.VISIBLE);
                    tvNotes.setText("Shenkman and South Halls require a puck/transponder " +
                            "in order to access those garages. Students will need to pick " +
                            "it up from the Parking Services Office, located on the P1 level " +
                            "of the 2028 G St., NW Garage (P1-001, next to the entry/exit lanes) " +
                            "before the car can be parked. A $50 refundable deposit is required to " +
                            "receive the transponder. The $50 is returned when the puck/transponder is " +
                            "returned at the end of the semester or academic year.");
                }
                else if (garage.equals("Academic Center") || garage.equals("G Street") || garage.equals("Science and Engineering Hall")){
                    linear_notes.setVisibility(View.VISIBLE);
                    tvNotes.setText("GW has installed electric vehicle charging stations in the Academic " +
                            "Center, Law Learning Center/G Street Garage and Science and Engineering Hall " +
                            "Parking Garages. Learn more about the ChargePoint Network or email " +
                            "tve@gwu.edu if you have questions about the on-campus stations.");
                }
            }
            else if (campus_location.equals("Mount Vernon Campus")){
                tvCampus = (TextView) findViewById(R.id.Garage1_rate_details);
                tvCampus.setText(campus_location);
                tvRates1 = (TextView) findViewById(R.id.Garage1_rates1);
                tvRates1.setText("Daily Rate (0-3 hrs): Free" + "\n"+
                        "Daily Rate (3-24 hrs): $13" + "\n"+
                        "Daily Rate (after 5pm): $7" + "\n"+
                        "Daily Weekend Rate: Free" + "\n"+
                        "\n" +
                        "*Included 18% parking tax.");


                tvNotes.setText("In an effort to better utilize the available " +
                        "parking on the Mount Vernon Campus and support " +
                        "the neighboring community, Parking Services will " +
                        "now be providing the first three (3) hours of parking " +
                        "for free in the MVC parking garage for all students, " +
                        "faculty, staff, and visitors.  Garage parking is still " +
                        "free on weekends (Sat 12am – Sun 11:59pm). \n" +
                        "\n" +
                        "Please note that parking on Whitehaven Pkwy NW is reserved " +
                        "for St. Patrick’s Episcopal Church and Day School. " +
                        "Parking on the roads and fire lanes on campus is also prohibited.");
            }
            else {
                linear_notes  = (LinearLayout) findViewById(R.id.notes_title);;
                linear_notes.setVisibility(View.GONE);
                tvCampus = (TextView) findViewById(R.id.Garage1_rate_details);
                tvCampus.setText(campus_location);
                tvRates1 = (TextView) findViewById(R.id.Garage1_rates1);
                tvRates1.setText("Daily parking is available at no charge for VSTC students, " +
                        "faculty and staff at this campus." + "\n"+
                        "\n" +
                        "*Included 18% parking tax.");
            }
            Toast.makeText(getBaseContext(), garage, Toast.LENGTH_LONG).show();
        }

        rb.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        Toast.makeText(getApplicationContext(), "Rating: " + rating, Toast.LENGTH_SHORT).show();
                    }
                });

        // initiate a Switch
        final Switch simpleSwitch = (Switch) findViewById(R.id.simpleSwitch);
        simpleSwitch.setChecked(true);
        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if ( simpleSwitch.isChecked() ) {
                    simpleSwitch.setText("Like");
                } else {
                    simpleSwitch.setText("Dislike");
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
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng),17.0f));
    }

    //Display dialog
    public void showDialog(String garage) {
        final Dialog dialog = new Dialog(MapsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_form);

        //Initializing the views of the dialog.
        final DatePicker simpleDatePicker;
        simpleDatePicker = dialog.findViewById(R.id.simpleDatePicker);
        final TimePicker simpleTimePicker;
        simpleTimePicker = dialog.findViewById(R.id.timepicker);
        final EditText tvTime = dialog.findViewById(R.id.time);
        Button submitButton = dialog.findViewById(R.id.submit_button);

        simpleTimePicker.setIs24HourView(true);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day = (simpleDatePicker.getMonth() + 1) + "/" + simpleDatePicker.getDayOfMonth() +"/" +
                        simpleDatePicker.getYear();
                String time = tvTime.getText().toString();
                if(TextUtils.isEmpty(time)){
                    tvTime.setError( "Parking hr(s) is required!" );
                } else {
                    int hour, minute;
                    String am_pm;
                    String selected_time;
                    if (Build.VERSION.SDK_INT >= 23 ){
                        hour = simpleTimePicker.getHour();
                        minute = simpleTimePicker.getMinute();
                    }
                    else{
                        hour = simpleTimePicker.getCurrentHour();
                        minute = simpleTimePicker.getCurrentMinute();
                    }
                    if(hour > 12) {
                        am_pm = "PM";
                        hour = hour - 12;
                    }
                    else
                    {
                        am_pm="AM";
                    }
                    selected_time = hour +":"+ minute+" "+am_pm;
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("Reservation", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Campus", campus_location);
                    editor.putString("Garage", garage);
                    editor.putString("Date", day);
                    editor.putString("Time", selected_time);
                    editor.putString("Hours", time);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "You have successfully reserved!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            }
        });

        dialog.show();
    }

    public void showrates(View view){
        linear_rates.setVisibility(View.VISIBLE);
    }

    private void getMap() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            String garage = bundle.getString("garage");
            String address = bundle.getString("address");
            String latitude = bundle.getString("latitude");
            String longitude = bundle.getString("longitude");

            addMarkerToMap(latitude, longitude, address, garage);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getMap();
    }

}