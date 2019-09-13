package com.example.museumhunt.UI.about_location;

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
import com.example.museumhunt.Model.Location;
import com.example.museumhunt.R;

public class AboutFragment extends Fragment  {
    AboutViewModel aboutViewModel;
    ImageView imageView;
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about, container, false);
        Toast.makeText(getContext(), "About", Toast.LENGTH_SHORT).show();

        imageView = root.findViewById(R.id.imageViewAbout);
        textView = root.findViewById(R.id.textViewAbout);

        aboutViewModel =
                ViewModelProviders.of(this).get(AboutViewModel.class);
        aboutViewModel.getLocation().observe(this, new Observer<Location>() {
            @Override
            public void onChanged(@Nullable Location location){
                Glide.with(getContext())
                        .load("http://192.168.10.197:49994"+location.getPhotoURL())
                        .into(imageView);
                textView.setText(location.getName());
                Toast.makeText(getContext(), "asdasdasdzxczxczxczxc", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}
