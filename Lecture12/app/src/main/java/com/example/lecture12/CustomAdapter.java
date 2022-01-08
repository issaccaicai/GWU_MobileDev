package com.example.lecture12;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<itemModel> arrayList;

    public CustomAdapter(Context context, ArrayList<itemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public  View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView ==  null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fb_list_test, parent, false);
        }
        TextView garage, address, note,permit;
        ImageView image;
        RatingBar rating;
        garage = (TextView) convertView.findViewById(R.id.Garage1);
        address = (TextView) convertView.findViewById(R.id.Garage1_address_details1);
        note = (TextView) convertView.findViewById(R.id.Garage1_address_details2);
        permit = (TextView) convertView.findViewById(R.id.Garage1_Permit_details);
        rating = (RatingBar) convertView.findViewById(R.id.Garage1_RatingBar);
        image = (ImageView) convertView.findViewById(R.id.garage1_img);

        garage.setText(arrayList.get(position).getGarage());
        address.setText(arrayList.get(position).getAddress());
        note.setText(arrayList.get(position).getNote());
        permit.setText(arrayList.get(position).getPermit());
        rating.setRating(arrayList.get(position).getRating());
        image.setImageResource(arrayList.get(position).getImage());
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
//                ratingBar.setRating(v);
            }
        });
        return convertView;
    }
}
