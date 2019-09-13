package com.example.museumhunt.UI.home_artifacts_list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.example.museumhunt.UI.artifacts_details.ArtifactsDetail;

import java.util.List;

public class HomeArtifactsListFragment extends Fragment implements HomeArtifactsAdapter.ItemClickListener{

    private HomeViewModel homeViewModel;
    HomeArtifactsAdapter adapter;
    ImageView imageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home_artifacts_list, container, false);

        imageView = root.findViewById(R.id.imageViewArt);

        final RecyclerView recyclerView;

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
    public void onItemClick(String id) {
        ArtifactsDetail nextFrag= new ArtifactsDetail(id);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.host_home, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit();

        Toast.makeText(getContext(), "deeemememmed", Toast.LENGTH_SHORT).show();
        Log.d("lodtag","axzcz");
    }
}
