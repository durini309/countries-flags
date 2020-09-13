package com.jdurini.countriesflags.di.main;

import com.jdurini.countriesflags.ui.countries.CountriesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentsBuildersModule {

    @ContributesAndroidInjector(
            modules = {
                    CountriesViewModelsModule.class,
                    MainModule.class
            }
    )
    abstract CountriesFragment contributeCountriesFragment();
}
