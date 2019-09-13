package com.example.museumhunt.UI.about_location;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museumhunt.Api.Api;
import com.example.museumhunt.Model.Location;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AboutViewModel extends ViewModel {

    private MutableLiveData<Location> location;

    public AboutViewModel() {
        if (location == null) {
            location = new MutableLiveData<Location>();
            loadLocation();
        }
    }

    public LiveData<Location> getLocation() {
        if (location == null) {
            location = new MutableLiveData<Location>();
            loadLocation();
        }

        return location;
    }

    private void loadLocation() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.Companion.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", "8962c635-d42d-43c5-b1e3-fc77fcf71b19");

        Api api = retrofit.create(Api.class);
        Call<Location> call = api.getLocation("application/json",jsonObject);


        call.enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {

                //finally we are setting the list to our MutableLiveData
                location.setValue(response.body());
                Log.d("responseTag","response: "+location.toString());

            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
                Log.d("failTag","fail");
            }
        });
    }


}