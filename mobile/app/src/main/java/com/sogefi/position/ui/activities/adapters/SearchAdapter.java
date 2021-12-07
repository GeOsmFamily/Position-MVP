package com.sogefi.position.ui.activities.adapters;

import static com.sogefi.position.utils.Constants.IMAGEURL;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;
import com.sogefi.position.R;
import com.sogefi.position.models.Search;
import com.sogefi.position.ui.activities.MapActivity;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SearchAdapter extends SuggestionsAdapter<Search, SearchAdapter.SearchViewHolder> {
    MapActivity mapActivity;
    public SearchAdapter(LayoutInflater inflater,MapActivity mapActivity) {
        super(inflater);
        this.mapActivity = mapActivity;
    }



   /* public SearchAdapter(int rowLayout, MapActivity mapActivity, List<Search> search) {
        this.rowLayout = rowLayout;
        this.search = search;
        this.mapActivity = mapActivity;
    }*/

    @NotNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suggestion, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

  /*  @Override
    public void onBindViewHolder(SearchAdapter.SearchViewHolder holder, final int position) {

        if(search.get(position).getType().equals("nominatim")) {

            String[] parts = search.get(position).getNom().split(",");
            holder.suggestion.setText(parts[0]);
            holder.suggestionAdress.setText(search.get(position).getDetails());
            holder.imageView.setImageResource(R.drawable.ic_baseline_location_on_24);
        } else {
            holder.suggestion.setText(search.get(position).getNom());
            holder.suggestionAdress.setText(search.get(position).getDetails());
            holder.imageView.setImageResource(R.drawable.ic_baseline_location_on_24);
        }

    }*/

    @Override
    public void onBindSuggestionHolder(Search suggestion, SearchViewHolder holder, int position) {
        if(suggestion.getType().equals("nominatim")) {

            String[] parts = suggestion.getNom().split(",");
            holder.suggestion.setText(parts[0]);
            holder.suggestionAdress.setText(suggestion.getDetails());
            if(suggestion.getLogoUrl() != null) {
                Picasso.get().load(suggestion.getLogoUrl()).into(holder.imageView);
            } else {
                holder.imageView.setImageResource(R.drawable.ic_baseline_location_on_24);
            }

            holder.constraintLayout.setOnClickListener(view -> mapActivity.resultSearch(suggestion.getLongitude(), suggestion.getLatitude(), suggestion.getType()));

        } else {
            holder.suggestion.setText(suggestion.getNom());
            holder.suggestionAdress.setText(suggestion.getDetails());
            if(suggestion.getLogoUrl() != null) {
                Picasso.get().load(IMAGEURL + suggestion.getLogoUrl()).into(holder.imageView);
            } else {
                holder.imageView.setImageResource(R.drawable.ic_baseline_location_on_24);
            }
        }


    }

    @Override
    public int getSingleViewHeight() {
        return 60;
    }

  /*  @Override
    public int getItemCount() {
        return search.size();
    }*/

    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView suggestion;
        ImageView imageView;
        TextView suggestionAdress;
        ConstraintLayout constraintLayout;


        public SearchViewHolder(View v) {
            super(v);
            suggestion = v.findViewById(R.id.suggestion);
            imageView = v.findViewById(R.id.iconImage);
            suggestionAdress = v.findViewById(R.id.suggestionAdress);
            constraintLayout = v.findViewById(R.id.itemSuggestion);
        }
    }
}
