package com.example.lecture6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_relative);
    }

    public void onClickNext(View view){
        Intent intent=new Intent(SecondActivity.this, ThirdActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    public void onClickBack(View view){
        Intent intent=new Intent(SecondActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
