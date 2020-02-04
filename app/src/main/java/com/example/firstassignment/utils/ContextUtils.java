package com.example.firstassignment.utils;

import android.content.Context;
import android.widget.Toast;

public class ContextUtils {

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
