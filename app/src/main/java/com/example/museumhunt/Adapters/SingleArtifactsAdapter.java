package com.example.museumhunt.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.museumhunt.Model.Artifacts;
import com.example.museumhunt.R;

public class SingleArtifactsAdapter extends RecyclerView.Adapter<SingleArtifactsAdapter.ArtifactsViewHolder> {

    Context mCtx;
    Artifacts artifactsList;

    public SingleArtifactsAdapter(Context mCtx, Artifacts artifactsList) {
        this.mCtx = mCtx;
        this.artifactsList = artifactsList;
    }

    @NonNull
    @Override
    final public SingleArtifactsAdapter.ArtifactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.artifacts_cardview, parent, false);
        return new SingleArtifactsAdapter.ArtifactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtifactsViewHolder holder, int position) {
        Artifacts artifacts = artifactsList;
        Log.d("artifactsSingle","artifacts: "+artifacts.toString());
        Toast.makeText(mCtx, "192.168.10.78:49994"+artifacts.getMainImageURL(), Toast.LENGTH_SHORT).show();
        Glide.with(mCtx)
                .load("http://192.168.10.78:49994"+artifacts.getMainImageURL())
                .into(holder.imageView);
    }




    @Override
    final public int getItemCount() {
        return 1;
        //return artifactsList.size();
    }

    class ArtifactsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ArtifactsViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewArt);
        }
    }
}
