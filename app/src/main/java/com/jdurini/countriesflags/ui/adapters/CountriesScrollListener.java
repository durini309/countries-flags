package com.jdurini.countriesflags.ui.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class CountriesScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager layoutManager;

    public CountriesScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItems = layoutManager.getChildCount();
        int firstVisiblePos = layoutManager.findFirstVisibleItemPosition();
        int totalItems = layoutManager.getItemCount();

        if (!isFetching() && !finishedPagination()) {
            // We know we need more items to fetch if we are looking the last items from the list
            boolean needsMoreItems = (visibleItems + firstVisiblePos) >= totalItems;
            if (needsMoreItems && firstVisiblePos >= 0) {
                loadMoreItems();
            }
        }
    }

    public abstract boolean isFetching();
    public abstract boolean finishedPagination();
    protected abstract void loadMoreItems();
}
