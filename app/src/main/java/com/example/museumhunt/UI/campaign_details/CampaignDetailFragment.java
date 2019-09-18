package com.example.museumhunt.UI.campaign_details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.museumhunt.Model.Content;
import com.example.museumhunt.R;

public class CampaignDetailFragment extends Fragment {
    private String id;
    CampaignDetailViewModel camapignDetailViewModel;
    ImageView artImage;
    TextView tvName,tvTitle,tvDescription;
    VideoView videoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_campaign_details, container, false);
        Toast.makeText(getContext(), "Campaign Detail", Toast.LENGTH_SHORT).show();
        camapignDetailViewModel =
                ViewModelProviders.of(this).get(CampaignDetailViewModel.class);
        artImage = root.findViewById(R.id.ivCampaignDetail);
        tvName = root.findViewById(R.id.tvCampaignName);
        tvTitle = root.findViewById(R.id.tvCampaignTitle);
        tvDescription = root.findViewById(R.id.tvCampaignDesc);
        videoView = root.findViewById(R.id.videoViewCampaign);

        MediaController mediaController = new MediaController(getContext());
        mediaController.setPadding(145, 0, 145, 110);
        camapignDetailViewModel.getRelation(id).observe(this, new Observer<Content>() {
            @Override
            public void onChanged(Content content) {
                if(content!=null) {
                    Glide.with(getContext())
                            .load("http://192.168.10.197:49994" + content.getMainImageURL())
                            .into(artImage);
                    Toast.makeText(getContext(), "onchgange", Toast.LENGTH_SHORT).show();
                    tvName.setText(content.getName());
                    tvDescription.setText(content.getDescription());
                    tvTitle.setText(content.getTitle());
                    mediaController.setAnchorView(videoView);
                    videoView.setMediaController(mediaController);
                    videoView.setVideoPath("http://192.168.10.197:49994" + content.getVideoURL());
                    //videoView.start();
                }
                else{
                    Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }
    public CampaignDetailFragment(String id){
        this.id=id;
    }
}

