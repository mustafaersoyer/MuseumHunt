package com.example.museumhunt.UI.campaigns_host;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.museumhunt.R;
import com.example.museumhunt.UI.campaigns_list.CampaignsListFragment;

public class CampaignsHostFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_host_campaigns, container, false);

        CampaignsListFragment nextFrag= new CampaignsListFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.host_campaigns, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit();

        return root;
    }
}