package com.example.project;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private likeAdapter adapter;
    Like like;
    Like dislike;
    private ArrayList<Like> likeArrayList = new ArrayList<>();
    private ArrayList<Like> dislikeArrayList = new ArrayList<>();

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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_garage, parent, false);
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

        ImageButton favoriteBtn = (ImageButton) convertView.findViewById(R.id.bt_add);
        favoriteBtn.setFocusable(false);
        favoriteBtn.setFocusableInTouchMode(false);
        favoriteBtn.setTag(position);
        favoriteBtn.setOnClickListener(new View.OnClickListener() {
            String select = "Dislike";
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v) {
                if (select.equals("Dislike")) {
                    select = "Like";
                    favoriteBtn.setImageResource(R.drawable.favorite);
                    likeArrayList.add(new Like(arrayList.get(position).getGarage(), arrayList.get(position).getAddress()));
                    if (likeArrayList != null){
                        saveData();
                    }
                    Toast.makeText(context.getApplicationContext(),
                            "Favorite: " + arrayList.get(position).getGarage(), Toast.LENGTH_LONG).show();
                }
                else {
                    select = "Dislike";
                    favoriteBtn.setImageResource(R.drawable.not);
                    if (likeArrayList != null){
                        int listsize= likeArrayList.size();
                        for (int i = 0; i < listsize; i++) {
                            if(likeArrayList.get(i).getGarage()==arrayList.get(position).getGarage()){
                                likeArrayList.remove(likeArrayList.get(i));
                                i--;
                                listsize--;
                            }
                        }
                        saveData();
                    }
                }
            }
        });
        return convertView;
    }

    private void saveData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(likeArrayList);
        editor.putString("like", json);
        editor.apply();
    }
    private void removeData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(dislikeArrayList);
        editor.remove("like");
        editor.apply();
    }
}
