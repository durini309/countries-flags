package com.jdurini.countriesflags.di.main;

import androidx.lifecycle.ViewModel;

import com.jdurini.countriesflags.di.ViewModelKey;
import com.jdurini.countriesflags.viewmodels.CountriesViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class CountriesViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(CountriesViewModel.class)
    public abstract ViewModel bindCountriesViewModel(CountriesViewModel viewModel);
}
