package com.sogefi.position.ui.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.services.android.navigation.ui.v5.NavigationView;
import com.mapbox.services.android.navigation.ui.v5.NavigationViewOptions;
import com.mapbox.services.android.navigation.ui.v5.OnNavigationReadyCallback;
import com.mapbox.services.android.navigation.ui.v5.listeners.NavigationListener;
import com.sogefi.position.R;

public class NavigationActivity extends AppCompatActivity implements OnNavigationReadyCallback, NavigationListener {

    DirectionsRoute route;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        String r = getIntent().getStringExtra("ROUTE");

        route = DirectionsRoute.fromJson(r);

        navigationView = findViewById(R.id.navigationView);
        navigationView.onCreate(savedInstanceState);
        navigationView.initialize(this);
    }

    @Override
    public void onNavigationReady(boolean isRunning) {
        NavigationViewOptions options = NavigationViewOptions.builder()
                .navigationListener(this)
                .directionsRoute(route)
                .shouldSimulateRoute(false)
                .build();
        navigationView.startNavigation(options);
    }

    @Override
    public void onCancelNavigation() {
        finish();
    }

    @Override
    public void onNavigationFinished() {
        finish();
    }

    @Override
    public void onNavigationRunning() {

    }

    @Override
    public void onBackPressed() {
        if (!navigationView.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        navigationView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.onResume();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        navigationView.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigationView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        navigationView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        navigationView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        navigationView.onDestroy();
    }


}