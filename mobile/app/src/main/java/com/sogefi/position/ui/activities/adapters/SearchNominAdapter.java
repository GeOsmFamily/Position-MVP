package com.sogefi.position.ui.activities.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sogefi.position.R;
import com.sogefi.position.models.Nominatim;
import com.sogefi.position.ui.activities.SearchActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SearchNominAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    static SearchActivity searchActivity;
    final int VIEW_TYPE_POSITION = 0;
    final int VIEW_TYPE_NOMINATIM = 1;
    private List<Nominatim> suggestions;
    private int rowLayout;

    public SearchNominAdapter(int rowLayout, List<Nominatim> suggestions, SearchActivity searchActivity) {
        this.rowLayout = rowLayout;
        this.suggestions = suggestions;
        SearchNominAdapter.searchActivity = searchActivity;
    }

    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);

        if (viewType == VIEW_TYPE_NOMINATIM) {
            return new NominatimViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof NominatimViewHolder) {
            ((NominatimViewHolder) holder).populate(suggestions.get(position));

        }


    }

    @Override
    public int getItemViewType(int position) {

        if (suggestions.size() != 0) {
            return VIEW_TYPE_NOMINATIM;
        }

        return -1;
    }

    @Override
    public int getItemCount() {
        return suggestions.size();
    }


    public static class NominatimViewHolder extends RecyclerView.ViewHolder {
        TextView suggestion;
        ImageView imageView;
        TextView suggestionAdress;
        ConstraintLayout constraintLayout;


        public NominatimViewHolder(View v) {
            super(v);
            suggestion = v.findViewById(R.id.suggestion);
            imageView = v.findViewById(R.id.iconImage);
            suggestionAdress = v.findViewById(R.id.suggestionAdress);
            constraintLayout = v.findViewById(R.id.itemSuggestion);
        }

        public void populate(Nominatim nominatim) {
            String[] parts = nominatim.getDisplayName().split(",");
            suggestion.setText(parts[0]);

            if (nominatim.getAddress().getCity() == null) {
                suggestionAdress.setText(nominatim.getAddress().getCounty() + " , " + nominatim.getAddress().getState());
            } else {
                suggestionAdress.setText(nominatim.getAddress().getCity());
            }


            if (nominatim.getIcon() == null) {
                imageView.setImageResource(R.drawable.ic_baseline_location_on_24);
            } else {
                Glide.with(searchActivity).load(nominatim.getIcon()).into(imageView);
            }

            constraintLayout.setOnClickListener(view -> searchActivity.onSuggestionClick(nominatim.getLon(), nominatim.getLat(), parts[0]));
        }
    }
}

