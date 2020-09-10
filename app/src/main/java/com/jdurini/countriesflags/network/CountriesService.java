package com.jdurini.countriesflags.network;

import com.jdurini.countriesflags.models.CountriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CountriesService {
    @GET("/catalog/v2/countries")
    Call<CountriesResponse> getCountries(@Query("page") int page, @Query("page_size") int pageSize);
}
