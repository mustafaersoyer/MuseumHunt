package com.example.museumhunt.UI.settings_host;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.museumhunt.R;
import com.example.museumhunt.UI.settings_main.SettingsMainFragment;

public class SettingsHostFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        SettingsMainFragment nextFrag= new SettingsMainFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.host_settings_fragment, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit();
        return root;
    }
}
