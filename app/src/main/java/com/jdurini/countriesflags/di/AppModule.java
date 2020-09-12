package com.jdurini.countriesflags.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance() {
            return new retrofit2.Retrofit.Builder()
                    .baseUrl("https://mobile.xoom.com")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }
}
