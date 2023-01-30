package com.example.flowers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.ViewHolder> {

    private final static String PHOTO_URL = "http://services.hanselandpetal.com/photos/";
    private List<Flower> flowers;
    private Context context;

    FlowerAdapter(List<Flower> flowers) {
        this.flowers = flowers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Flower flower = flowers.get(position);

        holder.nameTextView.setText(flower.getName());
        Picasso.with(context).load(PHOTO_URL + flower.getPhoto())
                .resize(200, 150)
                .into(holder.flowerImageView);

    }

    @Override
    public int getItemCount() {
        if (flowers == null) {
            return 0;
        }
        return flowers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView flowerImageView;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            flowerImageView = (ImageView) itemView.findViewById(R.id.itemImageView);
        }
    }
}
