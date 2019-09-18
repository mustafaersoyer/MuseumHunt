package com.example.museumhunt.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.museumhunt.Model.Content;
import com.example.museumhunt.R;

import java.util.ArrayList;
import java.util.List;

public class CampaignAdapter extends RecyclerView.Adapter<CampaignAdapter.ViewHolder> {

    Context mCtx;
    List<Content> contentList;
    private ItemClickListener mClickListener;


    public CampaignAdapter(Context mCtx) {
        this.mCtx = mCtx;
        this.contentList = new ArrayList<>();
    }

    @NonNull
    @Override
    final public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.artifacts_cardview, parent, false);
        return new ViewHolder(view);
    }
    public void setClickListener(CampaignAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Content content = contentList.get(position);
        holder.imageView.setOnClickListener(v -> mClickListener.onItemClick(content.getId()));
        Glide.with(mCtx)
                .load("http://192.168.10.197:49994"+content.getMainImageURL())
                .into(holder.imageView);
    }
    public void setItems(List<Content> contentList) {
        this.contentList = contentList;
        notifyDataSetChanged();
    }

    @Override
    final public int getItemCount() {
        return contentList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewArt);
        }
    }
    public interface ItemClickListener{
        void onItemClick(String id);
    }
}