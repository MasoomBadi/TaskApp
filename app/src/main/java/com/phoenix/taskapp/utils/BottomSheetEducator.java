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
import com.phoenix.taskapp.classes.IndexClass;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetEducator extends BottomSheetDialogFragment {

    parseResponse parseResponse = new parseResponse();
    List<IndexClass> indexList;
    ArrayList<String> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_filtereducator, container, false);

        indexList = new ArrayList<>();
        list = new ArrayList<>();

        try {
            indexList = parseResponse.parseIndex(requireContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < indexList.size(); i++) {
                String educator = indexList.get(i).getEducator();
                if (!list.contains(educator)) {
                    list.add(educator);

            }
        }


        ListView listView = view.findViewById(R.id.list_educator);
        listView.setAdapter(new ListAdapter(requireContext(), list, new ListAdapter.OnClickListener() {
            @Override
            public void onClick(String input) {
                Bundle bundle = new Bundle();
                bundle.putString("selectedItemEducator", input);
                getParentFragmentManager().setFragmentResult("requestEducator", bundle);
                dismiss();
            }
        }));

        return view;
    }
}
