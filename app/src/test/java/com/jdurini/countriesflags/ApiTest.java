package com.jdurini.countriesflags;

import com.jdurini.countriesflags.models.CountriesResponse;
import com.jdurini.countriesflags.models.Country;
import com.jdurini.countriesflags.network.CountriesApi;
import com.jdurini.countriesflags.viewmodels.CountriesViewModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;

@RunWith(MockitoJUnitRunner.class)
public class ApiTest {

    @Mock
    CountriesApi countriesApi;

    // Tests that the API is returning the correct amount of countries.
    // If we send "2" as page size, we expect the API to return 2 OR LESS items.
    @Test
    public void test_apiReturnsCorrectAmountOfItems() {
        List<Country> mockedCountries = new ArrayList<Country>(){
            { add(new Country()); }
            { add(new Country()); }
        };
        int pageSize = 2;
        CountriesResponse mockedRes = new CountriesResponse(10, mockedCountries);
        TestSubscriber<CountriesResponse> testSubscriber = new TestSubscriber<>();
        Mockito.doReturn(Flowable.just(mockedRes)).when(countriesApi).getCountries(Mockito.anyInt(), Mockito.anyInt());
        countriesApi.getCountries(Mockito.anyInt(), Mockito.eq(pageSize)).subscribe(testSubscriber);
        testSubscriber.assertNoErrors().assertValue(res ->
                // It could be less int the case where there were less items than required
                res.getCountries().size() <= pageSize
        );
    }
}
