package com.example.museumhunt.ui.home_artifacts_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

import java.util.List;

public class HomeArtifactsListFragment extends Fragment implements HomeArtifactsAdapter.ItemClickListener{

    private HomeViewModel homeViewModel;
    HomeArtifactsAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home_artifacts_list, container, false);

        final RecyclerView recyclerView;

        recyclerView = root.findViewById(R.id.recyclerViewHome);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        homeViewModel.getAllArtifacts().observe(this, new Observer<List<Artifacts>>() {
            @Override
            public void onChanged(@Nullable List<Artifacts> artifactsList) {
                adapter = new HomeArtifactsAdapter(getContext(), artifactsList);
                recyclerView.setAdapter(adapter);
            }
        });
        adapter.setClickListener(new HomeArtifactsAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "denemedendemdemdmedmedm", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
    @Override
    public void onItemClick(View view, int position) {
    }
}
