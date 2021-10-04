package com.sogefi.position.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.sogefi.position.R;
import com.sogefi.position.database.AppExecutor;
import com.sogefi.position.database.PositionDataBase;
import com.sogefi.position.models.Favorite;
import com.sogefi.position.repositories.FavoriteRepository;
import com.sogefi.position.ui.activities.adapters.FavoriteAdapter;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class FavoriteActivity extends AppCompatActivity {

    private static final String DEEPLINK_QUERY_FRIEND_POSITION = "friend_position";
    RecyclerView favorites;
    FavoriteAdapter favoriteAdapter;
    ProgressBar loading;
    ConstraintLayout content;
    List<Favorite> fav;
    FavoriteRepository favoriteRepository;
    private PositionDataBase mDb;
    String favPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        mDb = PositionDataBase.getInstance(getApplicationContext());

        fav = new ArrayList<>();

        favoriteRepository = new FavoriteRepository(mDb);

        loading = findViewById(R.id.loading);
        content = findViewById(R.id.contentFav);

        favorites = findViewById(R.id.favorites);
        favorites.setLayoutManager(new LinearLayoutManager(this));
        favoriteAdapter = new FavoriteAdapter(R.layout.item_favorite, FavoriteActivity.this, fav);
        favorites.setAdapter(favoriteAdapter);


        getFavorites();
    }


    public void getFavorites() {
        onLoading(1);
        AppExecutor.getInstance().diskIO().execute(() -> {
            fav.addAll(mDb.favoriteDao().getAll());
            onLoading(0);
            Timber.tag("NUMBER").d(String.valueOf(fav.size()));
            favoriteAdapter.notifyDataSetChanged();

        });


    }

    public void onEdit(Favorite favorite, int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_edit_favorite, null, false);
        AlertDialog dialog = new MaterialAlertDialogBuilder(this)
                .setView(view)
                .show();
        MaterialButton validate = view.findViewById(R.id.validate);
        MaterialButton cancel = view.findViewById(R.id.cancel);
        EditText name = view.findViewById(R.id.name);
        validate.setOnClickListener(view1 -> {
            favorite.setSavedName(name.getText().toString());
            AppExecutor.getInstance().diskIO().execute(() -> mDb.favoriteDao().update(favorite));
            fav.set(index, favorite);
            dialog.dismiss();
            favoriteAdapter.notifyItemChanged(index);
        });

        cancel.setOnClickListener(view12 -> dialog.dismiss());


    }

    public void onShare(Favorite favorite) {
        sharePosition(favorite.getLat()+","+favorite.getLon());
    }

    public void onDelete(Favorite favorite, int index) {
        AppExecutor.getInstance().diskIO().execute(() -> mDb.favoriteDao().delete(favorite));

        fav.remove(index);
        favoriteAdapter.notifyItemRemoved(index);

    }

    public void onLoading(int visible) {
        if (visible > 0) {
            loading.setVisibility(View.VISIBLE);
        } else {
            loading.setVisibility(View.GONE);
        }
    }

    public void sharePosition(String position) {
        Uri uri = new Uri.Builder()
                .scheme("https")
                .authority("app.position.cm")
                .appendQueryParameter(DEEPLINK_QUERY_FRIEND_POSITION, position)
                .build();
        Timber.tag("URI_URL").d(uri.toString());
        FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(uri)
                .setDomainUriPrefix("https://app.position.cm/")
                .setAndroidParameters(
                        new DynamicLink.AndroidParameters.Builder()
                                .build()
                )
                .buildShortDynamicLink()
                .addOnSuccessListener(shortDynamicLink -> {
                    try {
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Position");
                        String shareMessage = getString(R.string.share_content, shortDynamicLink.getShortLink());
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(shareIntent, "Choississez une application"));
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Erreur survenue lors du partage", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FavoriteActivity.this, MapActivity.class);
        startActivity(intent);
        finish();
    }

    public void viewFavorite(Favorite favorite) {
        favPosition = favorite.getLat() + "," +favorite.getLon();
        Intent intent = new Intent(FavoriteActivity.this, MapActivity.class);
        intent.putExtra(DEEPLINK_QUERY_FRIEND_POSITION, favPosition);
        startActivity(intent);
        finish();
    }

}