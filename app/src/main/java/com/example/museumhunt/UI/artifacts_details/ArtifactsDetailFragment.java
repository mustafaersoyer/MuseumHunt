package com.example.museumhunt.UI.artifacts_details;

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

public class ArtifactsDetailFragment extends Fragment {
    private String id,url="";
    ArtDetailViewModel artDetailViewModel;
    ImageView artImage;
    TextView tvName,tvTitle,tvDescription;
    VideoView videoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_artifacts_details, container, false);
        Toast.makeText(getContext(), "Artifacts Detail", Toast.LENGTH_SHORT).show();
        artDetailViewModel =
                ViewModelProviders.of(this).get(ArtDetailViewModel.class);
        artImage = root.findViewById(R.id.imageViewArtDetail);
        tvName = root.findViewById(R.id.textViewName);
        tvTitle = root.findViewById(R.id.textViewTitle);
        tvDescription = root.findViewById(R.id.textViewDescription);
        videoView = root.findViewById(R.id.videoView);

        MediaController mediaController = new MediaController(getContext());
        mediaController.setPadding(145, 0, 145, 110);
        artDetailViewModel.getRelation(id).observe(this, new Observer<Content>() {
            @Override
            public void onChanged(Content content) {
                if(content!=null) {
                    Glide.with(getContext())
                            .load(getContext().getResources().getString(R.string.baseURL) + content.getMainImageURL())
                            .into(artImage);
                    tvName.setText(content.getName());
                    tvDescription.setText(content.getDescription());
                    tvTitle.setText(content.getTitle());
                    mediaController.setAnchorView(videoView);
                    videoView.setMediaController(mediaController);
                    videoView.setVideoPath(getContext().getResources().getString(R.string.baseURL) +""+ content.getVideoURL());
                }
            }
        });

        return root;
    }
    public ArtifactsDetailFragment(String id){
        this.id=id;
    }
}
