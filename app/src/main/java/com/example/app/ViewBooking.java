package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class ViewBooking extends AppCompatActivity {
    EditText fromdate, todate;
    private int fYear, fMonth, fDay, tYear, tMonth, tDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_booking);

        fromdate = (EditText) findViewById(R.id.fromdateInp);
        todate = (EditText) findViewById(R.id.todateInp);
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

    }

}