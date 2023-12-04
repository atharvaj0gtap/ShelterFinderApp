// Shelter.java
package com.example.app;

import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Shelter implements Serializable {
    private static int nextId = 1;
    private int ID;
    private String city;
    private String listerName;
    private String address;
    private String privacy;
    private boolean petFriendly;
    private boolean smokeFriendly;
    private String occupancy;
    private String startDate;
    private String endDate;
    private ImageView image;
    private ArrayList<String> review;
    private double rating;

    public Shelter(String listerName, String city, String address, String privacy,
                   boolean petFriendly, boolean smokeFriendly, String occupancy, String startDate, String endDate,
                   ImageView image, ArrayList<String> review, double rating) {
        this.listerName = listerName;
        this.city = city;
        this.address = address;
        this.privacy = privacy;
        this.petFriendly = petFriendly;
        this.smokeFriendly = smokeFriendly;
        this.occupancy = occupancy;
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
        this.review = review;
        this.rating = rating;
    }

    // Getters
    public int getID() {return ID;}
    private static int getNextId() {
        return nextId++;
    }
    public String getCity() {
        return city;
    }

    public String getListerName() {
        return listerName;
    }

    public String getAddress() {
        return address;
    }

    public String getPrivacy() {
        return privacy;
    }

    public boolean isPetFriendly() {
        return petFriendly;
    }

    public boolean isSmokeFriendly() {
        return smokeFriendly;
    }

    public String getOccupancy() {
        return occupancy;
    }

    public ImageView getImage() {
        return image;
    }

    public ArrayList<String> getReviews() {
        return review;
    }

    public double getRating() {
        return rating;
    }

    // Setters

    public void setCity(String city) {
        this.city = city;
    }

    public void setListerName(String listerName) {
        this.listerName = listerName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public void setPetFriendly(boolean petFriendly) {
        this.petFriendly = petFriendly;
    }

    public void setSmokeFriendly(boolean smokeFriendly) {
        this.smokeFriendly = smokeFriendly;
    }

    public void setOccupancy(String occupancy) {
        this.occupancy = occupancy;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setReview(ArrayList<String> review) {
        this.review = review;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}


