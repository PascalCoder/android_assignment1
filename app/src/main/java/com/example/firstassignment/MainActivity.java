package com.example.firstassignment;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_create_acct)
    Button btnCreateAcct;

    @BindView(R.id.btn_sign_in)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btnLogin.setOnClickListener(view -> navigateToLoginPage());

        btnCreateAcct.setOnClickListener(view -> createAcct());

    }

    private void navigateToLoginPage() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void createAcct() {
        Intent intent = new Intent();
        intent.setClass(this, CreateAccount.class);

        startActivity(intent);
    }
}
