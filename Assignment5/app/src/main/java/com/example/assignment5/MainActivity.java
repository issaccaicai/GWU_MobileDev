package com.example.assignment5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initiate image view
        final ImageView simpleImageView = (ImageView) findViewById(R.id.cat_image1);

        // initiate rating bar
        final RatingBar simpleRatingBar = (RatingBar) findViewById(R.id.simpleRatingBar);

        // initiate a button
        Button submitButton = (Button) findViewById(R.id.submit_button);

        // perform click event on button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float rating = simpleRatingBar.getRating();

                if (rating < 2.0) {
                    simpleImageView.setImageResource(R.drawable.image2);
                } else {
                    simpleImageView.setImageResource(R.drawable.image3);
                }
            }
        });
    }
}