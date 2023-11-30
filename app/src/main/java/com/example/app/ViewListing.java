package com.example.app;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ViewListing extends AppCompatActivity {
    EditText city, address, startDate, endDate,maxOcc;

    CheckBox petF, smokeF;
    Spinner privacy;
    private int sYear, sMonth, sDay, eYear, eMonth, eDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_listing);

       city = findViewById(R.id.editTextText);
       address = findViewById(R.id.editTextText2);
       privacy = findViewById(R.id.spinner);
       maxOcc = findViewById(R.id.editTextText3);
       startDate = findViewById(R.id.editTextDate1);
       endDate = findViewById(R.id.editTextDate2);
       petF = findViewById(R.id.checkBox);
       smokeF = findViewById(R.id.checkBox2);

        String[] privacyOptions = {"private","public"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, privacyOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        privacy.setAdapter(adapter);
    }

    public void setStartDate(View v){
        if (v == startDate) {

            final Calendar c = Calendar.getInstance();
            sYear = c.get(Calendar.YEAR);
            sMonth = c.get(Calendar.MONTH);
            sDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            startDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, sYear, sMonth, sDay);
            datePickerDialog.show();
        }

    }

   public void setEndDate(View v){
        if (v == endDate) {

            final Calendar c = Calendar.getInstance();
            eYear = c.get(Calendar.YEAR);
            eMonth = c.get(Calendar.MONTH);
            eDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            endDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, eYear, eMonth, eDay);
            datePickerDialog.show();
        }

    }


    public void onBack(View v){


    }


    public void onUploadImage(View v){


    }


    public void onList(View v){


    }






}
