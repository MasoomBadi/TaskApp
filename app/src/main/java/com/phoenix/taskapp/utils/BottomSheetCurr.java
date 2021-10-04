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

public class BottomSheetCurr extends BottomSheetDialogFragment {

    parseResponse parseResponse = new parseResponse();
    List<IndexClass> indexList;
    ArrayList<String> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_filtercurriculum, container, false);

        indexList = new ArrayList<>();
        list = new ArrayList<>();

        try {
            indexList = parseResponse.parseIndex(requireContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < indexList.size(); i++){
            List<String> curList = new ArrayList<>();
            curList = indexList.get(i).getCurriculumTag();
            for(int j = 0; j < curList.size(); j++)
            {
                String skill = curList.get(j);
                if(!list.contains(skill))
                {
                    list.add(skill);
                }
            }
        }



        ListView listView = view.findViewById(R.id.list_curriculums);
        listView.setAdapter(new ListAdapter(requireContext(), list, new ListAdapter.OnClickListener() {
            @Override
            public void onClick(String input) {
                Bundle bundle = new Bundle();
                bundle.putString("selectedItemCurr", input);
                getParentFragmentManager().setFragmentResult("requestCurr", bundle);
                dismiss();
            }
        }));

        return view;
    }
}
