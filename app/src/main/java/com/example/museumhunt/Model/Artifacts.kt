package com.example.museumhunt.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Artifacts {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("mainImageURL")
    @Expose
    var mainImageURL: String? = null
    @SerializedName("roomName")
    @Expose
    var roomName: String? = null
    @SerializedName("floorName")
    @Expose
    var floorName: String? = null
    @SerializedName("buildingName")
    @Expose
    var buildingName: String? = null
    @SerializedName("createdTime")
    @Expose
    var createdTime: String? = null
    @SerializedName("isActive")
    @Expose
    var isActive: Boolean? = null
    @SerializedName("id")
    @Expose
    var id: String? = null

}