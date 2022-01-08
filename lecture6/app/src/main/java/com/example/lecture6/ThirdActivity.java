package com.example.lecture6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ThirdActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mixed);
    }

    public void onClickBack(View view){
        Intent intent=new Intent(ThirdActivity.this, SecondActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
