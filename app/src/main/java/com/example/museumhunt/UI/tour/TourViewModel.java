package com.example.museumhunt.UI.tour;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.museumhunt.Api.Api;
import com.example.museumhunt.Model.Content;
import com.example.museumhunt.Utils.RetrofitBuilder;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourViewModel extends ViewModel{

    private MutableLiveData<Content> content;
    String uuid,major,minor;

    public LiveData<Content> getContent(String uuid,String major,String minor) {
        if (content == null) {
            content = new MutableLiveData<>();
            this.uuid=uuid;
            this.major=major;
            this.minor=minor;

            loadContent(this.uuid,this.major,this.minor);
        }

        return content;
    }
    private void loadContent(String uuid,String major,String minor) {

        RetrofitBuilder.retrofitBuild();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid",uuid);
        jsonObject.addProperty("major", major);
        jsonObject.addProperty("minor", minor);

        final Api api = RetrofitBuilder.retrofit.create(Api.class);

        Call<Content> call = api.getContentByBeacon("application/json",jsonObject);

        call.enqueue(new Callback<Content>() {
            @Override
            public void onResponse(Call<Content> call, Response<Content> response) {
               content.setValue(response.body());
            }
            @Override
            public void onFailure(Call<Content> call, Throwable t) {
                Log.d("failTag","fasssil");
            }
        });
    }



}