package com.sogefi.position.repositories;



import com.sogefi.position.database.AppExecutor;
import com.sogefi.position.database.PositionDataBase;
import com.sogefi.position.models.Favorite;

import org.joda.time.DateTime;

public class FavoriteRepository {
    PositionDataBase mDb;

    public FavoriteRepository(PositionDataBase mDb) {
        this.mDb = mDb;
    }

    public void onSave(String name, Favorite favorite) {
        AppExecutor.getInstance().diskIO().execute(() -> {
            favorite.setSavedName(name);
            favorite.setDate(DateTime.now().getMillis());
            mDb.favoriteDao().insert(favorite);

        });
    }
}
