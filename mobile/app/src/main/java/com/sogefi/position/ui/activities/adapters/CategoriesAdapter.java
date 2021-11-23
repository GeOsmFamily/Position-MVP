package com.sogefi.position.ui.activities.adapters;

import static com.sogefi.position.utils.Constants.IMAGEURL;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.sogefi.position.R;
import com.sogefi.position.models.Language;
import com.sogefi.position.models.data.DataCategories;
import com.sogefi.position.ui.activities.MapActivity;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {
    private int rowLayout;
    private List<DataCategories> categories;
    private MapActivity mapActivity;


    public CategoriesAdapter(int rowLayout, MapActivity mapActivity, List<DataCategories> categories) {
        this.rowLayout = rowLayout;
        this.categories = categories;
        this.mapActivity = mapActivity;
    }

    @NotNull
    @Override
    public CategoriesAdapter.CategoriesViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new CategoriesAdapter.CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriesAdapter.CategoriesViewHolder holder, final int position) {

        Picasso.get().load(IMAGEURL + categories.get(position).getLogoUrl()).into(holder.ivPhoto);
        holder.tvName.setText(categories.get(position).getNom());
       /* holder.chips.setOnClickListener(view -> mapActivity.onLocaleChanged(languages.get(position).getLocale()));
        if (languages.get(position).getLocale().equals(mapActivity.getResources().getConfiguration().locale.getLanguage())) {
            holder.checked.setVisibility(View.VISIBLE);
        } else {
            holder.checked.setVisibility(View.GONE);
        }*/

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPhoto;
        TextView tvName;
        RelativeLayout chips;


        public CategoriesViewHolder(View v) {
            super(v);
            ivPhoto = v.findViewById(R.id.ivPhoto);
            tvName = v.findViewById(R.id.tvName);
            chips = v.findViewById(R.id.container);
        }
    }
}
