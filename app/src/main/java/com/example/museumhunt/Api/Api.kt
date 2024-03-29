package com.example.museumhunt.Api

import com.example.museumhunt.Model.Artifacts
import com.example.museumhunt.Model.Content
import com.example.museumhunt.Model.Location
import com.example.museumhunt.Model.Relation
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface Api {

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("Artifact/GetAllArtifacts")
    fun getAllArtifacts(@Header("Content-Type") type: String, @Body body: JsonObject): Call<List<Artifacts>>

    @POST("Location/GetLocation")
    fun getLocation(@Header("Content-Type") type: String, @Body body: JsonObject): Call<Location>

    @POST("Location/GetAllLocation")
    fun getAllLocation(@Header("Content-Type") type: String, @Body body: JsonObject): Call<List<Location>>

    @POST("Relation/GetRelationsWithArtifact")
    fun getRelation(@Header("Content-Type") type: String, @Body body: JsonObject): Call<Relation>

    @POST("Relation/GetRelationsWithContent")
    fun getRelationContent(@Header("Content-Type") type: String, @Body body: JsonObject): Call<Relation>

    @POST("Content/GetContent")
    fun getContent(@Header("Content-Type") type: String, @Body body: JsonObject): Call<Content>

    @POST("Content/GetCampaignContents")
    fun getCampaignContents(@Header("Content-Type") type: String, @Body body: JsonObject): Call<List<Content>>

    @POST("Content/GetContentWithBeacon")
    fun getContentByBeacon(@Header("Content-Type") type: String, @Body body: JsonObject): Call<Content>


    companion object {
        val BASE_URL = "http://192.168.10.197:44327/api/"
    }
}
