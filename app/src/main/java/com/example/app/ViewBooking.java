package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ViewBooking extends AppCompatActivity {
    EditText fromdate, todate, occupancy, comments;
    private int fYear, fMonth, fDay, tYear, tMonth, tDay;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_booking);

        Intent intent = getIntent();
        username = findViewById(R.id.usernameView1);
        //username.setText(intent.getStringExtra("username"));

        fromdate = (EditText) findViewById(R.id.fromdateInp);
        todate = (EditText) findViewById(R.id.todateInp);
        occupancy = (EditText) findViewById(R.id.noInp);
        comments = (EditText) findViewById(R.id.detailsInp);
    }

    public void setFromDate(View v){
        if (v == fromdate) {

            final Calendar c = Calendar.getInstance();
            fYear = c.get(Calendar.YEAR);
            fMonth = c.get(Calendar.MONTH);
            fDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            fromdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, fYear, fMonth, fDay);
            datePickerDialog.show();
        }
    }

    public void setToDate(View v){
        if (v == todate) {

            final Calendar c = Calendar.getInstance();
            tYear = c.get(Calendar.YEAR);
            tMonth = c.get(Calendar.MONTH);
            tDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            todate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, tYear, tMonth, tDay);
            datePickerDialog.show();
        }
    }

    public void onBack(View v){
        finish();
    }

    public void onBook(View v){
        if (!isValidDates())
            Toast.makeText(this, "Please enter a valid to and/or from date", Toast.LENGTH_SHORT).show();
        else if(!isValidOccupancy())
            Toast.makeText(this, "Please enter a valid Occupancy (Hint: Integer > 0 )", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(this, "Booked successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, WelcomeActivity.class);
            // Add booking under users account
            startActivity(intent);
        }
    }

    private boolean isValidOccupancy() {
        String occupancyText = occupancy.getText().toString().trim();
        if (occupancyText.isEmpty()) {
            return false;
        }

        try {
            int occupancy = Integer.parseInt(occupancyText);
            return occupancy > 0; //&& occupancy < maxOccupancy;   // Make sure to edit this once max is known
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidDates() {
        //Make sure to enter dates between the given dates for listing

        String fromDateText = fromdate.getText().toString().trim();
        String toDateText = todate.getText().toString().trim();

        if (fromDateText.isEmpty() || toDateText.isEmpty()) {
            return false;
        }

        Date fromDate = parseDate(fromDateText);
        Date toDate = parseDate(toDateText);

        return toDate.after(fromDate);
    }

    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}