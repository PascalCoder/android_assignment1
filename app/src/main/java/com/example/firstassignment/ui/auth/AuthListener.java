package com.example.firstassignment.ui.auth;

public interface AuthListener {

    void onStarted();
    void onSuccess(String message);
    void onFailure(String message);

}
