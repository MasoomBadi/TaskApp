package com.phoenix.taskapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.phoenix.taskapp.R;
import com.phoenix.taskapp.classes.FilterObject;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OwnedAdapter extends RecyclerView.Adapter<OwnedAdapter.ItemHolder>{

    List<FilterObject> ownedItems;
    Context context;

    public OwnedAdapter(Context context, List<FilterObject> items) {
        this.ownedItems = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_owned, null);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        FilterObject items = ownedItems.get(position);
        holder.header.setText(items.getTitle());
        holder.educator.setText(items.getEducator());

        Picasso.get().load(context.getString(R.string.imgUrl, items.getId()))
                .fit()
                .centerCrop()
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return ownedItems.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        MaterialTextView header, educator;
        ImageView img;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.owned_item_title);
            educator = itemView.findViewById(R.id.owned_item_educator);
            img = itemView.findViewById(R.id.owned_item_image);
        }
    }
}
