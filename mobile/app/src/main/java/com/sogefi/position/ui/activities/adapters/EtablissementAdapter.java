package com.sogefi.position.ui.activities.adapters;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;
import static com.sogefi.position.utils.Constants.IMAGEURL;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.sogefi.position.R;
import com.sogefi.position.models.Language;
import com.sogefi.position.models.data.DataEtablissements;
import com.sogefi.position.ui.activities.MapActivity;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        try {
            Picasso.get().load(IMAGEURL+etablissements.get(position).getSousCategories().get(0).getLogoUrl()).into(holder.etablissement_image);

            holder.etablissement_name.setText(etablissements.get(position).getNom());
            if(etablissements.get(position).getEtage() == 0) {
                holder.etablissement_etage.setText("Rez de Chaussée");
            } else {
                holder.etablissement_etage.setText("etage " + etablissements.get(position).getEtage());
            }
            holder.etablissement_categorie.setText(etablissements.get(position).getSousCategories().get(0).getCategorie().getNom() + "," + etablissements.get(position).getSousCategories().get(0).getNom());
            String[] parts = etablissements.get(position).getCreatedAt().split("T");
            holder.etablissement_create.setText("Crée le "+parts[0]+" par "+etablissements.get(position).getNomCommercial());
            holder.card_etablissement.setOnClickListener(v -> mapActivity.clickDialog(etablissements.get(position)));
            holder.openFiche.setOnClickListener(v -> mapActivity.openFiche(etablissements.get(position)));
            holder.deleteFiche.setOnClickListener(v -> mapActivity.deleteFiche(etablissements.get(position)));
        } catch (IndexOutOfBoundsException e) {
            Toast.makeText(getApplicationContext(), "Ce batiment n'est pas complet", Toast.LENGTH_LONG).show();
        }




    }

    @Override
    public int getItemCount() {
        return etablissements.size();
    }

    public static class EtablissementsViewHolder extends RecyclerView.ViewHolder {
        ImageView etablissement_image;
        TextView etablissement_name;
        TextView etablissement_etage;
        TextView etablissement_categorie;
        TextView etablissement_create;
        Button openFiche;
        Button deleteFiche;
        ConstraintLayout card_etablissement;


        public EtablissementsViewHolder(View v) {
            super(v);
            etablissement_image = v.findViewById(R.id.etablissement_image);
             etablissement_name= v.findViewById(R.id.etablissement_name);
            etablissement_etage= v.findViewById(R.id.etablissement_etage);
            etablissement_categorie= v.findViewById(R.id.etablissement_categorie);
            etablissement_create= v.findViewById(R.id.etablissement_create);
            card_etablissement = v.findViewById(R.id.card_etablissement);
            openFiche = v.findViewById(R.id.open_fiche);
            deleteFiche = v.findViewById(R.id.delete_fiche);
        }
    }
}
