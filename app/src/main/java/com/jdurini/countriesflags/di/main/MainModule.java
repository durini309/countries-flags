package com.jdurini.countriesflags.di.main;

import android.app.Application;
import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.jdurini.countriesflags.network.CountriesApi;
import com.jdurini.countriesflags.ui.adapters.CountryAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static CountryAdapter provideAdapter() {
        return new CountryAdapter();
    }

    @Provides
    static LinearLayoutManager provideLinearLayout(Application application) {
        return new LinearLayoutManager(application);
    }

    @Provides
    static CountriesApi provideCountriesApi(Retrofit retrofit) {
        return retrofit.create(CountriesApi.class);
    }
}
