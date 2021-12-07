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

import com.sogefi.position.R;
import com.sogefi.position.models.SearchSousCategories;
import com.sogefi.position.models.data.DataCategories;
import com.sogefi.position.models.data.DataSearchSousCategories;
import com.sogefi.position.ui.activities.MapActivity;
import com.sogefi.position.ui.activities.NewBusinessActivity;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SearchSousCategoriesAdapter extends RecyclerView.Adapter<SearchSousCategoriesAdapter.SearchSousCategoriesViewHolder>{
    private int rowLayout;
    private List<DataSearchSousCategories> searchSousCategories;
    private NewBusinessActivity newBusinessActivity;


    public SearchSousCategoriesAdapter(int rowLayout, NewBusinessActivity newBusinessActivity, List<DataSearchSousCategories> searchSousCategories) {
        this.rowLayout = rowLayout;
        this.searchSousCategories = searchSousCategories;
        this.newBusinessActivity = newBusinessActivity;
    }

    @NotNull
    @Override
    public SearchSousCategoriesAdapter.SearchSousCategoriesViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new SearchSousCategoriesAdapter.SearchSousCategoriesViewHolder(view);
    }


    @Override
    public void onBindViewHolder(SearchSousCategoriesAdapter.SearchSousCategoriesViewHolder holder, final int position) {
        if(searchSousCategories.get(position).getCategorie().getLogoUrl() != null) {
            Picasso.get().load(IMAGEURL + searchSousCategories.get(position).getCategorie().getLogoUrl()).into(holder.imageView);
        } else {
            holder.imageView.setImageResource(R.drawable.logo_carre);
        }

        holder.suggestion.setText(searchSousCategories.get(position).getNom());

        holder.spinner_constraint.setOnClickListener(v -> newBusinessActivity.populateTv(searchSousCategories.get(position).getCategorie().getNom(),searchSousCategories.get(position).getNom(),searchSousCategories.get(position).getId()));


    }

    @Override
    public int getItemCount() {
        return searchSousCategories.size();
    }

    public static class SearchSousCategoriesViewHolder extends RecyclerView.ViewHolder {

        TextView suggestion;
        ImageView imageView;
        ConstraintLayout spinner_constraint;


        public SearchSousCategoriesViewHolder(View v) {
            super(v);
            suggestion = v.findViewById(R.id.nom_categorie);
            imageView = v.findViewById(R.id.image_categorie);
            spinner_constraint = v.findViewById(R.id.spinner_constraint);
        }
    }
}
