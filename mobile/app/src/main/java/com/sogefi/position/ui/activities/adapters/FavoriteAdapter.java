package com.sogefi.position.ui.activities.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.sogefi.position.R;
import com.sogefi.position.models.Favorite;
import com.sogefi.position.ui.activities.FavoriteActivity;

import org.jetbrains.annotations.NotNull;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import java.util.List;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendDayOfMonth(1)
            .appendLiteral(" ")
            .appendMonthOfYearText()
            .appendLiteral(" ")
            .appendYear(4, 4)
            .toFormatter();
    private int rowLayout;
    private List<Favorite> favorites;
    private FavoriteActivity favoriteActivity;

    public FavoriteAdapter(int rowLayout, FavoriteActivity favoriteActivity, List<Favorite> favorites) {
        this.rowLayout = rowLayout;
        this.favoriteActivity = favoriteActivity;
        this.favorites = favorites;
    }

    @NotNull
    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.value.setText(favorites.get(position).getDisplayName());
        holder.name.setText(favorites.get(position).getSavedName());
        holder.date.setText(formatter.print(favorites.get(position).getDate()));
        holder.edit.setOnClickListener(view -> favoriteActivity.onEdit(favorites.get(position), position));

        holder.share.setOnClickListener(view -> favoriteActivity.onShare(favorites.get(position)));

        holder.delete.setOnClickListener(view -> favoriteActivity.onDelete(favorites.get(position), position));

        holder.show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favoriteActivity.viewFavorite(favorites.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView value;
        TextView date;
        ImageView share;
        ImageView edit;
        MaterialButton delete;
        MaterialButton show;


        public FavoriteViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            value = v.findViewById(R.id.value);
            date = v.findViewById(R.id.date);
            share = v.findViewById(R.id.share);
            edit = v.findViewById(R.id.edit);
            delete = v.findViewById(R.id.delete);
            show = v.findViewById(R.id.showOnMap);
        }
    }
}
