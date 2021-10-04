package com.sogefi.position.ui.activities.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.sogefi.position.R;
import com.sogefi.position.ui.activities.TutorielActivity;

import org.jetbrains.annotations.NotNull;

public class TutorielAdapter extends RecyclerView.Adapter<TutorielAdapter.TutorielViewHolder> {
    private int rowLayout;
    private TutorielActivity tutorielActivity;

    private int[] pictures = {R.drawable.tuto1,
            R.drawable.tuto2,
            R.drawable.tuto3,
            R.drawable.tuto4};

    private int[] icons = {R.drawable.ic_tuto1_icon,
            R.drawable.ic_tuto2_icon,
            R.drawable.ic_tuto3_icon,
            R.drawable.ic_tuto4_icon};

    private int[] labels = {R.string.label_tuto1,
            R.string.label_tuto2,
            R.string.label_tuto3,
            R.string.label_tuto4};

    public TutorielAdapter(int rowLayout, TutorielActivity tutorielActivity) {
        this.rowLayout = rowLayout;
        this.tutorielActivity = tutorielActivity;
    }

    @NotNull
    @Override
    public TutorielViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new TutorielViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TutorielViewHolder holder, final int position) {
        holder.picture.setImageResource(pictures[position]);
        holder.icon.setImageResource(icons[position]);
        holder.label.setText(labels[position]);
        if (position == pictures.length - 1) {
            holder.start.setVisibility(View.VISIBLE);
            holder.start.setOnClickListener(v -> tutorielActivity.clickItem());
        } else {
            holder.start.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return pictures.length;
    }

    public static class TutorielViewHolder extends RecyclerView.ViewHolder {
        ImageView picture;
        ImageView icon;
        TextView label;
        MaterialButton start;


        public TutorielViewHolder(View v) {
            super(v);
            picture = v.findViewById(R.id.picture);
            icon = v.findViewById(R.id.icon);
            label = v.findViewById(R.id.label);
            start = v.findViewById(R.id.start);
        }
    }
}
