package com.example.museumhunt.UI.home_artifacts_list;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museumhunt.Api.Api;
import com.example.museumhunt.Model.Artifacts;
import com.example.museumhunt.Utils.RetrofitBuilder;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<Artifacts>> artifactsList;

    final public LiveData<List<Artifacts>> getAllArtifacts() {
        if (artifactsList == null) {
            artifactsList = new MutableLiveData<>();
            loadArtifacts();
        }
        return artifactsList;
    }

    final private void loadArtifacts() {
        RetrofitBuilder.retrofitBuild();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", "8962c635-d42d-43c5-b1e3-fc77fcf71b19");

        final Api api = RetrofitBuilder.retrofit.create(Api.class);
        Call<List<Artifacts>> call = api.getAllArtifacts("application/json", jsonObject);
        call.enqueue(new Callback<List<Artifacts>>() {
            @Override
            final public void onResponse(Call<List<Artifacts>> call, Response<List<Artifacts>> response) {
                artifactsList.setValue(response.body());
            }

            @Override
            final public void onFailure(Call<List<Artifacts>> call, Throwable t) {
                Log.d("failTag", "fail "+t);
            }
        });
    }
}