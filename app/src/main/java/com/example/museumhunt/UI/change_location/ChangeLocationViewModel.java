package com.example.museumhunt.UI.change_location;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museumhunt.Api.Api;
import com.example.museumhunt.Model.Location;
import com.example.museumhunt.Utils.RetrofitBuilder;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeLocationViewModel extends ViewModel {
    private MutableLiveData<List<Location>> locationList;


    public ChangeLocationViewModel() {
        if (locationList == null) {
            locationList = new MutableLiveData<List<Location>>();
            loadLocation();
        }
    }

    public LiveData<List<Location>> getAllLocation() {
        if (locationList == null) {
            locationList = new MutableLiveData<List<Location>>();
            loadLocation();
        }

        return locationList;
    }

    private void loadLocation() {
        RetrofitBuilder.retrofitBuild();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("", "");

        final Api api = RetrofitBuilder.retrofit.create(Api.class);
        Call<List<Location>> call = api.getAllLocation("application/json", jsonObject);


        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {

                //finally we are setting the list to our MutableLiveData
                locationList.setValue(response.body());
                Log.d("responseTag", "responsexxxx: " + locationList.toString());

            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Log.d("failTaggggggx", "fail "+t);
            }
        });
    }
}


