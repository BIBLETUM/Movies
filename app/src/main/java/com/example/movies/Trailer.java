package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trailer trailer = (Trailer) o;
        return Objects.equals(url, trailer.url) && Objects.equals(name, trailer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, name);
    }
}
