package com.phoenix.taskapp.ui.main;


import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.phoenix.taskapp.R;
import com.phoenix.taskapp.adapters.DetailAdapter;
import com.phoenix.taskapp.classes.FilterObject;
import com.phoenix.taskapp.utils.BottomSheetOnlyOwn;
import com.phoenix.taskapp.utils.BottomSheetSkill;
import com.phoenix.taskapp.viewmodels.SharedViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class fragment_detail extends Fragment{
    String curLabel;
    TextInputLayout searchInputLayout;
    TextInputEditText searchEditText;
    RecyclerView rvDetail;
    DetailAdapter adapter;

    MaterialTextView nosearch;

    List<String> ownedIDs;
    List<FilterObject> catList;

    MaterialCardView cardonly;
    MaterialTextView filterOnlyown;
    LinearLayout layoutonly;

    MaterialCardView cardskill;
    MaterialTextView filterskill;
    LinearLayout layoutskill;

    public fragment_detail(){
        super(R.layout.layout_fragmentdetail);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchEditText = view.findViewById(R.id.et_search);
        searchInputLayout = view.findViewById(R.id.til_search);

        rvDetail = view.findViewById(R.id.rv_filteritems);
        nosearch = view.findViewById(R.id.tv_noresult);

        cardonly = view.findViewById(R.id.card_onlyowned);
        cardonly.setSelected(true);
        filterOnlyown = view.findViewById(R.id.filterOnlyown);
        layoutonly = view.findViewById(R.id.layout_filteronlyown);

        cardskill = view.findViewById(R.id.card_skill);
        filterskill= view.findViewById(R.id.filterskill);
        layoutskill = view.findViewById(R.id.layout_filterskill);

        layoutonly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetOnlyOwn dialog = new BottomSheetOnlyOwn();
                dialog.show(getChildFragmentManager(), "Bottom");
            }
        });

        layoutskill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BottomSheetSkill dialog = new BottomSheetSkill();
                dialog.show(getChildFragmentManager(), "Skill");
            }
        });


        getChildFragmentManager().setFragmentResultListener("request",
                getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                       filterOnlyown.setText(result.getString("selectedItem"));
                    }
                });

        getChildFragmentManager().setFragmentResultListener("requestSkill",
                getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        filterskill.setText(result.getString("selectedItemSkill"));
                        adapter.getFilter().filter(result.getString("selectedItemSkill"));
                        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                            @Override
                            public void onChanged() {
                                super.onChanged();
                                if(adapter.getItemCount() == 0){
                                    Toast.makeText(requireContext(), "Zero", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
        catList = new ArrayList<>();
        ownedIDs = new ArrayList<>();

        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        model.getItems().observeForever(ownedItems -> catList.addAll(ownedItems));
        model.getIds().observeForever(strings -> ownedIDs.addAll(strings));
        model.getLabel().observeForever(label-> curLabel=label);

        searchInputLayout.setHint(requireActivity().getString(R.string.search_hint, curLabel));
        RecyclerView.LayoutManager manager = new GridLayoutManager(requireActivity(), 2, RecyclerView.VERTICAL, false);
        rvDetail.setLayoutManager(manager);
        rvDetail.setItemAnimator(new DefaultItemAnimator());
        adapter = new DetailAdapter(requireContext(), catList);
        rvDetail.setAdapter(adapter);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(adapter.getFilter() != null)
                {
                    adapter.getFilter().filter(charSequence);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar())
                .setTitle(curLabel);
    }
}
