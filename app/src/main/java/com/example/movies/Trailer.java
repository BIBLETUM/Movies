package com.example.movies;

import com.google.gson.annotations.SerializedName;

public class Trailer {
    @SerializedName("url")
    private String url;
    @SerializedName("name")
    private String name;

    public Trailer(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
