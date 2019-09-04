package com.example.museumhunt.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artifacts {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mainImageURL")
    @Expose
    private String mainImageURL;
    @SerializedName("roomName")
    @Expose
    private String roomName;
    @SerializedName("floorName")
    @Expose
    private String floorName;
    @SerializedName("buildingName")
    @Expose
    private String buildingName;
    @SerializedName("createdTime")
    @Expose
    private String createdTime;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("id")
    @Expose
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainImageURL() {
        return mainImageURL;
    }

    public void setMainImageURL(String mainImageURL) {
        this.mainImageURL = mainImageURL;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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