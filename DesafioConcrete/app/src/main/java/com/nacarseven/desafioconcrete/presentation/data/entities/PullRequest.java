package com.nacarseven.desafioconcrete.presentation.data.entities;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Calendar;

/**
 * Created by nacarseven on 11/10/17.
 */

@Parcel
public class PullRequest {

    //region FIELDS
    String title;
    String body;
    @SerializedName("html_url")
    String htmlUrl;
    private Calendar date;
    @SerializedName("user")
    Author author;

    //endregion

    //region PROPERTIES

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    //endregion
}
