package com.example.firstassignment.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserApiClient {

    private static UserApiClient instance;
    private MutableLiveData<String> loginResponse;

    public static UserApiClient getInstance() {
        if (instance == null) {
            instance = new UserApiClient();
        }
        return instance;
    }

    private UserApiClient() {
        loginResponse = new MutableLiveData<>();
    }

    public LiveData<String> getLoginResponse() {
        return loginResponse;
    }

    /*public void searchUser(String email, String password) {
        try {
            Response response = getUser(email, password).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void searchUser(String email, String password) {
        ServiceGenerator.getUserApi().userLogin(
                email, password).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    loginResponse.postValue(response.body().toString());
                } else {
                    loginResponse.postValue(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                loginResponse.postValue(t.getMessage());
            }
        });
    }
}
