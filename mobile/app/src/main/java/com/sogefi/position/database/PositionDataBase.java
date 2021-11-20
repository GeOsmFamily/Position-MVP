package com.sogefi.position.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.sogefi.position.db.DoubleListTypeConverter;
import com.sogefi.position.models.Favorite;
import com.sogefi.position.models.data.DataTracking;

import timber.log.Timber;

@Database(entities = {Favorite.class, DataTracking.class}, version = 1, exportSchema = false)

@TypeConverters(
        DoubleListTypeConverter.class
)
public abstract class PositionDataBase extends RoomDatabase {

    private static final String LOG_TAG = PositionDataBase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "position";
    private static PositionDataBase sInstance;

    public static PositionDataBase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Timber.tag(LOG_TAG).d("Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        PositionDataBase.class, PositionDataBase.DATABASE_NAME)
                        .build();
            }
        }
        Timber.tag(LOG_TAG).d("Getting the database instance");
        return sInstance;
    }

    public abstract FavoriteDAO favoriteDao();
    public abstract TrackingDAO trackingDao();
}
