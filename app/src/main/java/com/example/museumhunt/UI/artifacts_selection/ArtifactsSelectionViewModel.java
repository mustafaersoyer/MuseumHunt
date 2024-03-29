package com.example.museumhunt.UI.artifacts_selection;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museumhunt.Api.Api;
import com.example.museumhunt.Model.Artifacts;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtifactsSelectionViewModel extends ViewModel {

    private MutableLiveData<List<Artifacts>> artifactsList;

    public ArtifactsSelectionViewModel() {
        if (artifactsList == null) {
            artifactsList = new MutableLiveData<>();
            loadArtifacts();
        }
    }

    public LiveData<List<Artifacts>> getAllArtifacts() {
        if (artifactsList == null) {
            artifactsList = new MutableLiveData<>();
            loadArtifacts();
        }

        return artifactsList;
    }

    private void loadArtifacts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.Companion.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", "8962c635-d42d-43c5-b1e3-fc77fcf71b19");

        Api api = retrofit.create(Api.class);
        Call<List<Artifacts>> call = api.getAllArtifacts("application/json",jsonObject);


        call.enqueue(new Callback<List<Artifacts>>() {
            @Override
            public void onResponse(Call<List<Artifacts>> call, Response<List<Artifacts>> response) {
                artifactsList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Artifacts>> call, Throwable t) {
                Log.d("failTag","fail");
            }
        });
    }
}