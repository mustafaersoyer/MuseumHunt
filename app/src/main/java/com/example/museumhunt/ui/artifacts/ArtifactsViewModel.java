package com.example.museumhunt.ui.artifacts;

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

public class ArtifactsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<Artifacts>> artifactsList;

    public ArtifactsViewModel() {
        if (artifactsList == null) {
            artifactsList = new MutableLiveData<List<Artifacts>>();
            //we will load it asynchronously from server in this method
            loadArtifacts();
        }

        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<List<Artifacts>> getAllArtifacts() {
        //if the list is null
        if (artifactsList == null) {
            artifactsList = new MutableLiveData<List<Artifacts>>();
            //we will load it asynchronously from server in this method
            loadArtifacts();
        }

        //finally we will return the list
        return artifactsList;
    }

    public LiveData<String> getText() {
        return mText;
    }

    private void loadArtifacts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", "8962c635-d42d-43c5-b1e3-fc77fcf71b19");

        Api api = retrofit.create(Api.class);
        Call<List<Artifacts>> call = api.getAllArtifacts(jsonObject);


        call.enqueue(new Callback<List<Artifacts>>() {
            @Override
            public void onResponse(Call<List<Artifacts>> call, Response<List<Artifacts>> response) {

                //finally we are setting the list to our MutableLiveData
                artifactsList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Artifacts>> call, Throwable t) {

            }
        });
    }


}