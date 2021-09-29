package com.sogefi.position.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.sogefi.position.R;
import com.sogefi.position.utils.PreferenceManager;

import java.util.List;

public class SplashActivity extends AppCompatActivity implements PermissionsListener {
    PreferenceManager pref;
    private PermissionsManager permissionsManager;

    protected static final String TAG = "SplashActivity";

    protected static final int REQUEST_CHECK_SETTINGS = 0x1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        pref = new PreferenceManager(this);

        final Handler handler = new Handler();
        handler.postDelayed(() -> displayLocationSettingsRequest(SplashActivity.this), 2000L);
    }

    @Override
    public void onExplanationNeeded(List<String> list) {

    }

    @Override
    public void onPermissionResult(boolean b) {

    }

    private void displayLocationSettingsRequest(Context context) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(result1 -> {
            final Status status = result1.getStatus();
            switch (status.getStatusCode()) {
                case LocationSettingsStatusCodes.SUCCESS:
                    Log.i(TAG, "All location settings are satisfied.");

                    if (PermissionsManager.areLocationPermissionsGranted(SplashActivity.this)) {
                        if(pref.getLauch().equals("non")) {
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            pref.firstLaunch("non");
                            Intent intent = new Intent(SplashActivity.this, TutorielActivity.class);
                            startActivity(intent);
                        }
                        finish();

                    }
                    else {
                        permissionsManager = new PermissionsManager(SplashActivity.this);
                        permissionsManager.requestLocationPermissions(SplashActivity.this);
                    }




                    break;
                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                    Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                    try {
                        // Show the dialog by calling startResolutionForResult(), and check the result
                        // in onActivityResult().
                        status.startResolutionForResult(SplashActivity.this, REQUEST_CHECK_SETTINGS);

                    } catch (IntentSender.SendIntentException e) {
                        Log.i(TAG, "PendingIntent unable to execute request.");
                        Context context1 = getApplicationContext();
                        CharSequence text = "Erreur lors de l'activation du GPS";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context1, text, duration);
                        toast.show();

                        finish();
                    }
                    break;
                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                    Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                    Context context1 = getApplicationContext();
                    CharSequence text = "activez le GPS manuellement";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context1, text, duration);
                    toast.show();

                    finish();
                    break;
            }
        });
    }
}