package com.example.movies;

import com.google.gson.annotations.SerializedName;

public class VideosResponce {
    @SerializedName("videos")
    private Videos videos;

    public Videos getVideos() {
        return videos;
    }

    public VideosResponce(Videos videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "VideosResponce{" +
                "videos=" + videos +
                '}';
    }
}
