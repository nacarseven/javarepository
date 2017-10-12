package com.nacarseven.desafioconcrete.presentation.data.entities;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by nacarseven on 10/10/17.
 */

@Parcel
public class Repository {

    String name;
    String description;
    @SerializedName("forks_count")
    int forks;
    @SerializedName("stargazers_count")
    int stars;
    @SerializedName("owner")
    Author author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
