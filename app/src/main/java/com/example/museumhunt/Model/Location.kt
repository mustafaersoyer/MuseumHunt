package com.example.museumhunt.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Location {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("photoURL")
    @Expose
    var photoURL: String? = null

}
