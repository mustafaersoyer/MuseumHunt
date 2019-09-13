package com.example.museumhunt.UI.change_location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.museumhunt.Model.Location;
import com.example.museumhunt.R;

import java.util.ArrayList;
import java.util.List;

public class ChangeLocationFragment extends Fragment {
    ChangeLocationViewModel changeLocationViewModel;
    Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_change_location, container, false);
        Toast.makeText(getContext(), "location change", Toast.LENGTH_SHORT).show();

        spinner = root.findViewById(R.id.spinnerLocations);
        List<String> spinnerArray =  new ArrayList<String>();


        changeLocationViewModel.getAllLocation().observe(this, new Observer<List<Location>>() {
            @Override
            public void onChanged(@Nullable List<Location> locationList) {

            }
        });
        return root;
    }
}
