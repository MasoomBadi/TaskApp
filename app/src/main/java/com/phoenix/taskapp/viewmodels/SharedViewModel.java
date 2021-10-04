package com.phoenix.taskapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.phoenix.taskapp.classes.FilterObject;

import java.util.List;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<List<FilterObject>> items = new MutableLiveData<>();
    private final MutableLiveData<List<String>> ownedIds = new MutableLiveData<>();
    private final MutableLiveData<String> label = new MutableLiveData<>();

    public void setItems(List<FilterObject> list, List<String> ids, String header){
        items.setValue(list);
        ownedIds.setValue(ids);
        label.setValue(header);
    }

    public LiveData<List<FilterObject>> getItems() {
        return items;
    }

    public LiveData<List<String>> getIds() {
        return ownedIds;
    }

    public LiveData<String> getLabel() {
        return label;
    }
}
