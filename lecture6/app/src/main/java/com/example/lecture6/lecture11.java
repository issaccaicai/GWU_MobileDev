package com.example.lecture6;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class lecture11 extends AppCompatActivity {
    String imageLabel = "lion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture11);

        final ImageView simpleImageView = (ImageView) findViewById(R.id.simpleImageView);

        // initiate a Switch
        final Switch simpleSwitch = (Switch) findViewById(R.id.simpleSwitch);

        // initiate rating bar and a button
        final RatingBar simpleRatingBar = (RatingBar) findViewById(R.id.simpleRatingBar);
        Button submitButton = (Button) findViewById(R.id.submitButton);

        simpleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageLabel == "lion") {
                    simpleSwitch.setChecked(false);
                    simpleSwitch.setText("Monkey");
                    simpleImageView.setImageResource(R.drawable.monkey);
                    imageLabel = "monkey";

                } else {
                    simpleSwitch.setChecked(true);
                    simpleSwitch.setText("Lion");
                    simpleImageView.setImageResource(R.drawable.lion);
                    imageLabel = "lion";
                }
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get values and then displayed in a toast
                String totalStars = "Total Stars: " + simpleRatingBar.getNumStars();
                String rating = "Rating : " + simpleRatingBar.getRating();
                Toast.makeText(getApplicationContext(), totalStars + "\n" + rating, Toast.LENGTH_LONG).show();
            }
        });
        //set the current state of a Switch
        simpleSwitch.setChecked(true);
        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if ( simpleSwitch.isChecked() ) {
                    if (imageLabel == "lion") {
                        simpleImageView.setImageResource(R.drawable.monkey);
                        imageLabel = "monkey";
                    } else {
                        simpleImageView.setImageResource(R.drawable.lion);
                        imageLabel = "lion";
                    }
                    simpleSwitch.setText("Lion");
                } else {
                    simpleSwitch.setText("Monkey");
                    if (imageLabel == "lion") {
                        simpleImageView.setImageResource(R.drawable.monkey);
                        imageLabel = "monkey";
                    } else {
                        simpleImageView.setImageResource(R.drawable.lion);
                        imageLabel = "lion";
                    }
                }
            }
        });
    }

}