package com.jdurini.countriesflags;

import androidx.lifecycle.LiveData;

import com.jdurini.countriesflags.models.CountriesResponse;
import com.jdurini.countriesflags.models.Country;
import com.jdurini.countriesflags.network.CountriesApi;
import com.jdurini.countriesflags.viewmodels.CountriesViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

@RunWith(RobolectricTestRunner.class)
public class CountriesViewModelTest {
    CountriesApi countriesApi;
    CountriesViewModel countriesViewModel;
    LiveData<List<Country>> liveData;

    @Before
    public void mockSetup() {
        countriesApi = Mockito.mock(CountriesApi.class);
        countriesViewModel = new CountriesViewModel(countriesApi);
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    // Tests successful call from API
    @Test
    public void test_responseWithCountries() {
        List<Country> mockedCountries = new ArrayList<Country>(){
            { add(new Country()); }
            { add(new Country()); }
        };
        CountriesResponse mockedRes = new CountriesResponse(1, mockedCountries);
        Mockito.doReturn(Flowable.just(mockedRes)).when(countriesApi).getCountries(Mockito.anyInt(), Mockito.anyInt());
        liveData = countriesViewModel.getCountries(1, 1);
        Assert.assertNotNull(liveData.getValue());
        Assert.assertEquals(liveData.getValue().size(), 2);
    }

    // Tests that liveData value is null when an error is returned from API
    @Test
    public void test_apiResponsesWithError() {
        Mockito.doReturn(Flowable.error(new Exception("Some unexpected error")))
                .when(countriesApi).getCountries(Mockito.anyInt(), Mockito.anyInt());
        liveData = countriesViewModel.getCountries(1, 1);
        Assert.assertNull(liveData.getValue());
    }

    // Tests if there are some pages left from the API
    @Test
    public void test_countriesAreLeft() {
        int currentPage = 4;
        int mockedTotalPages = 5;
        List<Country> mockedCountries = new ArrayList<Country>(){{add(new Country());}};
        CountriesResponse mockedRes = new CountriesResponse(mockedTotalPages, mockedCountries);
        Mockito.doReturn(Flowable.just(mockedRes)).when(countriesApi).getCountries(Mockito.anyInt(), Mockito.anyInt());
        liveData = countriesViewModel.getCountries(currentPage, 1);
        Assert.assertNotNull(liveData.getValue());
        Assert.assertFalse(countriesViewModel.hasFinishedFetching());
    }

    // Tests that there are no pages left from the API
    @Test
    public void test_paginationHasEnded() {
        int currentPage = 5;
        int mockedTotalPages = 5;
        List<Country> mockedCountries = new ArrayList<Country>(){{add(new Country());}};
        CountriesResponse mockedRes = new CountriesResponse(mockedTotalPages, mockedCountries);
        Mockito.doReturn(Flowable.just(mockedRes)).when(countriesApi).getCountries(Mockito.anyInt(), Mockito.anyInt());
        liveData = countriesViewModel.getCountries(currentPage, 1);
        Assert.assertNotNull(liveData);
        Assert.assertTrue(countriesViewModel.hasFinishedFetching());
    }
}
