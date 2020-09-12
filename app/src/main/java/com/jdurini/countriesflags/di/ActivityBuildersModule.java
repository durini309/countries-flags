package com.jdurini.countriesflags.di;

import com.jdurini.countriesflags.di.main.CountriesViewModelsModule;
import com.jdurini.countriesflags.di.main.MainFragmentsBuildersModule;
import com.jdurini.countriesflags.di.main.MainModule;
import com.jdurini.countriesflags.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {
                    CountriesViewModelsModule.class,
                    MainModule.class,
                    MainFragmentsBuildersModule.class
            }
    )
    abstract MainActivity contributeMainActivity();
}
