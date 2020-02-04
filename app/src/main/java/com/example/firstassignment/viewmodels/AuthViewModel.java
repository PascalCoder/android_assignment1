package com.example.firstassignment.viewmodels;

import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.firstassignment.ui.auth.AuthListener;

public class AuthViewModel extends ViewModel {

    public String loginEmail = "";
    public String loginPassword = "";

    public AuthListener authListener;

    public void onLoginButtonClick(View view) {
        authListener.onStarted();
        if (loginEmail.isEmpty() || loginPassword.isEmpty()) {
            authListener.onFailure("Invalid email and/or password");
            return;
        }

        authListener.onSuccess("\nEmail " + loginEmail + "\nPassword " + loginPassword);
    }
}
