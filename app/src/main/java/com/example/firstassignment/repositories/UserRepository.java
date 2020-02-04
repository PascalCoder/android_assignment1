package com.example.firstassignment.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.firstassignment.network.UserApi;
import com.example.firstassignment.network.UserApiClient;

public class UserRepository {

    private static UserRepository instance;
    private UserApiClient userApiClient;

    public static UserRepository getInstance() {
        if (instance == null){
            instance = new UserRepository();
        }
        return instance;
    }

    private UserRepository(){
        userApiClient = UserApiClient.getInstance();
    }

    LiveData<String> userLogin(String email, String password) {
        LiveData<String> loginResponse;

        userApiClient.searchUser(email, password);
        loginResponse = userApiClient.getLoginResponse();

        return loginResponse;
    }
}
