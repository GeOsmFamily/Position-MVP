package com.sogefi.position.ui.activities;


import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.plugins.places.picker.PlacePicker;
import com.mapbox.mapboxsdk.plugins.places.picker.model.PlacePickerOptions;
import com.sogefi.position.R;
import com.sogefi.position.api.APIClient;
import com.sogefi.position.api.ApiInterface;
import com.sogefi.position.models.Nominatim;
import com.sogefi.position.utils.Function;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


public class PicklocationActivity extends AppCompatActivity{
    private static final int REQUEST_CODE = 5678;
    private TextView selectedLocationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

// Mapbox access token is configured here. This needs to be called either in your application
// object or in the same activity which contains the mapview.
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

// This contains the MapView in XML and needs to be called after the access token is configured.
        setContentView(R.layout.activity_place_selection);

        selectedLocationTextView = findViewById(R.id.selected_location_info_textview);
        goToPickerActivity();
    }

    /**
     * Set up the PlacePickerOptions and startActivityForResult
     */
    private void goToPickerActivity() {
        String longitude = getIntent().getStringExtra("longitude");
        String latitude = getIntent().getStringExtra("latitude");


        startActivityForResult(
                new PlacePicker.IntentBuilder()

                        .accessToken(getString(R.string.mapbox_access_token))
                        .placeOptions(PlacePickerOptions.builder()

                                .statingCameraPosition(new CameraPosition.Builder()
                                        .target(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude))).zoom(16).build())
                                .includeDeviceLocationButton(true)
                                .includeReverseGeocode(true)
                                .build())
                        .build(this), REQUEST_CODE);
    }

    /**
     * This fires after a location is selected in the Places Plugin's PlacePickerActivity.
     * @param requestCode code that is a part of the return to this activity
     * @param resultCode code that is a part of the return to this activity
     * @param data the data that is a part of the return to this activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
// Show the button and set the OnClickListener()
            Button goToPickerActivityButton = findViewById(R.id.go_to_picker_button);
            goToPickerActivityButton.setVisibility(View.VISIBLE);
            goToPickerActivityButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToPickerActivity();
                }
            });
        } else if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
// Retrieve the information from the selected location's CarmenFeature
           double longitude = PlacePicker.getLastCameraPosition(data).target.getLongitude();
           double latitude =  PlacePicker.getLastCameraPosition(data).target.getLatitude();
            searchPoint(String.valueOf(longitude),String.valueOf(latitude));
            CarmenFeature carmenFeature = PlacePicker. getPlace(data);
          /*  double longitude = ((Point) carmenFeature.geometry()).longitude();
            double latitude = ((Point) carmenFeature.geometry()).latitude();*/


          /*  Intent intent = new Intent(this, NewBusiness5Activity.class);
            intent.putExtra("adresse",String.valueOf(longitude)+","+String.valueOf(latitude));
            startActivity(intent);
            finish();*/

// Set the TextView text to the entire CarmenFeature. The CarmenFeature
// also be parsed through to grab and display certain information such as
// its placeName, text, or coordinates.
          /*  if (carmenFeature != null) {
                selectedLocationTextView.setText(String.format(
                        "Select", carmenFeature.toJson()));
            }*/
        }
    }

    public void searchPoint(String lon, String lat) {
        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient().create(ApiInterface.class);
            Call<Nominatim> call = apiService.nominatimCoord(lat, lon, 1, "json");
            call.enqueue(new Callback<Nominatim>() {
                @Override
                public void onResponse(@NotNull Call<Nominatim> call, @NotNull Response<Nominatim> response) {
                  //  selectedLocationTextView.setText(response.body().getDisplayName());
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("adresse",String.valueOf(lon)+","+String.valueOf(lat));
                    returnIntent.putExtra("adresseName",response.body().getDisplayName());
                    setResult(202,returnIntent);
                    finish();
                }

                @Override
                public void onFailure(@NotNull Call<Nominatim> call, @NotNull Throwable t) {

                    // Log error here since request failed
                    Timber.tag("main2").e(t.toString());
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
    }
}