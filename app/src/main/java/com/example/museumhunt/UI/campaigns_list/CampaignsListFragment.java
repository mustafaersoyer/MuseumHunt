package com.example.museumhunt.UI.campaigns_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museumhunt.Adapters.CampaignAdapter;
import com.example.museumhunt.Model.Content;
import com.example.museumhunt.R;
import com.example.museumhunt.UI.campaign_details.CampaignDetailFragment;

import java.util.List;

public class CampaignsListFragment extends Fragment implements CampaignAdapter.ItemClickListener{
    private RecyclerView recyclerView;
    private CampaignAdapter campaignAdapter;
    private CampaignsListViewModel campaignsListViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_campaigns, container, false);

        campaignsListViewModel =
                ViewModelProviders.of(this).get(CampaignsListViewModel.class);

        recyclerView = root.findViewById(R.id.recyclerViewCampaign);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        campaignAdapter = new CampaignAdapter(getContext());

        recyclerView.setAdapter(campaignAdapter);
        campaignsListViewModel.getCampaignsContent().observe(this, new Observer<List<Content>>() {
            @Override
            public void onChanged(@Nullable List<Content> contentList) {
                campaignAdapter.setItems(contentList);
            }
        });

        campaignAdapter.setClickListener(this);
        return root;
    }

    @Override
    public void onItemClick(String id) {
        CampaignDetailFragment nextFrag= new CampaignDetailFragment(id);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.host_campaigns, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit();
    }
}
