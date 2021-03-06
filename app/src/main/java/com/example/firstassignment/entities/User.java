package com.example.firstassignment.entities;

public class User {

    private String email;
    private String password;
    private String name;
    private String username;
    private int photo;
    private String dateOfBirth;
    private String country;
    private String gender;
    private String address;

    public User() {
    }

    public User(String email, String password, String name,
                String username, int photo, String dateOfBirth,
                String country, String gender, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.username = username;
        this.photo = photo;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.gender = gender;
        this.address = address;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
