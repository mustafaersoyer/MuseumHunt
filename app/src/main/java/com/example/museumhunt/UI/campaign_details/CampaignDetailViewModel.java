package com.example.museumhunt.UI.campaign_details;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museumhunt.Api.Api;
import com.example.museumhunt.Model.Content;
import com.example.museumhunt.Model.Relation;
import com.example.museumhunt.Utils.RetrofitBuilder;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CampaignDetailViewModel extends ViewModel {
    private MutableLiveData<Content> content;
    private String id;

    final public LiveData<Content> getRelation(String id) {
        content = new MutableLiveData<>();
        this.id=id;
        loadRelation(this.id);
        return content;
    }
    final private void loadRelation(String id) {
        RetrofitBuilder.retrofitBuild();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",id);

        final Api api = RetrofitBuilder.retrofit.create(Api.class);
        Call<Relation> call = api.getRelationContent("application/json",jsonObject);

        call.enqueue(new Callback<Relation>() {
            @Override
            final public void onResponse(Call<Relation> call, Response<Relation> response) {
                JsonObject jsonObject = new JsonObject();

                if(response.body()!=null)
                    jsonObject.addProperty("id",response.body().getContentId());

                Call<Content> callContent = api.getContent("application/json",jsonObject);
                callContent.enqueue(new Callback<Content>() {
                    @Override
                    final public void onResponse(Call<Content> call, Response<Content> response) {
                        content.setValue(response.body());
                    }

                    @Override
                    final public void onFailure(Call<Content> call, Throwable t) {
                        Log.d("failTag","fail: "+t.toString());
                    }
                });
            }
            @Override
            final public void onFailure(Call<Relation> call, Throwable t) {
                Log.d("failTag","fail: "+t.toString());
            }
        });
    }
}