package com.example.museumhunt.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relation {

    @SerializedName("artifacName")
    @Expose
    private String artifacName;
    @SerializedName("contentName")
    @Expose
    private String contentName;
    @SerializedName("contentId")
    @Expose
    private String contentId;
    @SerializedName("beaconName")
    @Expose
    private String beaconName;
    @SerializedName("proximity")
    @Expose
    private String proximity;
    @SerializedName("createdTime")
    @Expose
    private String createdTime;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("id")
    @Expose
    private String id;

    public String getArtifacName() {
        return artifacName;
    }

    public void setArtifacName(String artifacName) {
        this.artifacName = artifacName;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getBeaconName() {
        return beaconName;
    }

    public void setBeaconName(String beaconName) {
        this.beaconName = beaconName;
    }

    public String getProximity() {
        return proximity;
    }

    public void setProximity(String proximity) {
        this.proximity = proximity;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}