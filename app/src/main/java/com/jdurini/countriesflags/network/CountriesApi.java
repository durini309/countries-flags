package com.jdurini.countriesflags.network;

import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesApi {
    private static final String API_BASE_URL = "https://mobile.xoom.com";

    public CountriesService CountriesApi() {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CountriesService.class);
    }
}
