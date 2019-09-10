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

public class HomeArtifactsAdapter extends RecyclerView.Adapter<HomeArtifactsAdapter.ViewHolder> {

    Context mCtx;
    private ItemClickListener mClickListener;

    List<Artifacts> artifactsList;

    public HomeArtifactsAdapter(Context mCtx, List<Artifacts> artifactsList) {
        this.mCtx = mCtx;
        this.artifactsList = artifactsList;
    }

    @NonNull
    @Override
    public HomeArtifactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.artifacts_cardview, parent, false);
        return new HomeArtifactsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Artifacts artifacts = artifactsList.get(position);
        Glide.with(mCtx)
                .load("http://192.168.10.197:49994"+artifacts.getMainImageURL())
                .into(holder.imageView);
    }

    @Override
    final public int getItemCount() {
        return artifactsList.size();
    }

    final public void setClickListener(HomeArtifactsAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewArt);
            itemView.setOnClickListener(this);
        }

        @Override
        final public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
