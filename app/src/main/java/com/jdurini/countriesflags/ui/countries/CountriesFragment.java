package com.jdurini.countriesflags.ui.countries;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jdurini.countriesflags.R;
import com.jdurini.countriesflags.ui.adapters.CountriesScrollListener;
import com.jdurini.countriesflags.ui.adapters.CountryAdapter;
import com.jdurini.countriesflags.viewmodels.CountriesViewModel;
import com.jdurini.countriesflags.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class CountriesFragment extends DaggerFragment {

    // If we want longer/shorter responses, we should change this constant
    private final static int PAGE_SIZE = 20;

    private CountriesViewModel viewModel;
    private RecyclerView recyclerCountries;
    private ConstraintLayout layoutLoading;
    private boolean isFetching = false;
    private boolean finishedFetching = true;
    private int currentPage = 1;

    @Inject
    CountryAdapter adapter;

    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    public CountriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_countries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerCountries = view.findViewById(R.id.recycler_countries);
        layoutLoading = view.findViewById(R.id.layout_loading);
        viewModel = new ViewModelProvider(this, viewModelProviderFactory).get(CountriesViewModel.class);
        initRecyclerview();
    }

    private void initRecyclerview() {
        recyclerCountries.setLayoutManager(layoutManager);
        recyclerCountries.setAdapter(adapter);
        recyclerCountries.addOnScrollListener(new CountriesScrollListener(layoutManager) {
            @Override
            public boolean isFetching() {
                return isFetching;
            }

            @Override
            public boolean finishedPagination() {
                return finishedFetching;
            }

            @Override
            protected void loadMoreItems() {
                isFetching = true;
                getCountries(false);
            }
        });
        getCountries(true);
    }

    private void getCountries(boolean firstFetch) {
        viewModel.getCountries(currentPage, PAGE_SIZE).observe(getViewLifecycleOwner(), countries -> {
            if (countries != null) {
                if (firstFetch) {
                    layoutLoading.setVisibility(View.GONE);
                    recyclerCountries.setVisibility(View.VISIBLE);
                } else {
                    isFetching = false;
                    adapter.removeFetching();
                }
                adapter.addCountries(countries);
                finishedFetching = viewModel.hasFinishedFetching();

                // If pages are still remaining, we will show the loading item at the end
                if (!finishedFetching) {
                    adapter.showFetching();
                }

                currentPage++;
            } else {
                Toast.makeText(
                        getContext(),
                        "Unexpected error",
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}