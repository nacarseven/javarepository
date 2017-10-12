package com.nacarseven.desafioconcrete.presentation.data.entities;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by nacarseven on 10/10/17.
 */

@Parcel
public class Author {

    String login;
    String name;
    String fullName;
    @SerializedName("avatar_url")
    String imageUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
