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



    public TourViewModel() {
        if (beaconArtId == null) {
            beaconArtId = new MutableLiveData<BeaconArtId>();
            //we will load it asynchronously from server in this method
            loadArtifacts();
        }
    }

    public LiveData<Artifacts> getArtifacts() {
        //if the list is null
        if (artifacts == null) {
            artifacts = new MutableLiveData<Artifacts>();
            //we will load it asynchronously from server in this method
            loadArtifacts();
        }

        //finally we will return the list
        return artifacts;
    }
    JsonObject jsonObject1;
    private void loadArtifacts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", "1231");
        jsonObject.addProperty("major", "343");
        jsonObject.addProperty("minor", "22");

        final Api api = retrofit.create(Api.class);
        Call<BeaconArtId> call = api.getBeaconByUUID("application/json",jsonObject);


        call.enqueue(new Callback<BeaconArtId>() {
            @Override
            public void onResponse(Call<BeaconArtId> call, Response<BeaconArtId> response) {

                //finally we are setting the list to our MutableLiveData
                Log.d("rspTag","responsebody "+response.body().toString());

                artifactID = response.body().toString();
                beaconArtId.setValue(response.body());
                Log.d("responseTag","response: "+beaconArtId.getValue());
                jsonObject1  = new JsonObject();
               // jsonObject1.addProperty("id", beaconArtId.getValue().toString());
                BeaconArtId beaconArtId = new BeaconArtId();
                //jsonObject1.addProperty("id", "497a2d0e-7a2f-4a00-ae56-08d72a29f302");
               jsonObject1.addProperty("id", beaconArtId.getId());
               Log.d("artid",""+beaconArtId.getId());



                Log.d("jsonnn",""+jsonObject1.toString());
                call1 = api.getArtifacts("application/json",jsonObject1);

                call1.enqueue(new Callback<Artifacts>() {
                    @Override
                    public void onResponse(Call<Artifacts> call, Response<Artifacts> response) {

                        //finally we are setting the list to our MutableLiveData
                        //artifactID = response.body().toString();
                        //beaconArtId.setValue(response.body());
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





        //Log.d("jsonnn",""+jsonObject1.toString());


    }

}