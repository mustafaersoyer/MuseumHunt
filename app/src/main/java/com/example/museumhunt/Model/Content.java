package com.example.museumhunt.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("name")
    @Expose
    private String name;    
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("mainImageURL")
    @Expose
    private String mainImageURL;
    @SerializedName("videoURL")
    @Expose
    private Object videoURL;
    @SerializedName("slideImageURL")
    @Expose
    private Object slideImageURL;
    @SerializedName("audioURL")
    @Expose
    private Object audioURL;
    @SerializedName("text")
    @Expose
    private Object text;
    @SerializedName("isHomePage")
    @Expose
    private Boolean isHomePage;
    @SerializedName("isCampaign")
    @Expose
    private Boolean isCampaign;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainImageURL() {
        return mainImageURL;
    }

    public void setMainImageURL(String mainImageURL) {
        this.mainImageURL = mainImageURL;
    }

    public Object getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(Object videoURL) {
        this.videoURL = videoURL;
    }

    public Object getSlideImageURL() {
        return slideImageURL;
    }

    public void setSlideImageURL(Object slideImageURL) {
        this.slideImageURL = slideImageURL;
    }

    public Object getAudioURL() {
        return audioURL;
    }

    public void setAudioURL(Object audioURL) {
        this.audioURL = audioURL;
    }

    public Object getText() {
        return text;
    }

    public void setText(Object text) {
        this.text = text;
    }

    public Boolean getIsHomePage() {
        return isHomePage;
    }

    public void setIsHomePage(Boolean isHomePage) {
        this.isHomePage = isHomePage;
    }

    public Boolean getIsCampaign() {
        return isCampaign;
    }

    public void setIsCampaign(Boolean isCampaign) {
        this.isCampaign = isCampaign;
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