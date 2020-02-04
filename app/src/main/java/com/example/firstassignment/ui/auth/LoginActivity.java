package com.example.firstassignment.ui.auth;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.firstassignment.R;
import com.example.firstassignment.databinding.ActivityLoginBinding;
import com.example.firstassignment.utils.ContextUtils;
import com.example.firstassignment.viewmodels.AuthViewModel;

public class LoginActivity extends AppCompatActivity implements AuthListener {

    ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        AuthViewModel authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        mBinding.setViewmodel(authViewModel);

        authViewModel.authListener = this;
    }

    @Override
    public void onStarted() {
        ContextUtils.showToast(this, "Login started");
    }

    @Override
    public void onSuccess(String message) {
        ContextUtils.showToast(this, "Login success" + message);
    }

    @Override
    public void onFailure(String message) {
        ContextUtils.showToast(this, message);
    }
}
