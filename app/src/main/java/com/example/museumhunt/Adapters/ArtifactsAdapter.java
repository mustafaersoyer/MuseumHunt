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
    public ArtifactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.artifacts_cardview, parent, false);
        return new ArtifactsViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ArtifactsViewHolder holder, int position) {
        Artifacts artifacts = artifactsList.get(position);

        /*Glide.with(mCtx)
                .load(artifacts.getMainImageURL())
                .into(holder.imageViewArt);*/
        Toast.makeText(mCtx, ""+artifacts.getMainImageURL(), Toast.LENGTH_SHORT).show();
        holder.textView.setText(artifacts.getName());
    }

    @Override
    public int getItemCount() {
        return artifactsList.size();
    }

    class ArtifactsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ArtifactsViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}