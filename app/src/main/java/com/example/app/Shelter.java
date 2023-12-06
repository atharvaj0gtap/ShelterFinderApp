package com.example.app;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Shelter  implements Serializable {


    private String city;
    private String listerName;
    private String address;
    private String privacy;
    private boolean petFriendly;
    private boolean smokeFriendly;
    private int occupancy;
    private int stayDuration;
    private double rating;
    private String imagePath;
    private ArrayList<String> reviews;

    // Other constructor and methods...

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

    public int getStayDuration() {
        return stayDuration;
    }

    public double getRating() {
        return rating;
    }

    public String getImagePath() {
        return imagePath;
    }

    public ArrayList<String> getReviews() {
        return reviews;
    }

    // Other setter methods...
    public Shelter(String city, String listerName, String address, String privacy, boolean petFriendly,
                   boolean smokeFriendly, int occupancy, int stayDuration, double rating, String imagePath,
                   ArrayList<String> reviews) {
        this.city = city;
        this.listerName = listerName;
        this.address = address;
        this.privacy = privacy;
        this.petFriendly = petFriendly;
        this.smokeFriendly = smokeFriendly;
        this.occupancy = occupancy;
        this.stayDuration = stayDuration;
        this.rating = rating;
        this.imagePath = imagePath;
        this.reviews = reviews;
    }
    // Convert Shelter object to JSON string
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    // Create Shelter object from JSON string
    public static Shelter fromJson(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Shelter.class);
    }

    // Convert ArrayList of Shelter objects to JSON string
    public static String listToJson(ArrayList<Shelter> shelterList) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Shelter>>() {}.getType();
        return gson.toJson(shelterList, type);
    }

    // Create ArrayList of Shelter objects from JSON string
    public static ArrayList<Shelter> listFromJson(String jsonString) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Shelter>>() {}.getType();
        return gson.fromJson(jsonString, type);
    }

}
