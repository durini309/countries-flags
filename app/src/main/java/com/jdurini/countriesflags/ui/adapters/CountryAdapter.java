package com.jdurini.countriesflags.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jdurini.countriesflags.R;
import com.jdurini.countriesflags.models.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int FETCHING_ITEM = 0;
    private final static int COUNTRY_ITEM = 1;

    private List<Country> countries = new ArrayList<>();
    private boolean isFetching = false;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == FETCHING_ITEM) {
            return new FetchingViewHolder(
                    inflater.inflate(R.layout.view_fetching, parent, false)
            );
        }

        return new CountryViewHolder(
                inflater.inflate(R.layout.view_country, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Country country = countries.get(position);
        if (getItemViewType(position) == FETCHING_ITEM) {
            FetchingViewHolder fetchingViewHolder = (FetchingViewHolder) holder;
            fetchingViewHolder.progressBar.setVisibility(View.VISIBLE);
        } else {
            CountryViewHolder countryViewHolder = (CountryViewHolder) holder;
            countryViewHolder.countryName.setText(country.getName());

            Glide.with(holder.itemView)
                    .load(country.getFlagUrl())
                    .into(countryViewHolder.flagImage);
        }
    }

    @Override
    public int getItemViewType(int position) {
        // If it's list's last item and we are fetching data, we show progressbar
        return (position == countries.size() - 1 && isFetching) ? FETCHING_ITEM : COUNTRY_ITEM;
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public void addCountries(List<Country> newCountries) {
        for (Country country : newCountries) {
            countries.add(country);
            notifyItemInserted(countries.size() - 1);
        }
    }

    public void showFetching() {
        isFetching = true;
        countries.add(new Country());
        notifyItemInserted(countries.size() - 1);
    }

    public void removeFetching() {
        if (isFetching) {
            isFetching = false;
            int fetchingItemPos = countries.size() - 1;
            countries.remove(fetchingItemPos);
            notifyItemRemoved(fetchingItemPos);
        }
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        private ImageView flagImage;
        private TextView countryName;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);

            flagImage = itemView.findViewById(R.id.image_flag);
            countryName = itemView.findViewById(R.id.txt_country_name);
        }
    }

    class FetchingViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;

        public FetchingViewHolder(@NonNull View itemView) {
            super(itemView);

            progressBar = itemView.findViewById(R.id.progressbar_fetching);
        }
    }
}
