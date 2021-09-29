package com.sogefi.position.ui.activities.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.sogefi.position.R;

public class TutorielAdapter extends RecyclerView.Adapter<TutorielAdapter.TutorielViewHolder> {
    private final View.OnClickListener onClickListener;

    private final int[] pictures = {
            R.drawable.tuto1,
            R.drawable.tuto2,
            R.drawable.tuto3,
            R.drawable.tuto4};

    private final int[] icons= {
            R.drawable.ic_tuto1_icon,
            R.drawable.ic_tuto2_icon,
            R.drawable.ic_tuto3_icon,
            R.drawable.ic_tuto4_icon};

    private final int[] labels = {
            R.string.label_tuto1,
            R.string.label_tuto2,
            R.string.label_tuto3,
            R.string.label_tuto4};

    public TutorielAdapter(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    @NonNull
    @Override
    public TutorielViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tutoriel, parent, false);
        return new TutorielViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TutorielViewHolder holder, final int position) {

        holder.picture.setImageResource(pictures[position]);
        holder.label.setText(labels[position]);
        holder.icon.setImageResource(icons[position]);


        if(position == pictures.length-1){
            holder.start.setVisibility(View.VISIBLE);
        }

        holder.start.setOnClickListener(onClickListener);

    }

    @Override
    public int getItemCount() {
        return pictures.length;
    }

    public static class TutorielViewHolder extends RecyclerView.ViewHolder {
        ImageView picture;
        MaterialButton start;
        TextView label;
        ImageView icon;


        public TutorielViewHolder(View v) {
            super(v);
            picture = v.findViewById(R.id.picture);
            label = v.findViewById(R.id.label);
            start = v.findViewById(R.id.start);
            icon = v.findViewById(R.id.icon);
        }
    }

}

