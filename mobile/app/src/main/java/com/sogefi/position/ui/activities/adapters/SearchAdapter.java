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
import com.sogefi.position.models.data.DataEtablissements;
import com.sogefi.position.models.data.DataSearchEtablissement;
import com.sogefi.position.ui.activities.MapActivity;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SearchAdapter extends SuggestionsAdapter<DataSearchEtablissement, SearchAdapter.SearchViewHolder> {
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
    public void onBindSuggestionHolder(DataSearchEtablissement suggestion, SearchViewHolder holder, int position) {

            holder.suggestion.setText(suggestion.getNom());
            holder.suggestionAdress.setText(suggestion.getNomSousCategorie()+","+suggestion.getNomCategorie());
            if(suggestion.getLogoUrl() != null) {
                Picasso.get().load(IMAGEURL + suggestion.getLogoUrl()).into(holder.imageView);
            } else {
                holder.imageView.setImageResource(R.drawable.ic_baseline_location_on_24);
            }

        DataEtablissements dataEtablissements = new DataEtablissements();
            dataEtablissements.setId(suggestion.getId());
            dataEtablissements.setSiteInternet(suggestion.getSiteInternet());
            dataEtablissements.setEtage(suggestion.getEtage());
            dataEtablissements.setCover(suggestion.getCover());
            dataEtablissements.setCodePostal(suggestion.getCodePostal());
            dataEtablissements.setAutres(suggestion.getAutres);
            dataEtablissements.setCreatedAt(suggestion.getCreatedAt());
            dataEtablissements.setDescription(suggestion.getDescription());
            dataEtablissements.setHoraires(suggestion.getHoraires);
            dataEtablissements.setImages(suggestion.getImages);
            dataEtablissements.setIdBatiment(suggestion.getIdBatiment());
            dataEtablissements.setIdCommercial(suggestion.getIdCommercial());
            dataEtablissements.setIdManager(suggestion.getIdManager());
            dataEtablissements.setIndicationAdresse(suggestion.getIndicationAdresse());
            dataEtablissements.setNom(suggestion.getNom());
            dataEtablissements.setNomCommercial(suggestion.getNomCommercial());
            dataEtablissements.setTelephones(suggestion.getTelephones());
            dataEtablissements.setSousCategories(suggestion.getSousCategorie());


        holder.constraintLayout.setOnClickListener(view -> mapActivity.clickDialog(dataEtablissements));



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
