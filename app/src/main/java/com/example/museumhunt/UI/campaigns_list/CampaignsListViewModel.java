package com.example.museumhunt.UI.campaigns_list;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museumhunt.Api.Api;
import com.example.museumhunt.Model.Content;
import com.example.museumhunt.Utils.RetrofitBuilder;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CampaignsListViewModel extends ViewModel {
    private MutableLiveData<List<Content>> contentList;

    final public LiveData<List<Content>> getCampaignsContent() {
        if (contentList == null) {
            contentList = new MutableLiveData<List<Content>>();
            loadContent();
        }
        return contentList;
    }

    final private void loadContent() {
        RetrofitBuilder.retrofitBuild();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", "8962c635-d42d-43c5-b1e3-fc77fcf71b19");

        final Api api = RetrofitBuilder.retrofit.create(Api.class);
        Call<List<Content>> call = api.getCampaignContents("application/json", jsonObject);
        call.enqueue(new Callback<List<Content>>() {
            @Override
            final public void onResponse(Call<List<Content>> call, Response<List<Content>> response) {
                contentList.setValue(response.body());
            }

            @Override
            final public void onFailure(Call<List<Content>> call, Throwable t) {
                Log.d("failTag", "fail "+t);
            }
        });
    }
}