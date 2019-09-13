package com.example.museumhunt.UI.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.museumhunt.Adapters.HomeArtifactsAdapter;
import com.example.museumhunt.R;
import com.example.museumhunt.UI.home_artifacts_list.HomeArtifactsListFragment;
import com.example.museumhunt.UI.home_artifacts_list.HomeViewModel;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ImageView imageView;
    HomeArtifactsAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        HomeArtifactsListFragment nextFrag= new HomeArtifactsListFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.host_home, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit();

        return root;
    }
}