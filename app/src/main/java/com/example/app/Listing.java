package com.example.app;

import android.app.DatePickerDialog;
import android.content.Context;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Listing extends AppCompatActivity {
    EditText city, address, startDate, endDate, maxOcc;
    TextView username;
    CheckBox petF, smokeF;
    Spinner privacy;
    private int sYear, sMonth, sDay, eYear, eMonth, eDay;

    ImageView image;
    private final int GALLERY_REQ_CODE = 1000;

    private List<Shelter> shelters;

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

        String[] privacyOptions = {"private", "public"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, privacyOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        privacy.setAdapter(adapter);

        Intent intent = getIntent();
        username = findViewById(R.id.textView11);
        username.setText(intent.getStringExtra("username"));

        // Initialize the JSON array from the file
        shelters = loadSheltersFromJson();
    }

    public void setStartDate(View v) {
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

    public void setEndDate(View v) {
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

    public void onBack(View v) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra("username", username.getText().toString());
        startActivity(intent);
    }

    public void onUploadImage(View v) {
        Intent iGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(iGallery, GALLERY_REQ_CODE);
    }

    public void onList(View v) {
        if(!isValidCity()){
            Toast.makeText(this, "Please enter a valid city", Toast.LENGTH_SHORT).show();
        }
        else if(!isValidAddress()){
            Toast.makeText(this, "Please enter a valid address", Toast.LENGTH_SHORT).show();
        }
        else if(!isValidMaxOccupancy()){
            Toast.makeText(this, "Please enter a valid Max Occupancy. Hint:Integer > 0", Toast.LENGTH_SHORT).show();
        }
        else if(!isValidDates()){
            Toast.makeText(this, "Please enter valid dates. Hint: Check-out date after Check-in date", Toast.LENGTH_SHORT).show();
        }



        if (isValidCity() && isValidAddress() && isValidMaxOccupancy() && isValidDates()) {
            Shelter newShelter = createShelterFromInput();
            shelters.add(newShelter);
            saveSheltersToJson();
            Toast.makeText(this, "Shelter listed successfully", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE && data != null) {
                Uri selectedImageUri = data.getData();
                image.setImageURI(selectedImageUri);
            }
        }
    }

    private Shelter createShelterFromInput() {
        String cityText = city.getText().toString().trim();
        String addressText = address.getText().toString().trim();
        String privacyText = privacy.getSelectedItem().toString();
        int maxOccupancy = Integer.parseInt(maxOcc.getText().toString().trim());
        String startDateText = startDate.getText().toString().trim();
        String endDateText = endDate.getText().toString().trim();
        boolean petFriendly = petF.isChecked();
        boolean smokeFriendly = smokeF.isChecked();

        return new Shelter(cityText, "", addressText, privacyText, petFriendly, smokeFriendly, maxOccupancy, 0, 0.0, "", new ArrayList<>());
    }

    private void saveSheltersToJson() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(shelters);

        try {
            OutputStream outputStream = openFileOutput("shelters.json", Context.MODE_PRIVATE);
            outputStream.write(jsonString.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Shelter> loadSheltersFromJson() {
        Gson gson = new Gson();
        try {
            InputStream inputStream = openFileInput("shelters.json");
            InputStreamReader reader = new InputStreamReader(inputStream);
            Type type = new TypeToken<List<Shelter>>() {}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private boolean isValidCity() {
        String cityText = city.getText().toString().trim();
        return !cityText.isEmpty();
    }

    private boolean isValidAddress() {
        String addressText = address.getText().toString().trim();
        return !addressText.isEmpty();
    }

    private boolean isValidMaxOccupancy() {
        String occupancyText = maxOcc.getText().toString().trim();
        if (occupancyText.isEmpty()) {
            return false;
        }

        try {
            int occupancy = Integer.parseInt(occupancyText);
            return occupancy > 0;
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

        return endDate.after(startDate);
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
