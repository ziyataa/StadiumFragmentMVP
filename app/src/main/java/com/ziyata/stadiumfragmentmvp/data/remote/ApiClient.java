package com.ziyata.stadiumfragmentmvp.data.remote;

import com.ziyata.stadiumfragmentmvp.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;
    public static Retrofit getCliend(){
        retrofit = new Retrofit.Builder().
                baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
