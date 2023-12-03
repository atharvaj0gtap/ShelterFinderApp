// Shelter.java
package com.example.app;

public class Shelter {

    private String name;
    private String city;
    private String listerName;
    private String address;
    private String privacy;
    private boolean petFriendly;
    private boolean smokeFriendly;
    private int occupancy;
    private String image;
    private String review;
    private double rating;

    public Shelter(String listerName, String name, String city, String address, String privacy,
                   boolean petFriendly, boolean smokeFriendly, int occupancy,
                   String image, String review, double rating) {
        this.listerName = listerName;
        this.name = name;
        this.city = city;
        this.address = address;
        this.privacy = privacy;
        this.petFriendly = petFriendly;
        this.smokeFriendly = smokeFriendly;
        this.occupancy = occupancy;
        this.image = image;
        this.review = review;
        this.rating = rating;
    }

    // Getters
    public String getName() {
        return name;
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

    public int getOccupancy() {
        return occupancy;
    }

    public String getImage() {
        return image;
    }

    public String getReview() {
        return review;
    }

    public double getRating() {
        return rating;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

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

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}


