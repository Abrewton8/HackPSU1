package com.barelypassing.hackpsu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DBHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);

        // Student Button Handler
        Button sButton = findViewById(R.id.studentButton);
        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSecondActivity();
            }
        });

        // Tutor Button Handler
        Button tButton = findViewById(R.id.tutorButton);
        tButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTutor_Profile();
            }
        });

        // Submit Button Handler
        Button mButton = findViewById(R.id.submitButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = findViewById(R.id.nameTextField);
                EditText classCode = findViewById(R.id.classTextField);
                EditText number = findViewById(R.id.phoneTextField);
                EditText major = findViewById(R.id.majorTextField);

                dbHelper.insertTutor(classCode.getText().toString(), name.getText().toString(),
                        number.getText().toString(), major.getText().toString());
            }
        });

        // Spinner Handler
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        loadSpinnerData(spinner);
    }

    private void goToSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    private void goToTutor_Profile() {
        Intent intent = new Intent(this, Tutor_Profile.class);
        startActivity(intent);
    }

    private void loadSpinnerData(Spinner spinner) {
        DBHandler dbHandler = new DBHandler(getApplicationContext());
        List<String> classCodes = dbHandler.getAllClasses();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, classCodes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
