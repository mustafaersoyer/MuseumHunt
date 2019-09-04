package com.example.museumhunt.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        View view = LayoutInflater.from(mCtx).inflate(R.layout.artifacts_cardview, parent, false);
        return new ArtifactsViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ArtifactsViewHolder holder, int position) {
        Artifacts artifacts = artifactsList.get(position);
        Toast.makeText(mCtx, ""+artifacts.getMainImageURL(), Toast.LENGTH_SHORT).show();
        Glide.with(mCtx)
                .load(artifacts.getMainImageURL())
                .into(holder.imageView);
    }

    @Override
    final public int getItemCount() {
        return artifactsList.size();
    }

    class ArtifactsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ArtifactsViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewArt);
        }
    }
}