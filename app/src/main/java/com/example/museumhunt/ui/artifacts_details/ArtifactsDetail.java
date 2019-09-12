package com.example.museumhunt.ui.artifacts_details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.museumhunt.Model.Content;
import com.example.museumhunt.R;

public class ArtifactsDetail extends Fragment {
    private String id;
    ArtDetailViewModel artDetailViewModel;
    ImageView artImage;
    TextView artText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_artifacts_details, container, false);
        Toast.makeText(getContext(), "Artifacts Detail", Toast.LENGTH_SHORT).show();
        artDetailViewModel =
                ViewModelProviders.of(this).get(ArtDetailViewModel.class);
        artImage = root.findViewById(R.id.imageViewArtDetail);
        artText = root.findViewById(R.id.textViewDetail);
        
        artDetailViewModel.getRelation(id).observe(this, new Observer<Content>() {
            @Override
            public void onChanged(Content content) {
                Glide.with(getContext())
                        .load("http://192.168.10.197:49994"+content.getMainImageURL())
                        .into(artImage);
                Toast.makeText(getContext(), "onchgange", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
    public ArtifactsDetail(String id){
        this.id=id;
    }
}
