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
import com.sogefi.position.models.data.DataEtablissements;
import com.sogefi.position.ui.activities.MapActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EtablissementAdapter extends RecyclerView.Adapter<EtablissementAdapter.EtablissementsViewHolder> {
    private int rowLayout;
    private List<DataEtablissements> etablissements;
    private MapActivity mapActivity;


    public EtablissementAdapter(int rowLayout, MapActivity mapActivity, List<DataEtablissements> etablissements) {
        this.rowLayout = rowLayout;
        this.etablissements = etablissements;
        this.mapActivity = mapActivity;
    }

    @NotNull
    @Override
    public EtablissementAdapter.EtablissementsViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new EtablissementAdapter.EtablissementsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EtablissementAdapter.EtablissementsViewHolder holder, final int position) {
        holder.etablissement_image.setImageResource(R.drawable.logo_carre);
        holder.etablissement_name.setText(etablissements.get(position).getNom());
        holder.etablissement_etage.setText("etage " + etablissements.get(position).getEtage());
        //holder.card.setOnClickListener(view -> mapActivity.onLocaleChanged(languages.get(position).getLocale()));
     /*   if (languages.get(position).getLocale().equals(mapActivity.getResources().getConfiguration().locale.getLanguage())) {
            holder.checked.setVisibility(View.VISIBLE);
        } else {
            holder.checked.setVisibility(View.GONE);
        }*/

    }

    @Override
    public int getItemCount() {
        return etablissements.size();
    }

    public static class EtablissementsViewHolder extends RecyclerView.ViewHolder {
        ImageView etablissement_image;
        TextView etablissement_name;
        TextView etablissement_etage;
        ConstraintLayout card_etablissement;


        public EtablissementsViewHolder(View v) {
            super(v);
            etablissement_image = v.findViewById(R.id.etablissement_image);
             etablissement_name= v.findViewById(R.id.etablissement_name);
            etablissement_etage= v.findViewById(R.id.etablissement_etage);
            card_etablissement = v.findViewById(R.id.card_etablissement);
        }
    }
}
