package com.sogefi.position.repositories;

import com.sogefi.position.database.AppExecutor;
import com.sogefi.position.database.PositionDataBase;
import com.sogefi.position.models.data.DataTracking;

import org.joda.time.DateTime;

public class TrackingRepository {
    PositionDataBase mDb;

    public TrackingRepository(PositionDataBase mDb) {
        this.mDb = mDb;
    }

    public void onSave(DataTracking dataTracking) {
        AppExecutor.getInstance().diskIO().execute(() -> {
            dataTracking.setCreatedAt(DateTime.now().toString());
            mDb.trackingDao().insert(dataTracking);

        });
    }
}
