package com.jdurini.countriesflags.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountriesResponse {
    @SerializedName("total_items") private final int totalItems;
    @SerializedName("total_pages") private final int totalPages;
    @SerializedName("items") private final List<Country> countries;

    public CountriesResponse(int totalItems, int totalPages, List<Country> countries) {
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.countries = countries;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<Country> getCountries() {
        return countries;
    }
}
