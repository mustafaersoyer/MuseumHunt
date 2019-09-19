package com.example.museumhunt.UI.artifacts_selection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museumhunt.Adapters.ArtifactsSelectionAdapter;
import com.example.museumhunt.Model.Artifacts;
import com.example.museumhunt.R;

import java.util.List;

public class ArtifactsSelectionFragment extends Fragment {

    private ArtifactsSelectionViewModel artifactsViewModel;
    private ArtifactsSelectionAdapter adapter;
    private RecyclerView recyclerView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        artifactsViewModel =
                ViewModelProviders.of(this).get(ArtifactsSelectionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_artifacts, container, false);

        recyclerView = root.findViewById(R.id.recyclerViewArt);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        artifactsViewModel.getAllArtifacts().observe(this, new Observer<List<Artifacts>>() {
            @Override
            public void onChanged(@Nullable List<Artifacts> artifactsList) {
                adapter = new ArtifactsSelectionAdapter(getContext(), artifactsList);
                recyclerView.setAdapter(adapter);
            }
        });

        return root;
    }
}