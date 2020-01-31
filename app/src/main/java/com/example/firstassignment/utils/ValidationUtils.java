package com.example.firstassignment.utils;

import android.support.v4.util.PatternsCompat;

import java.util.regex.Pattern;

public class ValidationUtils {

    public static boolean isEmailValid(CharSequence email) {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isPasswordValid(CharSequence password) {
        String pwdRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        Pattern pattern = Pattern.compile(pwdRegex);

        return pattern.matcher(password).find();
    }

    public static boolean doPasswordsMatch(CharSequence password, CharSequence passwordRepeat) {
        return password.equals(passwordRepeat);
    }
}
