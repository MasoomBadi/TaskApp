package com.phoenix.taskapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.android.material.textview.MaterialTextView;
import com.phoenix.taskapp.R;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<String> items;
    private final OnClickListener listener;

    public ListAdapter(Context context, ArrayList<String> items, OnClickListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        if (view == null) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.item_bottomlist, viewGroup, false);
        }
        String currentItem = (String) getItem(i);
        MaterialTextView tv = view.findViewById(R.id.list_option);
        tv.setText(currentItem);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(currentItem);
            }
        });
        return view;
    }

    public interface OnClickListener
    {
        void onClick(String input);
    }
}
