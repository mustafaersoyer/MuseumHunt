package com.example.museumhunt.ui.settings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class SettingsViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> settingList;
    ArrayList<String> arrayList;

    public SettingsViewModel() {
        arrayList = new ArrayList<>();
        arrayList.add("Change your museum location");
        arrayList.add("Campaigns");
        arrayList.add("About");
        settingList = new MutableLiveData<>();
        settingList.setValue(arrayList);
    }

}