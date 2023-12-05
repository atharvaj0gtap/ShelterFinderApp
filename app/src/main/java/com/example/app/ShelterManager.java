package com.example.app;

import java.util.ArrayList;
import java.util.List;

public class ShelterManager {

    private static ShelterManager instance;
    private List<Shelter> shelters;

    // Inside the ShelterManager constructor
    public ShelterManager() {
        shelters = new ArrayList<>();

        // Add example shelters
        Shelter shelter1 = new Shelter("John's Shelter", "Kelowna", "123 Main St", "Private", true, false, "Single", "2023-01-01", "2023-02-01", null, new ArrayList<>(), 4.5);
        Shelter shelter2 = new Shelter("Lisa's Pet Haven", "Vancouver", "456 Oak St", "Shared", true, true, "Family", "2023-03-01", "2023-04-01", null, new ArrayList<>(), 3.8);
        Shelter shelter3 = new Shelter("Mike's Cozy Corner", "Kelowna", "789 Elm St", "Private", false, true, "Single", "2023-05-01", "2023-06-01", null, new ArrayList<>(), 4.2);

        shelters.add(shelter1);
        shelters.add(shelter2);
        shelters.add(shelter3);
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

    public Shelter getShelterByID(int ID) {
        for (Shelter shelter : shelters) {
            if (shelter.getID() == ID) {
                return shelter;
            }
        }
        return null; // Shelter not found
    }


}