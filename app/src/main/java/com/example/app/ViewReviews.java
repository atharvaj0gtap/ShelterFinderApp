package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class ViewReviews extends AppCompatActivity {
    RatingBar reviewRating;
    double myRating = 0;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reviews);

        Intent intent = getIntent();
        username = findViewById(R.id.usernameView);
        //username.setText(intent.getStringExtra("username"));

        reviewRating = findViewById(R.id.reviewRating);

        reviewRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                myRating = ratingBar.getRating();
            }
        });
    }

    public void onBack(View v){
        finish();
    }
}