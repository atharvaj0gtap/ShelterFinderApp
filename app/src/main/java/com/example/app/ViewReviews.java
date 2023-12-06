package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class ViewReviews extends AppCompatActivity {
    RatingBar reviewRating;
    double myRating = 0;
    TextView username, reviewComment, userReviews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reviews);

        Intent intent = getIntent();
        username = findViewById(R.id.usernameView);
        //username.setText(intent.getStringExtra("username"));

        reviewRating = findViewById(R.id.reviewRating);
        reviewComment = findViewById(R.id.reviewInp);
        userReviews = findViewById(R.id.userReviews);

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

    public void onSubmitReview(View v){
        if (!isValidRating())
            Toast.makeText(this, "Please enter a star rating", Toast.LENGTH_SHORT).show();
        else if(!isValidComments())
            Toast.makeText(this, "Please enter comments", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(this, "Rating submitted", Toast.LENGTH_SHORT).show();
            //userReviews.setText("Username\nRating: " + myRating + "/5 stars\nComments: " + reviewComment.getText().toString().trim());

            reviewRating.setRating(0);
            reviewComment.setText("");
        }
    }

    private boolean isValidRating() {
        return myRating> 0;
    }

    private boolean isValidComments(){
        String txt = reviewComment.getText().toString().trim();
        return !txt.isEmpty();
    }
}