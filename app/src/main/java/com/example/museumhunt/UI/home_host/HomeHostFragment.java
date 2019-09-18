package com.example.museumhunt.UI.home_host;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.museumhunt.R;
import com.example.museumhunt.UI.home_artifacts_list.HomeArtifactsListFragment;

public class HomeHostFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        HomeArtifactsListFragment nextFrag= new HomeArtifactsListFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.host_home, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit();

        return root;
    }
}