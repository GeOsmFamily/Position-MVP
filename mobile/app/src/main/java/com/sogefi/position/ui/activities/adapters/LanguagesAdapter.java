package com.sogefi.position.ui.activities.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.sogefi.position.R;
import com.sogefi.position.models.Language;
import com.sogefi.position.ui.activities.MapActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.LanguagesViewHolder> {
    private int rowLayout;
    private List<Language> languages;
    private MapActivity mapActivity;


    public LanguagesAdapter(int rowLayout, MapActivity mapActivity, List<Language> languages) {
        this.rowLayout = rowLayout;
        this.languages = languages;
        this.mapActivity = mapActivity;
    }

    @NotNull
    @Override
    public LanguagesAdapter.LanguagesViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new LanguagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LanguagesViewHolder holder, final int position) {
        holder.picture.setImageResource(languages.get(position).getPicture());
        holder.label.setText(languages.get(position).getName());
        holder.card.setOnClickListener(view -> mapActivity.onLocaleChanged(languages.get(position).getLocale()));
        if (languages.get(position).getLocale().equals(mapActivity.getResources().getConfiguration().locale.getLanguage())) {
            holder.checked.setVisibility(View.VISIBLE);
        } else {
            holder.checked.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return languages.size();
    }

    public static class LanguagesViewHolder extends RecyclerView.ViewHolder {
        ImageView picture;
        ImageView checked;
        TextView label;
        ConstraintLayout card;


        public LanguagesViewHolder(View v) {
            super(v);
            picture = v.findViewById(R.id.picture);
            checked = v.findViewById(R.id.checked);
            label = v.findViewById(R.id.label);
            card = v.findViewById(R.id.card);
        }
    }
}
