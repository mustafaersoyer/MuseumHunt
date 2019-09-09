package com.example.museumhunt.ui.artifacts_details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.museumhunt.R;

public class ArtifactsDetails extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_artifacts_details, container, false);
        Toast.makeText(getContext(), "Artifacts Detail", Toast.LENGTH_SHORT).show();
        return root;
    }
}
