package com.example.museumhunt.UI.about_location;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museumhunt.Api.Api;
import com.example.museumhunt.Model.Location;
import com.example.museumhunt.Utils.RetrofitBuilder;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutViewModel extends ViewModel {

    private MutableLiveData<Location> location;

    public AboutViewModel() {
        if (location == null) {
            location = new MutableLiveData<>();
            loadLocation();
        }
    }

    public LiveData<Location> getLocation() {
        if (location == null) {
            location = new MutableLiveData<>();
            loadLocation();
        }

        return location;
    }

    private void loadLocation() {
        RetrofitBuilder.retrofitBuild();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", "8962c635-d42d-43c5-b1e3-fc77fcf71b19");

        final Api api = RetrofitBuilder.retrofit.create(Api.class);
        Call<Location> call =api.getLocation("application/json",jsonObject);

        call.enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                location.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
                Log.d("failTag","fail");
            }
        });
    }


}