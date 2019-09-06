package com.example.museumhunt.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.museumhunt.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ImageView imageView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        imageView = root.findViewById(R.id.imageView);

        String imgUrl = "http://192.168.10.78:49994/Images/Koala.jpg";
        String imageUrl = "https://raw.githubusercontent.com/bumptech/glide/master/static/glide_logo.png";

        Glide.with(this).load(imgUrl).into(imageView);
        return root;
    }
}