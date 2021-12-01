package com.sogefi.position.ui.activities.adapters;

import static com.sogefi.position.utils.Constants.IMAGEURL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sogefi.position.R;
import com.sogefi.position.models.data.DataCategories;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    Context context;
    private List<DataCategories> categories;
    LayoutInflater inflter;

    public SpinnerAdapter(Context applicationContext, List<DataCategories> categories) {
        this.context = applicationContext;
        this.categories = categories;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.item_spinner, null);
        ImageView image_categorie = convertView.findViewById(R.id.image_categorie);
        TextView nom_categorie =  convertView.findViewById(R.id.nom_categorie);
        if(categories.get(position).getLogoUrl() != null) {
            Picasso.get().load(IMAGEURL + categories.get(position).getLogoUrl()).into(image_categorie);
        } else {
            image_categorie.setImageResource(R.drawable.logo_carre);
        }

        nom_categorie.setText(categories.get(position).getNom());
        return convertView;
    }

}
