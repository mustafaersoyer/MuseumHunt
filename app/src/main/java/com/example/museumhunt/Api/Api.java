package com.example.museumhunt.Api;

import com.example.museumhunt.Model.Artifacts;
import com.example.museumhunt.Model.BeaconArtId;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {
    String BASE_URL = "http://192.168.10.78:44327/api/";

    @Headers({"Accept: application/json","Content-Type: application/json"})
    @POST("Artifact/GetAllArtifacts")
    Call<List<Artifacts>> getAllArtifacts(@Header("Content-Type") String type, @Body JsonObject body);

    @POST("Beacon/GetBeaconByUUID")
    Call<BeaconArtId> getBeaconByUUID(@Header("Content-Type") String type, @Body JsonObject body);

    @POST("Artifact/GetArtifact")
    Call<Artifacts> getArtifacts(@Header("Content-Type") String type, @Body JsonObject body);
}
