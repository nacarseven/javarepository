package com.nacarseven.desafioconcrete.presentation.data.entities;

/**
 * Created by nacarseven on 10/10/17.
 */

public class Author {

    private String name;
    private String imageUrl;
    private int qtyStars;
    private int qtyForks;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQtyStars() {
        return qtyStars;
    }

    public void setQtyStars(int qtyStars) {
        this.qtyStars = qtyStars;
    }

    public int getQtyForks() {
        return qtyForks;
    }

    public void setQtyForks(int qtyForks) {
        this.qtyForks = qtyForks;
    }
}
