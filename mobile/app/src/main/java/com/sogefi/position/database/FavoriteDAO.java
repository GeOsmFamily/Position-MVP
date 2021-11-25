package com.sogefi.position.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sogefi.position.models.Favorite;

import java.util.List;

@Dao
public interface FavoriteDAO {

    @Query("SELECT * FROM FAVORITES")
    List<Favorite> getAll();

    @Insert
    void insert(Favorite favorite);

    @Update
    void update(Favorite favorite);

    @Delete
    void delete(Favorite favorite);
}
