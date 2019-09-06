package com.example.museumhunt.ui.tour;

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

public class TourViewModel extends ViewModel {

    String artifactID;
    private MutableLiveData<BeaconArtId> beaconArtId;
    private MutableLiveData<Artifacts> artifacts;
    Call<Artifacts> call1;
    String uuid,major,minor;


    public TourViewModel() {

    }

    public LiveData<Artifacts> getArtifacts(String uuid,String major,String minor) {
        if (artifacts == null) {
            artifacts = new MutableLiveData<Artifacts>();
            this.uuid=uuid;
            this.major=major;
            this.minor=minor;

            loadArtifacts(this.uuid,this.major,this.minor);
        }

        return artifacts;
    }
    JsonObject jsonObject1;
    private void loadArtifacts(String uuid,String major,String minor) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid",uuid);
        jsonObject.addProperty("major", major);
        jsonObject.addProperty("minor", minor);

        final Api api = retrofit.create(Api.class);
        Call<BeaconArtId> call = api.getBeaconByUUID("application/json",jsonObject);


        call.enqueue(new Callback<BeaconArtId>() {
            @Override
            public void onResponse(Call<BeaconArtId> call, Response<BeaconArtId> response) {
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
            public void onFailure(Call<BeaconArtId> call, Throwable t) {
                Log.d("failTag","fasssil");
            }
        });
    }



}