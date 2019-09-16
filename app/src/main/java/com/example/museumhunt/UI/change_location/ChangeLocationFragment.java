package com.example.museumhunt.UI.change_location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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
        changeLocationViewModel =
                ViewModelProviders.of(this).get(ChangeLocationViewModel.class);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        spinner = getActivity().findViewById(R.id.locationSpinner);

        ArrayList<String> arrayList = new ArrayList<>();
        changeLocationViewModel.getAllLocation().observe(this, new Observer<List<Location>>() {
            @Override
            public void onChanged(@Nullable List<Location> locationList) {
                Location location;
                for (int i=0; i<locationList.size();i++) {
                    location = locationList.get(i);
                    arrayList.add(location.getName());
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_spinner_item, arrayList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(dataAdapter);
            }
        });
    }
}
