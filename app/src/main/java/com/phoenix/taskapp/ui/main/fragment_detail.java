package com.phoenix.taskapp.ui.main;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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
import com.phoenix.taskapp.utils.BottomSheetCurr;
import com.phoenix.taskapp.utils.BottomSheetEducator;
import com.phoenix.taskapp.utils.BottomSheetOnlyOwn;
import com.phoenix.taskapp.utils.BottomSheetSeries;
import com.phoenix.taskapp.utils.BottomSheetSkill;
import com.phoenix.taskapp.utils.BottomSheetStyle;
import com.phoenix.taskapp.viewmodels.SharedViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

    MaterialCardView cardCurriculum;
    MaterialTextView filtercurriculum;
    LinearLayout layoutcurriculum;

    MaterialCardView cardStyle;
    MaterialTextView filterstyle;
    LinearLayout layoutstyle;

    MaterialCardView cardEducator;
    MaterialTextView filtereducator;
    LinearLayout layouteducator;

    MaterialCardView cardSeries;
    MaterialTextView filterseries;
    LinearLayout layoutseries;

    MaterialCardView cardclear;
    MaterialTextView filterclear;
    LinearLayout layoutclear;

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

        cardCurriculum = view.findViewById(R.id.card_curriculum);
        filtercurriculum= view.findViewById(R.id.filtercurriculum);
        layoutcurriculum = view.findViewById(R.id.layout_filtercurriculum);

        cardStyle = view.findViewById(R.id.card_style);
        filterstyle= view.findViewById(R.id.filterstyle);
        layoutstyle = view.findViewById(R.id.layout_filterstyle);

        cardEducator = view.findViewById(R.id.card_educator);
        filtereducator= view.findViewById(R.id.filtereducator);
        layouteducator = view.findViewById(R.id.layout_filtereducator);

        cardSeries = view.findViewById(R.id.card_series);
        filterseries= view.findViewById(R.id.filterseries);
        layoutseries = view.findViewById(R.id.layout_filterseries);

        cardclear = view.findViewById(R.id.card_clear);
        filterclear= view.findViewById(R.id.filterclear);
        layoutclear = view.findViewById(R.id.layout_filterclear);

        layoutonly.setOnClickListener(view1 -> {
            BottomSheetOnlyOwn dialog = new BottomSheetOnlyOwn();
            dialog.show(getChildFragmentManager(), "Bottom");
        });

        layoutskill.setOnClickListener(view12 -> {

            BottomSheetSkill dialog = new BottomSheetSkill();
            dialog.show(getChildFragmentManager(), "Skill");
        });

        layoutcurriculum.setOnClickListener(view13 -> {

            BottomSheetCurr dialog = new BottomSheetCurr();
            dialog.show(getChildFragmentManager(), "Curriculum");
        });

        layoutstyle.setOnClickListener(view14 -> {

            BottomSheetStyle dialog = new BottomSheetStyle();
            dialog.show(getChildFragmentManager(), "Style");
        });

        layouteducator.setOnClickListener(view15 -> {

            BottomSheetEducator dialog = new BottomSheetEducator();
            dialog.show(getChildFragmentManager(), "Educator");
        });


        layoutseries.setOnClickListener(view16 -> {

            BottomSheetSeries dialog = new BottomSheetSeries();
            dialog.show(getChildFragmentManager(), "Series");
        });

        getChildFragmentManager().setFragmentResultListener("request",
                getViewLifecycleOwner(), (requestKey, result) -> {
                    filterOnlyown.setText(result.getString("selectedItem"));
                    if(result.getString("selectedItem").toLowerCase(Locale.ROOT).equals("yes"))
                    {
                        adapter.getFilter().filter("ownedyes");
                    }
                    else
                    {
                        adapter.getFilter().filter("ownedno");
                    }
                    checkForNull();
                    cardonly.setChecked(true);
                    checkforClear();
                });

        getChildFragmentManager().setFragmentResultListener("requestSkill",
                getViewLifecycleOwner(), (requestKey, result) -> {
                    filterskill.setText(result.getString("selectedItemSkill"));
                    adapter.getFilter().filter(result.getString("selectedItemSkill"));
                    checkForNull();
                    cardskill.setChecked(true);
                    checkforClear();
                });

        getChildFragmentManager().setFragmentResultListener("requestCurr",
                getViewLifecycleOwner(), (requestKey, result) -> {
                    filtercurriculum.setText(result.getString("selectedItemCurr"));
                    adapter.getFilter().filter(result.getString("selectedItemCurr"));
                    checkForNull();
                    cardCurriculum.setChecked(true);
                    checkforClear();
                });

        getChildFragmentManager().setFragmentResultListener("requestStyle",
                getViewLifecycleOwner(), (requestKey, result) -> {
                    filterstyle.setText(result.getString("selectedItemStyle"));
                    adapter.getFilter().filter(result.getString("selectedItemStyle"));
                    checkForNull();
                    cardskill.setChecked(true);
                    checkforClear();
                });

        getChildFragmentManager().setFragmentResultListener("requestEducator",
                getViewLifecycleOwner(), (requestKey, result) -> {
                    filtereducator.setText(result.getString("selectedItemEducator"));
                    adapter.getFilter().filter(result.getString("selectedItemEducator"));
                    checkForNull();
                    cardEducator.setChecked(true);
                    checkforClear();
                });

        getChildFragmentManager().setFragmentResultListener("requestSeries",
                getViewLifecycleOwner(), (requestKey, result) -> {
                    filterseries.setText(result.getString("selectedItemSeries"));
                    adapter.getFilter().filter(result.getString("selectedItemSeries"));
                    checkForNull();
                    cardSeries.setChecked(true);
                    checkforClear();
                });

        cardclear.setOnClickListener(view17 -> {
            clearFilters();
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
                    checkForNull();
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

    void checkForNull()
    {
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if(adapter.getItemCount() == 0){
                    nosearch.setVisibility(View.VISIBLE);
                }
                else
                {
                    nosearch.setVisibility(View.GONE);
                }
            }
        });
    }
    
    void checkforClear()
    {
        if((cardonly.isChecked() || cardskill.isChecked()
                || cardCurriculum.isChecked() || cardStyle.isChecked() ||
                cardEducator.isChecked() || cardSeries.isChecked()))
        {
            cardclear.setVisibility(View.VISIBLE);
        }
        else
        {
            cardclear.setVisibility(View.GONE);
        }
    }

    void clearFilters()
    {
        cardonly.setChecked(false);
        cardskill.setChecked(false);
        cardCurriculum.setChecked(false);
        cardStyle.setChecked(false);
        cardEducator.setChecked(false);
        cardSeries.setChecked(false);

        adapter.getFilter().filter("");

        filterskill.setText("");
        filterOnlyown.setText(requireActivity().getString(R.string.no));
        filtercurriculum.setText("");
        filterstyle.setText("");
        filtereducator.setText("");
        filterseries.setText("");

        cardclear.setVisibility(View.GONE);
    }
}
