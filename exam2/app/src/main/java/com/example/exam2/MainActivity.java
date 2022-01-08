package com.example.exam2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String imageLabel = "first_image";
    String record;
    String image5_option;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button dislikeButton = (Button) findViewById(R.id.Dislike);
        Button likeButton = (Button) findViewById(R.id.Like);
        final ImageView simpleImageView = (ImageView) findViewById(R.id.displayImage);

        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageLabel == "first_image") {
                    simpleImageView.setImageResource(R.drawable.image2);
                    imageLabel = "second_image";
                    record = "image1Dislike";
                } else if (imageLabel == "second_image") {
                    simpleImageView.setImageResource(R.drawable.image3);
                    imageLabel = "third_image";
                    record = record + "\n" +  "image2Dislike";
                } else if (imageLabel == "third_image") {
                    simpleImageView.setImageResource(R.drawable.image4);
                    imageLabel = "fourth_image";
                    record = record + "\n" +  "image3Dislike";
                } else if (imageLabel == "fourth_image") {
                    simpleImageView.setImageResource(R.drawable.image5);
                    imageLabel = "last_image";
                    record = record + "\n" +  "image4Dislike";
                } else {
                    image5_option = "\n" +  "image5Dislike";
                    Toast.makeText(getApplicationContext(), record + image5_option, Toast.LENGTH_LONG).show();
                }
            }
        });
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageLabel == "first_image") {
                    simpleImageView.setImageResource(R.drawable.image2);
                    imageLabel = "second_image";
                    record = "image1Like";
                } else if (imageLabel == "second_image") {
                    simpleImageView.setImageResource(R.drawable.image3);
                    imageLabel = "third_image";
                    record = record + "\n" + "image2Like";
                } else if (imageLabel == "third_image") {
                    simpleImageView.setImageResource(R.drawable.image4);
                    imageLabel = "fourth_image";
                    record = record + "\n" + "image3Like";
                } else if (imageLabel == "fourth_image") {
                    simpleImageView.setImageResource(R.drawable.image5);
                    imageLabel = "last_image";
                    record = record + "\n" + "image4Like";
                } else {
                    image5_option = "\n" + "image5Like";
                    Toast.makeText(getApplicationContext(), record + image5_option, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}