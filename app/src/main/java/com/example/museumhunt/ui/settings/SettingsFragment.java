package com.example.museumhunt.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museumhunt.Adapters.SettingsRecyclerAdapter;
import com.example.museumhunt.MainActivity;
import com.example.museumhunt.R;
import com.example.museumhunt.ui.aboutLocation.AboutFragment;

import java.util.ArrayList;

public class SettingsFragment extends Fragment implements SettingsRecyclerAdapter.ItemClickListener {

    private SettingsViewModel settingsViewModel;
    SettingsRecyclerAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);


        final ArrayList<String> settings = new ArrayList<>();
        settings.add("Change your museum location");
        settings.add("Campaigns");
        settings.add("About");


        // set up the RecyclerView
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        adapter = new SettingsRecyclerAdapter(getContext(), settings);

        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        final Fragment fragment1 = new AboutFragment();


        MainActivity mainActivity = new MainActivity();
        // Create new fragment and transaction
       /* Fragment newFragment = new AboutFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
        transaction.replace(R.id.nav_host_fragment, newFragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();*/

        Fragment fragmentC = new AboutFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.child_fragment, fragmentC ).commit();

        return root;
    }

    @Override
    public void onItemClick(View v, int position) {
        Toast.makeText(getContext(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();

    }
}
