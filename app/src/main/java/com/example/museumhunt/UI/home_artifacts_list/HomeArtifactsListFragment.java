package com.example.museumhunt.UI.home_artifacts_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museumhunt.Adapters.HomeArtifactsAdapter;
import com.example.museumhunt.Model.Artifacts;
import com.example.museumhunt.R;
import com.example.museumhunt.UI.artifacts_details.ArtifactsDetailFragment;

import java.util.List;

public class HomeArtifactsListFragment extends Fragment implements HomeArtifactsAdapter.ItemClickListener{

    private HomeViewModel homeViewModel;
    HomeArtifactsAdapter adapter;
    ImageView imageView;
    RecyclerView recyclerView;

    final public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home_artifacts_list, container, false);

        recyclerView = root.findViewById(R.id.recyclerViewHome);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new HomeArtifactsAdapter(getContext());
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        homeViewModel.getAllArtifacts().observe(this, new Observer<List<Artifacts>>() {
            @Override
            public void onChanged(@Nullable List<Artifacts> artifactsList) {
                adapter.setItems(artifactsList);
            }
        });
        return root;
    }

    @Override
    final public void onItemClick(String id) {
        ArtifactsDetailFragment nextFrag= new ArtifactsDetailFragment(id);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.host_home, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit();
    }
}
