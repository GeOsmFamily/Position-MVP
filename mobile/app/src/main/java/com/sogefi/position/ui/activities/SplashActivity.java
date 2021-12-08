package com.sogefi.position.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.sogefi.position.R;
import com.sogefi.position.utils.PreferenceManager;

import java.util.List;

import timber.log.Timber;

public class SplashActivity extends AppCompatActivity implements PermissionsListener {
    protected static final String TAG = "SplashActivity";
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    private static final String DEEPLINK_QUERY_FRIEND_POSITION = "friend_position";
    String friendPosition;
    private PermissionsManager permissionsManager;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        getSharedPosition();

        final Handler handler = new Handler();
        handler.postDelayed(() -> displayLocationSettingsRequest(SplashActivity.this), 1000L);
    }

    //Methode pour verifier si les permissions de localisation sont autorisées
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
                    Timber.i("All location settings are satisfied.");

                    if (PermissionsManager.areLocationPermissionsGranted(SplashActivity.this)) {
                        Intent intent = new Intent(SplashActivity.this, TutorielActivity.class);
                        intent.putExtra(DEEPLINK_QUERY_FRIEND_POSITION, friendPosition);
                        startActivity(intent);
                        finish();
                    } else {
                        permissionsManager = new PermissionsManager(SplashActivity.this);
                        permissionsManager.requestLocationPermissions(SplashActivity.this);
                    }


                    break;
                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                    Timber.i("Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                    try {
                        // Show the dialog by calling startResolutionForResult(), and check the result
                        // in onActivityResult().
                        status.startResolutionForResult(SplashActivity.this, REQUEST_CHECK_SETTINGS);

                    } catch (IntentSender.SendIntentException e) {
                        Timber.i("PendingIntent unable to execute request.");
                        Context context1 = getApplicationContext();
                        CharSequence text = "Erreur lors de l'activation du GPS";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context1, text, duration);
                        toast.show();

                        finish();
                    }
                    break;
                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                    Timber.i("Location settings are inadequate, and cannot be fixed here. Dialog not created.");
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check for the integer request code originally supplied to startResolutionForResult().
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    Timber.tag(TAG).i("User agreed to make required location settings changes.");

                    if (PermissionsManager.areLocationPermissionsGranted(SplashActivity.this)) {
                        Intent intent = new Intent(SplashActivity.this, TutorielActivity.class);
                        intent.putExtra(DEEPLINK_QUERY_FRIEND_POSITION, friendPosition);
                        startActivity(intent);
                        finish();
                    } else {
                        permissionsManager = new PermissionsManager(SplashActivity.this);
                        permissionsManager.requestLocationPermissions(SplashActivity.this);
                    }

                    break;
                case Activity.RESULT_CANCELED:
                    Timber.i("User chose not to make required location settings changes.");

                    Context context = getApplicationContext();
                    CharSequence text = "activez le GPS pour continuer";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    finish();
                    break;
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, "Demande de permission", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            Intent intent = new Intent(SplashActivity.this, TutorielActivity.class);
            intent.putExtra(DEEPLINK_QUERY_FRIEND_POSITION, friendPosition);
            startActivity(intent);
        } else Toast.makeText(this, "Permission non accordée", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) { // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Accordée", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Permission non accordée", Toast.LENGTH_LONG).show();

            }
        }
    }

    //Methode pour recuperer les paramètres passés en URL
    public void getSharedPosition() {
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, pendingDynamicLinkData -> {
                    Uri deepLink;
                    if (pendingDynamicLinkData != null) {
                        deepLink = pendingDynamicLinkData.getLink();
                        friendPosition = deepLink != null ? deepLink.getQueryParameter(DEEPLINK_QUERY_FRIEND_POSITION) : null;
                        // Toast.makeText(getApplicationContext(),friendPosition, Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(this, e -> {
                    Timber.tag(TAG).w(e, "getDynamicLink:onFailure");
                    //  Toast.makeText(getApplicationContext(),"Null data", Toast.LENGTH_LONG).show();
                });


    }
}