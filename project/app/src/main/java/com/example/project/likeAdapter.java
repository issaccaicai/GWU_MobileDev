package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class likeAdapter extends RecyclerView.Adapter<likeAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<Like> likeArrayList;
    private Context context;

    // creating a constructor for our variables.
    public likeAdapter(ArrayList<Like> likeArrayList, Context context) {
        this.likeArrayList = likeArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public likeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull likeAdapter.ViewHolder holder, int position) {
        Like modal = likeArrayList.get(position);
        holder.garages.setText(modal.getAddress());
        holder.address.setText(modal.getGarage());
    }

    @Override
    public int getItemCount() {
        return likeArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView garages, address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            garages = itemView.findViewById(R.id.idTVGarage);
            address = itemView.findViewById(R.id.idTVAddress);
        }
    }
}