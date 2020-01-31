package com.example.firstassignment.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class FirstData implements Parcelable {

    private String email;
    private String password;
    private String passwordConfirm;

    public FirstData(){}

    public FirstData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public FirstData(String email, String password, String passwordConfirm) {
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    protected FirstData(Parcel in) {
        email = in.readString();
        password = in.readString();
        passwordConfirm = in.readString();
    }

    public static final Creator<FirstData> CREATOR = new Creator<FirstData>() {
        @Override
        public FirstData createFromParcel(Parcel in) {
            return new FirstData(in);
        }

        @Override
        public FirstData[] newArray(int size) {
            return new FirstData[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(passwordConfirm);
    }
}
