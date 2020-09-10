package com.jdurini.countriesflags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

public class HomeFragment extends Fragment {

    MaterialButton btnHome;

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnHome = view.findViewById(R.id.btn_home);
        setListeners();
        return view;
    }

    private void setListeners() {
        btnHome.setOnClickListener(view ->
                Navigation.findNavController(view)
                .navigate(R.id.action_homeFragment_to_flagsFragment)
        );
    }
}