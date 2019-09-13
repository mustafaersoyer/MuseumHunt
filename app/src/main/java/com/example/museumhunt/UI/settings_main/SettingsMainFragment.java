package com.example.museumhunt.UI.settings_main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museumhunt.Adapters.SettingsRecyclerAdapter;
import com.example.museumhunt.R;
import com.example.museumhunt.UI.about_location.AboutFragment;
import com.example.museumhunt.UI.campaigns.CampaignsFragment;
import com.example.museumhunt.UI.change_location.ChangeLocationFragment;

import java.util.ArrayList;

public class SettingsMainFragment extends Fragment implements SettingsRecyclerAdapter.ItemClickListener {
    RecyclerView recyclerView;
    SettingsRecyclerAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.settings_main, container, false);


        final ArrayList<String> settings = new ArrayList<>();
        settings.add("Change your museum location");
        settings.add("Campaigns");
        settings.add("About");

        recyclerView = root.findViewById(R.id.recycler_view_settings);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new SettingsRecyclerAdapter(getContext(), settings);

        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


        return root;
    }

    @Override
    public void onItemClick(View v, int position) {
        Toast.makeText(getContext(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        SettingsMainFragment settingsMainFragment = new SettingsMainFragment();
        if(position==2) {
            AboutFragment nextFrag= new AboutFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.host_settings_fragment, nextFrag, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        }else if(position==1){
            CampaignsFragment nextFrag= new CampaignsFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.host_settings_fragment, nextFrag, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        }else if(position==0){
            ChangeLocationFragment nextFrag = new ChangeLocationFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.host_settings_fragment, nextFrag, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        }
    }
}
