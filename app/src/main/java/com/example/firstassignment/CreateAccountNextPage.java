package com.example.firstassignment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstassignment.utils.StringUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccountNextPage extends AppCompatActivity {

    @BindView(R.id.btn_dob)
    Button btnDOB;
    @BindView(R.id.spn_country)
    Spinner spinner;
    Calendar calendar;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_next_page);
        ButterKnife.bind(this);

        btnDOB.setOnClickListener(view -> {

            calendar = Calendar.getInstance();

            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int myMonth = calendar.get(Calendar.MONTH);
            int myYear = calendar.get(Calendar.YEAR);

            datePickerDialog = new DatePickerDialog(CreateAccountNextPage.this,
                    (datePickerView, year, month, dayOfMonth) ->

                            btnDOB.setText(StringUtils.formatDate(month, dayOfMonth, year))

                    , myMonth, day, myYear);

            datePickerDialog.show();

        });

        spinner.setPrompt(getString(R.string.spinner_country_title));

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.countries, R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
    }
}
