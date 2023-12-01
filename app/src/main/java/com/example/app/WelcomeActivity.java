package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // setting username on the page
        Intent intent = getIntent();
       username= findViewById(R.id.textView11);
       username.setText(intent.getStringExtra("username"));

    }

    public void onList(View v){
        Intent intent = new Intent(this, Listing.class);
        intent.putExtra("username", username.getText().toString());
        startActivity(intent);
    }

    public void onFind(View v){

    }









}

