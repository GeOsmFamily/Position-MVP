package com.sogefi.position.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;
import com.sogefi.position.R;
import com.sogefi.position.ui.activities.adapters.TutorielAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TutorielActivity extends AppCompatActivity {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tutoriel) RecyclerView tutoriel;

    IndefinitePagerIndicator indefinitePagerIndicator;
    PagerSnapHelper pagerSnapHelper;
    View.OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoriel);

        ButterKnife.bind(this);



        onClickListener = v -> {
            Intent intent = new Intent(TutorielActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        };

        pagerSnapHelper = new PagerSnapHelper();
        indefinitePagerIndicator = new IndefinitePagerIndicator(this);
        pagerSnapHelper.attachToRecyclerView(tutoriel);
        indefinitePagerIndicator.attachToRecyclerView(tutoriel);
        tutoriel.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        tutoriel.setAdapter(new TutorielAdapter(onClickListener));


    }
}