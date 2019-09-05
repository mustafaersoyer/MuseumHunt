package com.example.museumhunt.ui.tour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museumhunt.Adapters.SingleArtifactsAdapter;
import com.example.museumhunt.Model.Artifacts;
import com.example.museumhunt.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TourFragment extends Fragment {

    private TourViewModel tourViewModel;
    SingleArtifactsAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tourViewModel =
                ViewModelProviders.of(this).get(TourViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tour, container, false);
        FloatingActionButton floatingActionButton = root.findViewById(R.id.fab);

        final RecyclerView recyclerView;

        recyclerView = root.findViewById(R.id.recyclerViewTour);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Toast.makeText(getContext(), "Start Tour", Toast.LENGTH_SHORT).show();
            }
        });

        tourViewModel.getArtifacts().observe(this, new Observer<Artifacts>() {
            @Override
            public void onChanged(Artifacts artifacts) {
                adapter = new SingleArtifactsAdapter(getContext(), artifacts);
                recyclerView.setAdapter(adapter);
            }

        });


        return root;
    }
}