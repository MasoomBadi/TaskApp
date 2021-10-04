package com.phoenix.taskapp.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.phoenix.taskapp.R;
import com.phoenix.taskapp.adapters.ItemAdapter;
import com.phoenix.taskapp.adapters.OwnedAdapter;
import com.phoenix.taskapp.classes.FilterObject;
import com.phoenix.taskapp.classes.IndexClass;
import com.phoenix.taskapp.utils.parseResponse;
import com.phoenix.taskapp.viewmodels.SharedViewModel;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class fragment_main extends Fragment {

    HashMap<String, List<String>> hashMap;
    List<IndexClass> indexList;
    List<FilterObject> ownedItems;
    List<FilterObject> recentItems;
    List<String> userOwnedIds;
    List<FilterObject> favouriteItems;
    List<FilterObject> wishlistItems;
    MaterialTextView viewOwned, viewRecent, viewFavorite, viewWishlist;
    SharedViewModel viewModel;
    RecyclerView rvOwned, rvRecent, rvFavourites, rvWishlist;

    public fragment_main() {
        super(R.layout.layout_fragmentmain);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        parseResponse parseResponse = new parseResponse();

        hashMap = new HashMap<>();
        indexList = new ArrayList<>();
        try {
            hashMap = parseResponse.parseCollection(requireContext());
            indexList = parseResponse.parseIndex(requireContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ownedItems = new ArrayList<>();
        recentItems = new ArrayList<>();
        favouriteItems = new ArrayList<>();
        wishlistItems = new ArrayList<>();
        userOwnedIds = new ArrayList<>();

        rvOwned = view.findViewById(R.id.rv_ownedItems);
        rvRecent = view.findViewById(R.id.rv_recentItems);
        rvFavourites = view.findViewById(R.id.rv_favoriteitems);
        rvWishlist = view.findViewById(R.id.rv_wishlist);

        viewOwned = view.findViewById(R.id.header_viewall_owned);
        viewRecent = view.findViewById(R.id.header_viewall_recent);
        viewFavorite = view.findViewById(R.id.header_viewall_favorite);
        viewWishlist = view.findViewById(R.id.header_viewall_wishlist);

        userOwnedIds = hashMap.get("Owned");

        loadOwnedItems();
        loadRecentItems();
        loadFavourites();
        loadWishlist();

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    void loadOwnedItems() {
        List<String> ownedCoursesID = hashMap.get("Owned");
        if (ownedCoursesID != null) {
            for (int i = 0; i < ownedCoursesID.size(); i++) {
                String courseID = ownedCoursesID.get(i);
                for (int j = 0; j < indexList.size(); j++) {
                    if (courseID.equals(indexList.get(j).getId())) {
                        ownedItems.add(new FilterObject(userOwnedIds,
                                indexList.get(j).getSkillTag(),
                                indexList.get(j).getCurriculumTag(),
                                indexList.get(j).getStyleTag(),
                                indexList.get(j).getSeriesTag(),
                                indexList.get(j).getEducator(),
                                indexList.get(j).getId(),
                                indexList.get(j).getTitle()));
                    }
                }
            }
        }

        OwnedAdapter adapter = new OwnedAdapter(requireContext(), ownedItems);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);

        rvOwned.setLayoutManager(manager);
        rvOwned.setItemAnimator(new DefaultItemAnimator());
        rvOwned.setAdapter(adapter);

        viewOwned.setOnClickListener(view1 -> {
            viewModel.setItems(ownedItems, userOwnedIds, "Owned");
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.main_container, new fragment_detail())
                    .addToBackStack(null)
                    .commit();
        });


    }

    void loadRecentItems() {
        List<String> ownedCoursesID = hashMap.get("Recently Watched");
        if (ownedCoursesID != null) {
            for (int i = 0; i < ownedCoursesID.size(); i++) {
                String courseID = ownedCoursesID.get(i);
                for (int j = 0; j < indexList.size(); j++) {
                    if (courseID.equals(indexList.get(j).getId())) {
                        recentItems.add(new FilterObject(userOwnedIds,
                                indexList.get(j).getSkillTag(),
                                indexList.get(j).getCurriculumTag(),
                                indexList.get(j).getStyleTag(),
                                indexList.get(j).getSeriesTag(),
                                indexList.get(j).getEducator(),
                                indexList.get(j).getId(),
                                indexList.get(j).getTitle()));
                    }
                }
            }
        }

        ItemAdapter adapter = new ItemAdapter(requireContext(), recentItems);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);

        rvRecent.setLayoutManager(manager);
        rvRecent.setItemAnimator(new DefaultItemAnimator());
        rvRecent.setAdapter(adapter);

        viewRecent.setOnClickListener(view1 -> {
            viewModel.setItems(recentItems, userOwnedIds, "Recently Watched");
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.main_container, new fragment_detail())
                    .addToBackStack(null)
                    .commit();
        });
    }

    void loadFavourites() {
        List<String> ownedCoursesID = hashMap.get("Favorites");
        if (ownedCoursesID != null) {
            for (int i = 0; i < ownedCoursesID.size(); i++) {
                String courseID = ownedCoursesID.get(i);
                for (int j = 0; j < indexList.size(); j++) {
                    if (courseID.equals(indexList.get(j).getId())) {
                        favouriteItems.add(new FilterObject(userOwnedIds,
                                indexList.get(j).getSkillTag(),
                                indexList.get(j).getCurriculumTag(),
                                indexList.get(j).getStyleTag(),
                                indexList.get(j).getSeriesTag(),
                                indexList.get(j).getEducator(),
                                indexList.get(j).getId(),
                                indexList.get(j).getTitle()));
                    }
                }
            }
        }

        ItemAdapter adapter = new ItemAdapter(requireContext(), favouriteItems);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);

        rvFavourites.setLayoutManager(manager);
        rvFavourites.setItemAnimator(new DefaultItemAnimator());
        rvFavourites.setAdapter(adapter);

        viewFavorite.setOnClickListener(view1 -> {
            viewModel.setItems(favouriteItems, userOwnedIds, "Favorites");
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.main_container, new fragment_detail())
                    .addToBackStack(null)
                    .commit();
        });
    }

    void loadWishlist() {
        List<String> ownedCoursesID = hashMap.get("Wish List");
        if (ownedCoursesID != null) {
            for (int i = 0; i < ownedCoursesID.size(); i++) {
                String courseID = ownedCoursesID.get(i);
                for (int j = 0; j < indexList.size(); j++) {
                    if (courseID.equals(indexList.get(j).getId())) {
                        wishlistItems.add(new FilterObject(userOwnedIds,
                                indexList.get(j).getSkillTag(),
                                indexList.get(j).getCurriculumTag(),
                                indexList.get(j).getStyleTag(),
                                indexList.get(j).getSeriesTag(),
                                indexList.get(j).getEducator(),
                                indexList.get(j).getId(),
                                indexList.get(j).getTitle()));
                    }
                }
            }
        }

        ItemAdapter adapter = new ItemAdapter(requireContext(), wishlistItems);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);

        rvWishlist.setLayoutManager(manager);
        rvWishlist.setItemAnimator(new DefaultItemAnimator());
        rvWishlist.setAdapter(adapter);

        viewWishlist.setOnClickListener(view1 -> {
            viewModel.setItems(wishlistItems, userOwnedIds, "Wish List");
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.main_container, new fragment_detail())
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar())
                .setTitle(requireActivity().getString(R.string.app_name));

    }
}
