package com.example.museumhunt.UI.artifacts_details;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museumhunt.Api.Api;
import com.example.museumhunt.Model.Artifacts;
import com.example.museumhunt.Model.Content;
import com.example.museumhunt.Model.Relation;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtDetailViewModel extends ViewModel {

    String artifactID;
    private MutableLiveData<Content> content;
    private MutableLiveData<Relation> relation;
    Call<Artifacts> call1;
    private String id;


    public ArtDetailViewModel() {

    }

    public LiveData<Content> getRelation(String id) {
        if (relation == null) {
            relation = new MutableLiveData<Relation>();
            content = new MutableLiveData<Content>();
           this.id=id;
            //this.id="497a2d0e-7a2f-4a00-ae56-08d72a29f302";

            loadRelation(this.id);
        }

        return content;
    }
    JsonObject jsonObject1;
    private void loadRelation(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.Companion.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",id);

        final Api api = retrofit.create(Api.class);
        Call<Relation> call = api.getRelation("application/json",jsonObject);


        call.enqueue(new Callback<Relation>() {
            @Override
            public void onResponse(Call<Relation> call, Response<Relation> response) {
                relation.setValue(response.body());
                JsonObject jsonObject = new JsonObject();
                if(relation.getValue()!=null)
                    jsonObject.addProperty("id",relation.getValue().getContentId());
               // jsonObject.addProperty("id","5d76f184-1817-4cec-8d67-d88d3db90b21");
                Call<Content> callContent = api.getContent("application/json",jsonObject);
                callContent.enqueue(new Callback<Content>() {
                    @Override
                    public void onResponse(Call<Content> call, Response<Content> response) {
                        content.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<Content> call, Throwable t) {
                        Log.d("failtagatag","fail: "+t.toString());
                    }
                });

            }
            @Override
            public void onFailure(Call<Relation> call, Throwable t) {
                Log.d("failTagtagtt","fasssil: "+t.toString());
            }
        });
    }

}