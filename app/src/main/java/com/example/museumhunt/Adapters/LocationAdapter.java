package com.example.museumhunt.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museumhunt.Model.Location;
import com.example.museumhunt.R;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    Context mCtx;
    List<Location> locationList;

    public LocationAdapter(Context mCtx, List<Location> locationList) {
        this.mCtx = mCtx;
        this.locationList = locationList;
    }

    @NonNull
    @Override
    final public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.fragment_change_location, parent, false);
        return new LocationViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        Location location = locationList.get(position);
    }

    @Override
    final public int getItemCount() {
        return locationList.size();
    }

    class LocationViewHolder extends RecyclerView.ViewHolder {
        Spinner spinner;
        public LocationViewHolder(View itemView) {
            super(itemView);
            spinner = itemView.findViewById(R.id.spinnerLocations);
        }
    }
}