package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ShelterAdapter extends RecyclerView.Adapter<ShelterAdapter.ShelterViewHolder> {

    private Context context;
    private List<Shelter> shelters;

    public ShelterAdapter(Context context, List<Shelter> shelters) {
        this.context = context;
        this.shelters = shelters;
    }

    @NonNull
    @Override
    public ShelterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_shelter, parent, false);
        return new ShelterViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ShelterViewHolder holder, int position) {
        Shelter shelter = shelters.get(position);

        // Load and display image using Glide from drawable folder
        int imageResId = context.getResources().getIdentifier(shelter.getImagePath(), "drawable", context.getPackageName());

        Glide.with(context)
                .load(imageResId)
                .into(holder.shelterImageView);

        // Set other attributes
        holder.cityTextView.setText(shelter.getCity());
        holder.addressTextView.setText(shelter.getAddress());

        // Add more bindings for other Shelter attributes as needed
    }

    @Override
    public int getItemCount() {
        return shelters.size();
    }

    public static class ShelterViewHolder extends RecyclerView.ViewHolder {
        ImageView shelterImageView;
        TextView cityTextView;
        TextView addressTextView;

        public ShelterViewHolder(@NonNull View itemView) {
            super(itemView);
            shelterImageView = itemView.findViewById(R.id.shelterImageView);
            cityTextView = itemView.findViewById(R.id.cityTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
        }
    }
}
