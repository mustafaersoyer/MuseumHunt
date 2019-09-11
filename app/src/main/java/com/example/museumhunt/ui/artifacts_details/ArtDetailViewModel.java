package com.example.museumhunt.ui.artifacts_details;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museumhunt.Api.Api;
import com.example.museumhunt.Model.Artifacts;
import com.example.museumhunt.Model.BeaconArtId;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtDetailViewModel extends ViewModel {

    String artifactID;
    private MutableLiveData<BeaconArtId> beaconArtId;
    private MutableLiveData<Artifacts> artifacts;
    Call<Artifacts> call1;
    private String id;


    public ArtDetailViewModel() {

    }

    public LiveData<Artifacts> getArtifacts(String id) {
        if (artifacts == null) {
            artifacts = new MutableLiveData<Artifacts>();
            this.id=id;

            loadArtifacts(this.id);
        }

        return artifacts;
    }
    JsonObject jsonObject1;
    private void loadArtifacts(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.Companion.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",id);

        final Api api = retrofit.create(Api.class);
        Call<Artifacts> call = api.getArtifacts("application/json",jsonObject);


        call.enqueue(new Callback<Artifacts>() {
            @Override
            public void onResponse(Call<Artifacts> call, Response<Artifacts> response) {
                Log.d("rspTag","responsebody "+response.body());

                artifactID = response.body().toString();

                jsonObject1  = new JsonObject();
                jsonObject1.addProperty("id", response.body().getId());
                Log.d("jsonnn",""+jsonObject1.toString());
                call1 = api.getArtifacts("application/json",jsonObject1);

                call1.enqueue(new Callback<Artifacts>() {
                    @Override
                    public void onResponse(Call<Artifacts> call, Response<Artifacts> response) {
                        artifacts.setValue(response.body());
                        Log.d("responseArtifactsss","artifact: "+artifacts.toString());
                        Log.d("responseBooooody","responsebody: "+response.body().toString()); //boş

                    }

                    @Override
                    public void onFailure(Call<Artifacts> call, Throwable t) {
                        Log.d("tourviewfail","fail: "+ t.toString());
                    }
                });
            }
            @Override
            public void onFailure(Call<Artifacts> call, Throwable t) {
                Log.d("failTag","fasssil");
            }
        });
    }

}