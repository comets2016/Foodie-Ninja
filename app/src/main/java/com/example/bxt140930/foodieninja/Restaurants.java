package com.example.bxt140930.foodieninja;

/**
 * Created by bxt140930 on 11/9/2016.
 */

public class Restaurants {
    int id;
    String name;
    String imageUrl;
    String WorkingHours;
    double EstimatedWait;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getWorkingHours() {
        return WorkingHours;
    }

    public void setWorkingHours(String workingHours) {
        WorkingHours = workingHours;
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

    public double getEstimatedWait() {
        return EstimatedWait;
    }

    public void setEstimatedWait(double EstimatWaitPerPerson) {
        this.EstimatedWait = EstimatWaitPerPerson;
    }
}
