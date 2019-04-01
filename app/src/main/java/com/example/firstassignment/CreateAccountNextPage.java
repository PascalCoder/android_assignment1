package com.example.firstassignment;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.Calendar;

public class CreateAccountNextPage extends AppCompatActivity {

    Button btnDOB;
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_next_page);

        btnDOB = (Button)findViewById(R.id.btn_dob);
        btnDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int myMonth = calendar.get(Calendar.MONTH);
                int myYear = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(CreateAccountNextPage.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePickerView, int year, int month, int dayOfMonth) {

                                btnDOB.setText(month + 1 +"/" + dayOfMonth + "/" + year);
                            }
                        }, myMonth, day, myYear);

                datePickerDialog.show();
            }
        });

        Spinner spinner = findViewById(R.id.spn_country);
        spinner.setPrompt(getString(R.string.spinner_country_title));

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.countries, R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
    }
}
