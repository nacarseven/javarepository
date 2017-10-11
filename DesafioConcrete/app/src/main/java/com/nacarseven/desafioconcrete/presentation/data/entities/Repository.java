package com.nacarseven.desafioconcrete.presentation.data.entities;

/**
 * Created by nacarseven on 10/10/17.
 */

public class Repository {

    private String name;
    private String description;
    private Author author;


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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
