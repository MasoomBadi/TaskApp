package com.phoenix.taskapp.adapters;

import android.content.Context;
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

        List<String> ids = new ArrayList<>();
        ids.addAll(ownedItems.get(0).getOwnedIds());
        if(ids.contains(items.getId())){
            holder.labelowned.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.labelowned.setVisibility(View.GONE);
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

    static class ItemHolder extends RecyclerView.ViewHolder {

        MaterialTextView header, educator, tvnoresult, labelowned;
        ImageView img;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.detail_item_title);
            educator = itemView.findViewById(R.id.detail_item_educator);
            img = itemView.findViewById(R.id.detail_item_image);
            tvnoresult = itemView.findViewById(R.id.tv_noresult);
            labelowned = itemView.findViewById(R.id.owned_label);
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
                if (filterPattern.toLowerCase(Locale.ROOT).equals("ownedyes")) {
                    List<String> ownedId = new ArrayList<>();
                    if (searchList.get(0).getOwnedIds() != null) {
                        ownedId.addAll(searchList.get(0).getOwnedIds());
                        for (int i = 0; i < ownedId.size(); i++) {
                            String id = ownedId.get(i);
                            for (int j = 0; j < searchList.size(); j++) {
                                if (searchList.get(j).getId().equals(id)) {
                                    filterObjects.add(searchList.get(j));
                                }
                            }
                        }
                    }
                }
                for (FilterObject object : searchList) {
                    if (object.getEducator().toLowerCase(Locale.ROOT).contains(filterPattern) ||
                            object.getTitle().toLowerCase(Locale.ROOT).contains(filterPattern) ||
                            object.getSkills().contains(charSequence) ||
                            object.getCurriculum().contains(charSequence) ||
                            object.getStyles().contains(charSequence) ||
                            object.getEducator().toLowerCase(Locale.ROOT).contains(filterPattern)) {
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
