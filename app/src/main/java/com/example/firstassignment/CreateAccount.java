package com.example.firstassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {

    ImageView backArrow, checkEmail, checkPwd, checkPwdConfirm;
    EditText emailAddress, password, passwordConfirm;
    String emailValue, passwordValue, passwordConfirmValue;
    TextView emailErrorMsg, pwdErrorMsg;
    boolean pwdNoMatchRegex;

    Button btnNextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        backArrow = findViewById(R.id.iv_arrow_left);
        backArrow.setOnClickListener(view -> backToHome());

        emailAddress = findViewById(R.id.et_email);
        emailAddress.setOnFocusChangeListener((view, b) -> validateEmail());

        password = findViewById(R.id.et_pwd);
        passwordConfirm = findViewById(R.id.et_pwd_confirm);
        password.setOnFocusChangeListener((view, b) -> validatePassword());
        passwordConfirm.setOnFocusChangeListener((view, b) -> validatePasswordConfirm());

        btnNextPage = findViewById(R.id.btn_next);
        if(validatePasswordConfirm() && validateEmail()){
            btnNextPage.setEnabled(true);
            btnNextPage.setOnClickListener(view -> createAcctNextPage());
        }else{
            btnNextPage.setEnabled(false);
        }
        btnNextPage.setOnClickListener(view -> createAcctNextPage());

    }

    private boolean validateEmail() {
        emailValue = emailAddress.getText().toString().trim();

        emailErrorMsg = findViewById(R.id.tv_email_error);
        checkEmail = findViewById(R.id.iv_check_email);

        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher myMatcher = pattern.matcher(emailValue);

        btnNextPage = findViewById(R.id.btn_next);

        if(emailValue.length() > 0) {
            if (myMatcher.find()) {
                emailAddress.setBackgroundResource(R.drawable.border_color_good);

                checkEmail.setVisibility(View.VISIBLE);

                if(validatePasswordConfirm()){
                    btnNextPage.setEnabled(true);
                }

                return true;

            } else {
                if (emailValue.length() > 0) {
                    checkEmail.setVisibility(View.GONE);
                    emailAddress.setBackgroundResource(R.drawable.border_color_bad);

                    btnNextPage.setEnabled(false);

                    return false;

                } else {
                    checkEmail.setVisibility(View.GONE);
                    btnNextPage.setEnabled(false);

                    return false;
                }
            }
        }else{
            checkEmail.setVisibility(View.GONE);
            btnNextPage.setEnabled(false);

            emailAddress.setBackgroundResource(R.drawable.border_none);
            return false;
        }
    }

    private boolean validatePassword() {
        passwordValue = password.getText().toString().trim();

        String pwdRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        Pattern pattern = Pattern.compile(pwdRegex);
        Matcher myMatcher = pattern.matcher(passwordValue);
        if(passwordValue.length() < 8){
            return false;
        }else{
            if(myMatcher.find()){
                return true;
            } else if(!passwordValue.equals(passwordConfirmValue)){
                password.setBackgroundResource(R.drawable.border_color_bad);
                passwordConfirm.setBackgroundResource(R.drawable.border_color_bad);

                return false;

            }else if(passwordValue.equals(passwordConfirmValue) && myMatcher.find()){
                password.setBackgroundResource(R.drawable.border_color_good);
                passwordConfirm.setBackgroundResource(R.drawable.border_color_good);

                return true;

            }else{
                pwdNoMatchRegex = true;
                return false;
            }
        }
    }
    private boolean validatePasswordConfirm(){
        validatePassword();
        passwordConfirmValue = passwordConfirm.getText().toString().trim();

        pwdErrorMsg = findViewById(R.id.tv_pwd_error);

        checkPwd = findViewById(R.id.iv_check_pwd);
        checkPwdConfirm = findViewById(R.id.iv_check_pwd_conf);

        //btnNextPage = findViewById(R.id.btn_next);

        if(passwordConfirmValue.length() > 0) {
            if (passwordConfirmValue.equals(passwordValue) && validatePassword()) {

                pwdErrorMsg.setVisibility(View.GONE);
                password.setBackgroundResource(R.drawable.border_color_good);
                passwordConfirm.setBackgroundResource(R.drawable.border_color_good);

                /*if(validateEmail()){
                    btnNextPage.setEnabled(true);
                }*/

                //Let's add the check mark to show that the passwords match and are valid
                checkPwd.setVisibility(View.VISIBLE);
                checkPwdConfirm.setVisibility(View.VISIBLE);

                return true;

            } else if (pwdNoMatchRegex || passwordValue.length() < 8) { //One of the pwd does not match the regex
                password.setBackgroundResource(R.drawable.border_color_bad);
                passwordConfirm.setBackgroundResource(R.drawable.border_color_bad);

                pwdErrorMsg.setVisibility(View.VISIBLE);
                pwdErrorMsg.setText(getString(R.string.invalid_pwd_msg));
                pwdErrorMsg.setBackgroundResource(R.drawable.border_color_error_msg);

                checkPwd.setVisibility(View.GONE);
                checkPwdConfirm.setVisibility(View.GONE);

                //btnNextPage.setEnabled(false);

                return false;
            } else { //The passwords do not match (not equal)
                checkPwd.setVisibility(View.GONE);
                checkPwdConfirm.setVisibility(View.GONE);

                password.setBackgroundResource(R.drawable.border_color_bad);
                passwordConfirm.setBackgroundResource(R.drawable.border_color_bad);

                pwdErrorMsg.setVisibility(View.VISIBLE);
                pwdErrorMsg.setText(getString(R.string.not_equal_pwd_msg));
                pwdErrorMsg.setBackgroundResource(R.drawable.border_color_error_msg);

                //btnNextPage.setEnabled(false);

                return false;
            }
        }else{
            checkPwd.setVisibility(View.GONE);
            checkPwdConfirm.setVisibility(View.GONE);

            password.setBackgroundResource(R.drawable.border_none);
            passwordConfirm.setBackgroundResource(R.drawable.border_none);

            //btnNextPage.setEnabled(false);

            return false;
        }
    }

    private void backToHome() {
        finish();
    }

    private void createAcctNextPage(){
        btnNextPage = findViewById(R.id.btn_next);
        btnNextPage.setEnabled(true);
        if(validateEmail() && validatePasswordConfirm()){
            btnNextPage.setEnabled(true);
        }

        Intent intent = new Intent();
        intent.setClass(this, CreateAccountNextPage.class);

        //btnNextPage.setEnabled(true);

        startActivity(intent);
    }
}
