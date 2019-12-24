package com.example.bloodmateapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class NewPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        Spinner bloodTypeSpinner = (Spinner) findViewById(R.id.bloodType);

        ArrayAdapter<String> bloodTypeAdapter = new ArrayAdapter<String>(NewPostActivity.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.bloodTypeList));
        bloodTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodTypeSpinner.setAdapter(bloodTypeAdapter);

        Spinner citiesSpinner = (Spinner) findViewById(R.id.city);

        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<String>(NewPostActivity.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.cityList));
        citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citiesSpinner.setAdapter(citiesAdapter);

        Spinner hospitalsSpinner = (Spinner) findViewById(R.id.hospital);

        ArrayAdapter<String> hospitalsAdapter = new ArrayAdapter<String>(NewPostActivity.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.hospitals));
        hospitalsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hospitalsSpinner.setAdapter(hospitalsAdapter);


    }
}
