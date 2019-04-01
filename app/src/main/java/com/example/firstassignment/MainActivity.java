package com.example.firstassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCreateAcct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateAcct = findViewById(R.id.btn_create_acct);
        btnCreateAcct.setOnClickListener(view -> createAcct());

    }

    private void createAcct() {
        Intent intent = new Intent();
        intent.setClass(this, CreateAccount.class);

        startActivity(intent);
    }
}
