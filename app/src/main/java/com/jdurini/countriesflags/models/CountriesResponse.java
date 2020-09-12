package com.jdurini.countriesflags.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountriesResponse {
    @SerializedName("total_pages") private final int totalPages;
    @SerializedName("items") private final List<Country> countries;

    public CountriesResponse(int totalPages, List<Country> countries) {
        this.totalPages = totalPages;
        this.countries = countries;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<Country> getCountries() {
        return countries;
    }
}
