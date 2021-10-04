package com.phoenix.taskapp.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.phoenix.taskapp.R;
import com.phoenix.taskapp.adapters.ListAdapter;

import java.util.ArrayList;

public class BottomSheetOnlyOwn extends BottomSheetDialogFragment {
    ArrayList<String> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_filterown, container, false);
        list = new ArrayList<>();
        list.add("No");
        list.add("Yes");


        ListView listView = view.findViewById(R.id.list_bottomdialog);
        listView.setAdapter(new ListAdapter(requireContext(), list, new ListAdapter.OnClickListener() {
            @Override
            public void onClick(String input) {
                Bundle bundle = new Bundle();
                bundle.putString("selectedItem", input);
                getParentFragmentManager().setFragmentResult("request", bundle);
                dismiss();
            }
        }));

        return view;
    }
}
