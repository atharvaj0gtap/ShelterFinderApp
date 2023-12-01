package com.example.app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Listing extends AppCompatActivity {
    EditText city, address, startDate, endDate,maxOcc;
    TextView username;
    CheckBox petF, smokeF;
    Spinner privacy;
    private int sYear, sMonth, sDay, eYear, eMonth, eDay;

    ImageView image;
    private final int GALLERY_REQ_CODE = 1000;
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
       image = findViewById(R.id.imageView);

        String[] privacyOptions = {"private","public"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, privacyOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        privacy.setAdapter(adapter);

        Intent intent = getIntent();
        username= findViewById(R.id.textView11);
        username.setText(intent.getStringExtra("username"));
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
        //go back to welcome screen when clicked
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra("username", username.getText().toString());
        // Start the Welcome screen activity
        startActivity(intent);

    }


    public void onUploadImage(View v){
        Intent iGallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(iGallery, GALLERY_REQ_CODE);
    }




    public void onList(View v){
        // NEED TO SEND DATA TO THE VIEW LISTINGS

        //go back to welcome screen when clicked
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra("username", username.getText().toString());
        // Start the Welcome screen activity
        startActivity(intent);



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE && data != null) {
                // For gallery
                Uri selectedImageUri = data.getData();
                image.setImageURI(selectedImageUri);
            }
        }
    }

}
