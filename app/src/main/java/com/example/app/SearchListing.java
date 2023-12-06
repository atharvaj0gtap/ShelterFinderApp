package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SearchListing extends AppCompatActivity {
    private TextView username;
    private EditText cityEditText;
    private CheckBox petCheckBox;
    private CheckBox smokeCheckBox;
    private Spinner privacySpinner;
    private EditText numGuestsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_listing);

        // Get the spinner from the layout
        privacySpinner = findViewById(R.id.PrivacySpinner);

        // Define the privacy options
        String[] privacyOptions = {"Private", "Public"};

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, privacyOptions);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        privacySpinner.setAdapter(adapter);

        // Get other UI components
        cityEditText = findViewById(R.id.CityText);
        petCheckBox = findViewById(R.id.petCheckBox);
        smokeCheckBox = findViewById(R.id.SmokeCheckBox);
        numGuestsEditText = findViewById(R.id.Numguests);

        // Get username from the intent
        Intent intent = getIntent();
        username = findViewById(R.id.textView11);
        username.setText(intent.getStringExtra("username"));

        // Set up the Search button click listener
        Button searchButton = findViewById(R.id.Search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataToSearchResults();
            }
        });
    }

    private void sendDataToSearchResults() {
        // Get user input
        String city = cityEditText.getText().toString();
        boolean isPetFriendly = petCheckBox.isChecked();
        boolean isSmokeFriendly = smokeCheckBox.isChecked();
        String privacy = privacySpinner.getSelectedItem().toString();
        int numGuests = Integer.parseInt(numGuestsEditText.getText().toString());

        // Create an intent to start the SearchResults activity
        Intent intent = new Intent(SearchListing.this, SearchResults.class);

        // Pass the user input as extras in the intent
        intent.putExtra("CITY", city);
        intent.putExtra("PET_FRIENDLY", isPetFriendly);
        intent.putExtra("SMOKE_FRIENDLY", isSmokeFriendly);
        intent.putExtra("PRIVACY", privacy);
        intent.putExtra("NUM_GUESTS", numGuests);

        // Pass the username as an extra
        intent.putExtra("username", username.getText().toString());

        // Start the SearchResults activity
        startActivity(intent);
    }
}
