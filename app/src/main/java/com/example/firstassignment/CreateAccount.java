package com.example.firstassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstassignment.entities.FirstData;
import com.example.firstassignment.utils.ValidationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccount extends AppCompatActivity {

    @BindView(R.id.iv_arrow_left)
    ImageView backArrow;
    @BindView(R.id.iv_check_email)
    ImageView checkEmail;
    @BindView(R.id.iv_check_pwd)
    ImageView checkPwd;
    @BindView(R.id.iv_check_pwd_conf)
    ImageView checkPwdConfirm;

    @BindView(R.id.et_email)
    EditText emailAddress;
    @BindView(R.id.et_pwd)
    EditText password;
    @BindView(R.id.et_pwd_confirm)
    EditText passwordConfirm;

    @BindView(R.id.tv_email_error)
    TextView emailErrorMsg;
    @BindView(R.id.tv_pwd_error)
    TextView pwdErrorMsg;

    @BindView(R.id.btn_next)
    Button btnNextPage;

    String emailValue, passwordValue, passwordConfirmValue;

    boolean pwdNoMatchRegex;
    boolean emailIsValid, pwdIsValid, pwdConfIsValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        backArrow.setOnClickListener(view -> backToHome());

        emailAddress.setOnFocusChangeListener((view, b) -> validateEmail());

        password.setOnFocusChangeListener((view, b) -> validatePassword());
        passwordConfirm.setOnFocusChangeListener((view, b) -> validatePasswordConfirm());

        if (validatePasswordConfirm() && validateEmail()) {
            btnNextPage.setEnabled(true);
            btnNextPage.setOnClickListener(view -> createAcctNextPage(new FirstData(emailValue, passwordValue)));
        } else {
            btnNextPage.setEnabled(false);
        }
        btnNextPage.setOnClickListener(view -> createAcctNextPage(new FirstData(emailValue, passwordValue)));

    }

    private boolean validateEmail() {
        emailValue = emailAddress.getText().toString().trim();

        if (emailValue.length() > 0) {
            if (ValidationUtils.isEmailValid(emailValue)) {
                emailAddress.setBackgroundResource(R.drawable.border_color_good);

                checkEmail.setVisibility(View.VISIBLE);

                emailIsValid = true;
                if (pwdConfIsValid) { //validatePasswordConfirm()
                    btnNextPage.setEnabled(true);
                }

                return true;

            } else {
                if (emailValue.length() > 0) {
                    checkEmail.setVisibility(View.GONE);
                    emailAddress.setBackgroundResource(R.drawable.border_color_bad);

                    emailIsValid = false;
                    btnNextPage.setEnabled(false);

                    return false;

                } else {
                    checkEmail.setVisibility(View.GONE);

                    emailIsValid = false;
                    btnNextPage.setEnabled(false);

                    return false;
                }
            }
        } else {
            checkEmail.setVisibility(View.GONE);

            emailIsValid = false;
            btnNextPage.setEnabled(false);

            emailAddress.setBackgroundResource(R.drawable.border_none);
            return false;
        }
    }

    private boolean validatePassword() {
        passwordValue = password.getText().toString().trim();

        if (passwordValue.length() < 8) {
            pwdIsValid = false;
            btnNextPage.setEnabled(false);
            return false;
        } else {
            if (ValidationUtils.isPasswordValid(passwordValue)) {
                pwdIsValid = true;

                if (pwdConfIsValid && emailIsValid) {
                    btnNextPage.setEnabled(true);
                }
                return true;
            } else if (!passwordValue.equals(passwordConfirmValue)) {
                password.setBackgroundResource(R.drawable.border_color_bad);
                passwordConfirm.setBackgroundResource(R.drawable.border_color_bad);

                pwdIsValid = false;
                btnNextPage.setEnabled(false);

                return false;

            } else if (passwordValue.equals(passwordConfirmValue)
                    && ValidationUtils.isPasswordValid(passwordValue)) {
                password.setBackgroundResource(R.drawable.border_color_good);
                passwordConfirm.setBackgroundResource(R.drawable.border_color_good);

                pwdIsValid = true;
                pwdConfIsValid = true;

                if (emailIsValid) {
                    btnNextPage.setEnabled(true);
                }

                return true;

            } else {
                pwdNoMatchRegex = true;
                pwdIsValid = false;
                btnNextPage.setEnabled(false);

                return false;
            }
        }
    }

    private boolean validatePasswordConfirm() {
        validatePassword();
        passwordConfirmValue = passwordConfirm.getText().toString().trim();

        if (passwordConfirmValue.length() > 0) {
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

                pwdIsValid = pwdConfIsValid = true;

                if (emailIsValid) {
                    btnNextPage.setEnabled(true);
                }

                return true;

            } else if (pwdNoMatchRegex || passwordValue.length() < 8) { //One of the pwd does not match the regex
                password.setBackgroundResource(R.drawable.border_color_bad);
                passwordConfirm.setBackgroundResource(R.drawable.border_color_bad);

                pwdErrorMsg.setVisibility(View.VISIBLE);
                pwdErrorMsg.setText(getString(R.string.invalid_pwd_msg));
                pwdErrorMsg.setBackgroundResource(R.drawable.border_color_error_msg);

                checkPwd.setVisibility(View.GONE);
                checkPwdConfirm.setVisibility(View.GONE);

                pwdConfIsValid = false;

                btnNextPage.setEnabled(false);

                return false;
            } else { //The passwords do not match (not equal)
                checkPwd.setVisibility(View.GONE);
                checkPwdConfirm.setVisibility(View.GONE);

                password.setBackgroundResource(R.drawable.border_color_bad);
                passwordConfirm.setBackgroundResource(R.drawable.border_color_bad);

                pwdErrorMsg.setVisibility(View.VISIBLE);
                pwdErrorMsg.setText(getString(R.string.not_equal_pwd_msg));
                pwdErrorMsg.setBackgroundResource(R.drawable.border_color_error_msg);

                pwdConfIsValid = false;

                btnNextPage.setEnabled(false);

                return false;
            }
        } else {
            checkPwd.setVisibility(View.GONE);
            checkPwdConfirm.setVisibility(View.GONE);

            password.setBackgroundResource(R.drawable.border_none);
            passwordConfirm.setBackgroundResource(R.drawable.border_none);

            pwdConfIsValid = false;
            btnNextPage.setEnabled(false);

            return false;
        }
    }

    private void backToHome() {
        finish();
    }

    private void createAcctNextPage(FirstData firstData) {
        btnNextPage = findViewById(R.id.btn_next);

        if (!emailIsValid || !pwdIsValid || !pwdConfIsValid) {
            btnNextPage.setEnabled(false);
        }
        //btnNextPage.setEnabled(true);
        if (validateEmail() && validatePasswordConfirm()) {
            btnNextPage.setEnabled(true);
        }

        Intent intent = new Intent();
        intent.setClass(this, CreateAccountNextPage.class);
        intent.putExtra("firstData", firstData);

        //btnNextPage.setEnabled(true);

        startActivity(intent);
    }
}
