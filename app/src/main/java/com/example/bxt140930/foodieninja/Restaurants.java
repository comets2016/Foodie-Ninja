package com.example.bxt140930.foodieninja;

/**
 * Created by bxt140930 on 11/9/2016.
 */

public class Restaurants {
    int id;
    String name;
    String imageUrl;
    int servingNumber;
    int lastIssuedTicketNum;
    double estimatWaitPerPerson;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getServingNumber() {
        return servingNumber;
    }

    public void setServingNumber(int servingNumber) {
        this.servingNumber = servingNumber;
    }

    public int getLastIssuedTicketNum() {
        return lastIssuedTicketNum;
    }

    public void setLastIssuedTicketNum(int lastIssuedTicketNum) {
        this.lastIssuedTicketNum = lastIssuedTicketNum;
    }

    public double getEstimatWaitPerPerson() {
        return estimatWaitPerPerson;
    }

    public void setEstimatWaitPerPerson(double estimatWaitPerPerson) {
        this.estimatWaitPerPerson = estimatWaitPerPerson;
    }
}
