package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity {

    private List<Shelter> shelters;
    private List<Shelter> filteredShelters;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        // Load shelter data from JSON
        loadAllSheltersFromJson();

        // Get user input from the intent
        Intent intent = getIntent();
        String city = intent.getStringExtra("CITY");
        boolean isPetFriendly = intent.getBooleanExtra("PET_FRIENDLY", false);
        boolean isSmokeFriendly = intent.getBooleanExtra("SMOKE_FRIENDLY", false);
        String privacy = intent.getStringExtra("PRIVACY");
        int numGuests = intent.getIntExtra("NUM_GUESTS", 0);

        // Filter shelters based on user input
        filterShelters(city, isPetFriendly, isSmokeFriendly, privacy, numGuests);

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ShelterAdapter adapter = new ShelterAdapter(this, filteredShelters);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void loadAllSheltersFromJson() {
        Gson gson = new Gson();
        try {
            // Load existing shelters from assets file
            InputStream assetsInputStream = getAssets().open("shelters.json");
            InputStreamReader assetsReader = new InputStreamReader(assetsInputStream);
            Type assetsType = new TypeToken<List<Shelter>>() {}.getType();
            List<Shelter> assetsShelters = gson.fromJson(assetsReader, assetsType);

            // Load shelters from internal storage
            InputStream internalInputStream = openFileInput("shelters.json");
            InputStreamReader internalReader = new InputStreamReader(internalInputStream);
            Type internalType = new TypeToken<List<Shelter>>() {}.getType();
            List<Shelter> internalShelters = gson.fromJson(internalReader, internalType);

            // Combine existing shelters and newly added shelters
            if (assetsShelters != null && internalShelters != null) {
                shelters = new ArrayList<>(assetsShelters);
                shelters.addAll(internalShelters);
            } else if (assetsShelters != null) {
                shelters = new ArrayList<>(assetsShelters);
            } else if (internalShelters != null) {
                shelters = new ArrayList<>(internalShelters);
            } else {
                shelters = new ArrayList<>(); // Initialize with an empty list if both are null
            }
        } catch (IOException e) {
            e.printStackTrace();
            shelters = new ArrayList<>();
        }
    }

    private void filterShelters(String city, boolean isPetFriendly, boolean isSmokeFriendly, String privacy, int numGuests) {
        filteredShelters = new ArrayList<>();

        for (Shelter shelter : shelters) {
            // Filter based on city
            if (!city.isEmpty() && !shelter.getCity().equalsIgnoreCase(city)) {
                continue;
            }

            // Filter based on pet-friendliness
            if (isPetFriendly && !shelter.isPetFriendly()) {
                continue;
            }

            // Filter based on smoke-friendliness
            if (isSmokeFriendly && !shelter.isSmokeFriendly()) {
                continue;
            }

            // Filter based on privacy
            if (!privacy.isEmpty() && !shelter.getPrivacy().equalsIgnoreCase(privacy)) {
                continue;
            }

            // Filter based on occupancy
            if (numGuests > 0 && shelter.getOccupancy() < numGuests) {
                continue;
            }

            // If all filters passed, add the shelter to the filtered list
            filteredShelters.add(shelter);
        }
    }
}
