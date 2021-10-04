package com.phoenix.taskapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.phoenix.taskapp.R;
import com.phoenix.taskapp.classes.FilterObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ItemHolder> implements Filterable {

    List<FilterObject> ownedItems;
    Context context;
    List<FilterObject> searchList;


    public DetailAdapter(Context context, List<FilterObject> items) {
        this.ownedItems = items;
        this.context = context;
        searchList = new ArrayList<>(ownedItems);
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail, null);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        FilterObject items = ownedItems.get(position);
        holder.header.setText(items.getTitle());
        holder.educator.setText(items.getEducator());

        if (getItemCount() == 0) {
            holder.tvnoresult.setVisibility(View.VISIBLE);
        } else {
            holder.tvnoresult.setVisibility(View.GONE);
        }
        Picasso.get().load(context.getString(R.string.imgUrl, items.getId()))
                .fit()
                .centerCrop()
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return ownedItems.size();
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }

    public int getFilterSize() {
        return searchList.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        MaterialTextView header, educator, tvnoresult;
        ImageView img;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.detail_item_title);
            educator = itemView.findViewById(R.id.detail_item_educator);
            img = itemView.findViewById(R.id.detail_item_image);
            tvnoresult = itemView.findViewById(R.id.tv_noresult);
        }
    }

    private final Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<FilterObject> filterObjects = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filterObjects.addAll(searchList);
            } else {
                String filterPattern = charSequence.toString().toLowerCase(Locale.ROOT).trim();
                for (FilterObject object : searchList) {
                    if (object.getSkills().contains("Intermediate")) {
                        Log.d("Response", object.getTitle());
                    }

                    if (object.getEducator().toLowerCase(Locale.ROOT).contains(filterPattern) ||
                            object.getTitle().toLowerCase(Locale.ROOT).contains(filterPattern) ||
                            object.getSkills().contains(charSequence) ||
                            object.getCurriculum().contains(charSequence) ||
                            object.getStyles().contains(charSequence)) {
                        filterObjects.add(object);
                    }

                }
            }

            FilterResults results = new FilterResults();
            results.values = filterObjects;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ownedItems.clear();
            ownedItems.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

}
