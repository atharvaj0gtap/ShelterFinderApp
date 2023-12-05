package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SearchListing extends AppCompatActivity {
        TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_listing);

        // Get the spinner from the layout
        Spinner privacySpinner = findViewById(R.id.PrivacySpinner);

        // Define the privacy options
        String[] privacyOptions = {"Private", "Public"};

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, privacyOptions);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        privacySpinner.setAdapter(adapter);

        Intent intent = getIntent();
        username= findViewById(R.id.textView11);
        username.setText(intent.getStringExtra("username"));
    }
}
