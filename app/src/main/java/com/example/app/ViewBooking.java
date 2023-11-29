package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class ViewBooking extends AppCompatActivity {
    private TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_booking);

        dateTextView = findViewById(R.id.dateTextView);

        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        // Get the selected date from the DatePicker
        int year = 2022;
        int month = 02;
        int day = 01;

        // Create and show the Date Picker Dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // Display the selected date in your desired format
                        String selectedDate = day + "-" + (month + 1) + "-" + year;
                        // Update the TextView with the selected date
                        dateTextView.setText(selectedDate);
                        Toast.makeText(ViewBooking.this, "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
                    }
                },
                year, month, day);

        // Show the Date Picker Dialog
        datePickerDialog.show();
    }
}