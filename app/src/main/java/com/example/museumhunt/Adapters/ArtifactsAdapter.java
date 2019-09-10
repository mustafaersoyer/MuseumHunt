package com.example.museumhunt.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.museumhunt.Model.Artifacts;
import com.example.museumhunt.R;

import java.util.List;

public class ArtifactsAdapter extends RecyclerView.Adapter<ArtifactsAdapter.ArtifactsViewHolder> {

    Context mCtx;
    List<Artifacts> artifactsList;

    public ArtifactsAdapter(Context mCtx, List<Artifacts> artifactsList) {
        this.mCtx = mCtx;
        this.artifactsList = artifactsList;
    }

    @NonNull
    @Override
    final public ArtifactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.artifacts_card_choose, parent, false);
        return new ArtifactsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ArtifactsViewHolder holder, int position) {
        Artifacts artifacts = artifactsList.get(position);
        Glide.with(mCtx)
                .load("http://192.168.10.197:49994"+artifacts.getMainImageURL())
                .into(holder.imageView);
    }

    @Override
    final public int getItemCount() {
        return artifactsList.size();
    }

    class ArtifactsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        public ArtifactsViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewArt_choose);
        }
    }
}