package com.example.bxt140930.foodieninja;

/**
 * Created by bxt140930 on 11/9/2016.
 */

public class Menu {
    int id;
    String name;
    String imageUrl;
    String Description;
    double Price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        Description = Description;
    }
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

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }
}
