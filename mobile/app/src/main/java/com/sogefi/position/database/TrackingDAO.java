package com.sogefi.position.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sogefi.position.models.data.DataTracking;

import java.util.List;

@Dao
public interface TrackingDAO {

    @Query("SELECT * FROM TRACKING")
    List<DataTracking> getAll();

    @Insert
    void insert(DataTracking dataTracking);

    @Update
    void update(DataTracking dataTracking);

    @Delete
    void delete(DataTracking dataTracking);
}
