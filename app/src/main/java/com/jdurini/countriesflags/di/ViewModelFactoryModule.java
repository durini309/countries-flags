package com.jdurini.countriesflags.di;

import androidx.lifecycle.ViewModelProvider;

import com.jdurini.countriesflags.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory vmFactory);
}
