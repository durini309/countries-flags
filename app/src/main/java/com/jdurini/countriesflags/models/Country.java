package com.jdurini.countriesflags.models;

public class Country {
    private final static String FLAG_URL = "https://www.countryflags.io/%s/flat/64.png";
    private String code;
    private String name;

    public Country() {

    }

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getFlagUrl() {
        return String.format(FLAG_URL, this.code);
    }
}
