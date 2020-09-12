package com.jdurini.countriesflags.network;

import com.jdurini.countriesflags.models.CountriesResponse;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CountriesApi {
    @GET("/catalog/v2/countries")
    Flowable<CountriesResponse> getCountries(
            @Query("page") int page,
            @Query("page_size") int pageSize
    );
}
