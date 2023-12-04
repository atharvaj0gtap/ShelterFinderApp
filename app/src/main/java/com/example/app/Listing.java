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
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        if(!isValidCity())
            Toast.makeText(this, "Please enter a City", Toast.LENGTH_SHORT).show();

        else if(!isValidAddress())
            Toast.makeText(this, "Please enter a address", Toast.LENGTH_SHORT).show();

        else if (!isValidMaxOccupancy())
            Toast.makeText(this, "Please enter a valid Max Occupancy (Hint: Integer > 0 )", Toast.LENGTH_SHORT).show();

        else if (!isValidDates())
            Toast.makeText(this, "Please enter a valid start and/or end date", Toast.LENGTH_SHORT).show();



        if (!isValidCity() || !isValidAddress() || !isValidMaxOccupancy() || !isValidDates()) {
                return;
            }

        // NEED TO SEND DATA TO THE VIEW LISTINGS
        Shelter curShelter = new Shelter(username.getText().toString(),city.getText().toString(),address.getText().toString(),privacy.getSelectedItem().toString(),petF.isChecked(),smokeF.isChecked()
                ,maxOcc.getText().toString(),startDate.getText().toString(),endDate.getText().toString(),image,null,0);

      /*  Shelter(String listerName, String name, String city, String address, String privacy,
        boolean petFriendly, boolean smokeFriendly, int occupancy,
        String image, String review, double rating) */

        Intent intent = new Intent(this, SearchListing.class);
        intent.putExtra("shelter", curShelter);
        // Start the activity
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


    private boolean isValidCity() {
        String cityText = city.getText().toString().trim();
        return !cityText.isEmpty(); // Add more validation as needed
    }

    private boolean isValidAddress() {
        String addressText = address.getText().toString().trim();
        return !addressText.isEmpty(); // Add more validation as needed
    }

    private boolean isValidMaxOccupancy() {
        String occupancyText = maxOcc.getText().toString().trim();
        if (occupancyText.isEmpty()) {
            return false;
        }

        try {
            int occupancy = Integer.parseInt(occupancyText);
            return occupancy > 0; // Additional conditions can be added
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidDates() {
        String startDateText = startDate.getText().toString().trim();
        String endDateText = endDate.getText().toString().trim();

        if (startDateText.isEmpty() || endDateText.isEmpty()) {
            return false;
        }

        Date startDate = parseDate(startDateText);
        Date endDate = parseDate(endDateText);

        // Check if end date is later than start date
        return endDate.after(startDate);
    }

    private Date parseDate(String dateString) {
        // You need to implement the logic to parse the date string into a Date object
        // For example, you can use SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


}
