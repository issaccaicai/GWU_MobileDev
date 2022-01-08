package com.example.teamproject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

public class parking_campus extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ImageView imageView = (ImageView) findViewById(R.id.campus1);
////        imageView.setClipToOutline(true);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.garage1);
//        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
//        roundedBitmapDrawable.setCornerRadius(30);
//        imageView.setImageDrawable(roundedBitmapDrawable);
    }
}
