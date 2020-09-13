package com.jdurini.countriesflags.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jdurini.countriesflags.models.CountriesResponse;
import com.jdurini.countriesflags.models.Country;
import com.jdurini.countriesflags.network.CountriesApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CountriesViewModel extends ViewModel {

    private final CountriesApi apiService;
    private boolean finishedFetching = false;

    public MutableLiveData<List<Country>> liveDataCountries = new MutableLiveData<>();;

    @Inject
    public CountriesViewModel(CountriesApi countriesApi) {
        this.apiService = countriesApi;
    }


    public LiveData<List<Country>> getCountries(int currentPage, int pageSize) {
        apiService.getCountries(currentPage, pageSize)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CountriesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(CountriesResponse countriesResponse) {
                        liveDataCountries.postValue(countriesResponse.getCountries());
                        finishedFetching = currentPage >= countriesResponse.getTotalPages();
                    }

                    @Override
                    public void onError(Throwable e) {
                        liveDataCountries.postValue(null);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        return liveDataCountries;
    }

    public boolean hasFinishedFetching() {
        return finishedFetching;
    }
}
