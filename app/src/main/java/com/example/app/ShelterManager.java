package com.example.app;

import java.util.ArrayList;
import java.util.List;

public class ShelterManager {

    private static ShelterManager instance;
    private List<Shelter> shelters;

    private ShelterManager() {
        shelters = new ArrayList<>();
        // Add example shelters if needed
    }

    public static ShelterManager getInstance() {
        if (instance == null) {
            instance = new ShelterManager();
        }
        return instance;
    }

    public List<Shelter> getShelters() {

        return shelters;
    }

    public void addShelter(Shelter shelter) {

        shelters.add(shelter);
    }

    public Shelter getShelterByName(String name) {
        for (Shelter shelter : shelters) {
            if (shelter.getName().equals(name)) {
                return shelter;
            }
        }
        return null; // Shelter not found
    }


}