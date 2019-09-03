package com.example.museumhunt.ui.tour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.museumhunt.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class TourFragment extends Fragment {

    private TourViewModel tourViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tourViewModel =
                ViewModelProviders.of(this).get(TourViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tour, container, false);
        final TextView textView = root.findViewById(R.id.text_tour);
        FloatingActionButton floatingActionButton = root.findViewById(R.id.fab);

        tourViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Toast.makeText(getContext(), "Start Tour", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}