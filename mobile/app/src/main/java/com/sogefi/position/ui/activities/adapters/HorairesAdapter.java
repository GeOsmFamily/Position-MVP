package com.sogefi.position.ui.activities.adapters;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;
import static com.sogefi.position.utils.Constants.IMAGEURL;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.sogefi.position.R;
import com.sogefi.position.models.Horaires;
import com.sogefi.position.models.data.DataEtablissements;
import com.sogefi.position.models.data.DataHoraires;
import com.sogefi.position.ui.activities.DetailsBusinessActivity;
import com.sogefi.position.ui.activities.MapActivity;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HorairesAdapter extends RecyclerView.Adapter<HorairesAdapter.HorairesViewHolder> {
    private int rowLayout;
    private List<DataHoraires> horaires;
    private DetailsBusinessActivity detailsBusinessActivity;


    public HorairesAdapter(int rowLayout, DetailsBusinessActivity detailsBusinessActivity, List<DataHoraires> horaires) {
        this.rowLayout = rowLayout;
        this.horaires = horaires;
        this.detailsBusinessActivity = detailsBusinessActivity;
    }

    @NotNull
    @Override
    public HorairesAdapter.HorairesViewHolder onCreateViewHolder(ViewGroup parent,
                                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new HorairesAdapter.HorairesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HorairesAdapter.HorairesViewHolder holder, final int position) {

            holder.tvJours.setText(horaires.get(position).getJour());
            holder.heureOuvertureJour.setText(horaires.get(position).getHeureOuverture()+"-");
            holder.heureFermetureJour.setText(horaires.get(position).getHeureFermeture());
        }

    @Override
    public int getItemCount() {
        return horaires.size();
    }


    public static class HorairesViewHolder extends RecyclerView.ViewHolder {
        TextView tvJours;
        TextView heureOuvertureJour;
        TextView heureFermetureJour;


        public HorairesViewHolder(View v) {
            super(v);
            tvJours = v.findViewById(R.id.tvJours);
            heureFermetureJour= v.findViewById(R.id.heureFermetureJour);
            heureOuvertureJour= v.findViewById(R.id.heureOuvertureJour);
        }
    }




}
