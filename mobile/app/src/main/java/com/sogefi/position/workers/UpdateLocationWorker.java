package com.sogefi.position.workers;

import static com.sogefi.position.utils.Constants.API_KEY;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.sogefi.position.R;
import com.sogefi.position.api.APIClient;
import com.sogefi.position.api.ApiInterface;
import com.sogefi.position.database.AppExecutor;
import com.sogefi.position.database.PositionDataBase;
import com.sogefi.position.models.Favorite;
import com.sogefi.position.models.Images;
import com.sogefi.position.models.Tracking;
import com.sogefi.position.models.data.DataTracking;
import com.sogefi.position.repositories.FavoriteRepository;
import com.sogefi.position.repositories.TrackingRepository;
import com.sogefi.position.ui.activities.MapActivity;
import com.sogefi.position.utils.Function;
import com.sogefi.position.utils.PreferenceManager;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class UpdateLocationWorker extends Worker {
    PreferenceManager pref;
 /*   private static final String DEFAULT_START_TIME = "08:00";
    private static final String DEFAULT_END_TIME = "20:30";*/

    private static final String TAG = "UpdateLocationWorker";

    /**
     * The desired interval for location updates. Inexact. Updates may be more or less frequent.
     */
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    /**
     * The fastest rate for active location updates. Updates will never be more frequent
     * than this value.
     */
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    /**
     * The current location.
     */
    private Location mLocation;

    /**
     * Provides access to the Fused Location Provider API.
     */
    private FusedLocationProviderClient mFusedLocationClient;

    private Context mContext;
    /**
     * Callback for changes in location.
     */
    private LocationCallback mLocationCallback;

    TrackingRepository trackingRepository;
    PositionDataBase mDb;
    List<DataTracking> dataTrackings;


    public UpdateLocationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: Done");

        Log.d(TAG, "onStartJob: STARTING JOB..");

        if (Function.isNetworkAvailable(getApplicationContext())) {
            mDb = PositionDataBase.getInstance(getApplicationContext());
            trackingRepository = new TrackingRepository(mDb);
            dataTrackings = new ArrayList<>();
            AppExecutor.getInstance().diskIO().execute(() -> {
                dataTrackings.addAll(mDb.trackingDao().getAll());
            });

            for (int i = 0; i < dataTrackings.size(); i++) {
                sendLocation(Double.parseDouble( dataTrackings.get(i).getLongitude()),Double.parseDouble(dataTrackings.get(i).getLatitude()));
                mDb.trackingDao().delete(dataTrackings.get(i));
            }
        }



        DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        String formattedDate = dateFormat.format(date);

        try{
           /* Date currentDate = dateFormat.parse(formattedDate);
            Date startDate = dateFormat.parse(DEFAULT_START_TIME);
            Date endDate = dateFormat.parse(DEFAULT_END_TIME);*/

          //  if (currentDate.after(startDate) && currentDate.before(endDate)) {
                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext);
                mLocationCallback = new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                    }
                };

                LocationRequest mLocationRequest = new LocationRequest();
                mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
                mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
                mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

                try {
                    mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            if (task.isSuccessful() && task.getResult() != null) {
                                mLocation = task.getResult();
                                Log.d(TAG, "Location : " + mLocation);


                                sendLocation(mLocation.getLatitude(),mLocation.getLongitude());



                                mFusedLocationClient.removeLocationUpdates(mLocationCallback);
                            } else {
                                Log.w(TAG, "Failed to get location.");
                            }
                        }
                    });
                } catch (SecurityException unlikely) {
                    Log.e(TAG, "Lost location permission." + unlikely);
                }

                try{
                    mFusedLocationClient.requestLocationUpdates(mLocationRequest, null);
                } catch (SecurityException unlikely) {
                    //Utils.setRequestingLocationUpdates(this, false);
                    Log.e(TAG, "Lost location permission. Could not request updates. " + unlikely);
                }

         /*  } else {
                Log.d(TAG, "Time up to get location. Your time is : " + DEFAULT_START_TIME + " to " + DEFAULT_END_TIME);
            }*/

        } catch (Exception e) {

        }

        return Result.success();
    }

    private void sendLocation(double LATITUDE, double LONGITUDE) {
        mDb = PositionDataBase.getInstance(getApplicationContext());
        trackingRepository = new TrackingRepository(mDb);
        pref = new PreferenceManager(mContext);

        DataTracking dataTracking = new DataTracking(String.valueOf(LONGITUDE),String.valueOf(LATITUDE));


        if (Function.isNetworkAvailable(getApplicationContext())) {

            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<Tracking> call = apiService.addtracking(API_KEY,"Bearer "+pref.getToken(),dataTracking);
            call.enqueue(new Callback<Tracking>() {
                @Override
                public void onResponse(@NotNull Call<Tracking> call, @NotNull Response<Tracking> response) {
                    if(response.code() == 401 || response.code() == 500) {
                        trackingRepository.onSave(dataTracking);
                      //  Toast.makeText(getApplicationContext(), "Error add tracking", Toast.LENGTH_LONG).show();
                    } else {
                     //   Toast.makeText(getApplicationContext(), "Add Success", Toast.LENGTH_LONG).show();
                    }



                }

                @Override
                public void onFailure(@NotNull Call<Tracking> call, @NotNull Throwable t) {
                    trackingRepository.onSave(dataTracking);
                    // Log error here since request failed
                    Timber.tag("images").e(t.toString());
                    Log.e("error create", t.toString());
                   // Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            trackingRepository.onSave(dataTracking);
          //  Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
        }
    }
}
