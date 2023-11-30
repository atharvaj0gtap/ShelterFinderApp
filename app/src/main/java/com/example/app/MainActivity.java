package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText email;
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText date;
    EditText password;

    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.editTextText);
        email=findViewById(R.id.editTextText2);

        radioGroup = findViewById(R.id.radioGroup);

        date=findViewById(R.id.editTextDate);
        date.setOnClickListener(this);

        password=findViewById(R.id.editTextTextPassword);
    }
    public void onRegister(View view){
        String user = username.getText().toString();
        String mail = email.getText().toString();

        int selectedID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(selectedID);
        String gender = "";
        if (radioButton != null)
            gender = radioButton.getText().toString();


        String dateOfBirth = date.getText().toString();
        String pass = password.getText().toString();

        if(user.length() == 0)
            Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
        else if(!isValidEmail(mail))
            Toast.makeText(getApplicationContext(), "email missing", Toast.LENGTH_SHORT).show();
        else if(gender.isEmpty())
            Toast.makeText(getApplicationContext(), "gender missing", Toast.LENGTH_SHORT).show();
        else if(dateOfBirth.isEmpty())
            Toast.makeText(getApplicationContext(), "date of birth missing", Toast.LENGTH_SHORT).show();
        else if (pass.length() >= 8 && !isValidPasswordCriteria(pass))
            Toast.makeText(getApplicationContext(), "Password should contain 1 numeric digit, 1 uppercase letter, and 1 special character (@#$%^&+=!)", Toast.LENGTH_SHORT).show();
        else if(!isValidPassword(pass))
            Toast.makeText(getApplicationContext(), "password missing", Toast.LENGTH_SHORT).show();


        if (user.length() > 0 && isValidEmail(mail) && !gender.isEmpty() && !dateOfBirth.isEmpty() && isValidPassword(pass)) {
            Intent intent = new Intent(this, secondActivity.class);
            intent.putExtra("username", user);
            intent.putExtra("email", mail);
            intent.putExtra("gender", gender);
            intent.putExtra("dateOfBirth", dateOfBirth);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == date) {

            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }

    boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    boolean isValidPasswordCriteria(String password) {
        return password.matches(".*[0-9].*") && password.matches(".*[A-Z].*") && password.matches(".*[@#$%^&+=!].*");
    }
}