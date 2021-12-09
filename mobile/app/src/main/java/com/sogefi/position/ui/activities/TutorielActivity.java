package com.sogefi.position.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;
import com.sogefi.position.R;
import com.sogefi.position.ui.activities.adapters.TutorielAdapter;
import com.sogefi.position.utils.PreferenceManager;

public class TutorielActivity extends AppCompatActivity {
    private static final String DEEPLINK_QUERY_FRIEND_POSITION = "friend_position";
    PreferenceManager pref;
    String first;
    RecyclerView tutorielRecycler;
    PagerSnapHelper pagerSnapHelper;
    TutorielAdapter tutorielAdapter;
    IndefinitePagerIndicator indicator;
    String friendPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        friendPosition = getIntent().getStringExtra(DEEPLINK_QUERY_FRIEND_POSITION);


        pref = new PreferenceManager(this);
        first = pref.getConnect();
        setContentView(R.layout.activity_tutoriel);
        indicator = findViewById(R.id.indicator);
        tutorielRecycler = findViewById(R.id.tutoriel);
        pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(tutorielRecycler);
        indicator.attachToRecyclerView(tutorielRecycler);
        tutorielRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        tutorielAdapter = new TutorielAdapter(R.layout.item_tutoriel, this);
        tutorielRecycler.setAdapter(tutorielAdapter);

    }

    public void clickItem() {
        Intent intent = new Intent(TutorielActivity.this, MapActivity.class);
        intent.putExtra(DEEPLINK_QUERY_FRIEND_POSITION, friendPosition);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (first.matches("no")) {
            pref.firstConnect("yes");
        } else if(pref.getToken().equals("token")) {
            Intent intent = new Intent(TutorielActivity.this, LoginActivity.class);
            intent.putExtra(DEEPLINK_QUERY_FRIEND_POSITION, friendPosition);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(TutorielActivity.this, MapActivity.class);
            intent.putExtra(DEEPLINK_QUERY_FRIEND_POSITION, friendPosition);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}