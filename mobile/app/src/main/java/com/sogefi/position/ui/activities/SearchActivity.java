package com.sogefi.position.ui.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.sogefi.position.R;
import com.sogefi.position.api.APIClient;
import com.sogefi.position.api.ApiInterface;
import com.sogefi.position.models.Nominatim;
import com.sogefi.position.provider.MySuggestionProvider;
import com.sogefi.position.ui.activities.adapters.SearchNominAdapter;
import com.sogefi.position.utils.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

public class SearchActivity extends AppCompatActivity {
    SearchNominAdapter searchNominAdapter;
    RecyclerView recyClerView;
    ProgressBar loadingSearch;
    ApiInterface apiInterface;
    SearchView searchEdit;
    PreferenceManager pref;
    String extra;
    private List<Nominatim> nominatimList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        pref = new PreferenceManager(this);

        extra = getIntent().getStringExtra("EXTRA");

        nominatimList = new ArrayList<>();
        searchNominAdapter = new SearchNominAdapter(R.layout.item_suggestion, nominatimList, this);

        searchEdit = findViewById(R.id.searchEdit);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchEdit.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        loadingSearch = findViewById(R.id.loadingSearch);
        loadingSearch.setVisibility(View.VISIBLE);

        recyClerView = findViewById(R.id.recycler_view);
        recyClerView.setLayoutManager(new LinearLayoutManager(this));
        recyClerView.setAdapter(searchNominAdapter);


        apiInterface = APIClient.getNewClient2().create(ApiInterface.class);

        handleIntent(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    MySuggestionProvider.AUTHORITY, MySuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);
            searchEdit.setIconifiedByDefault(false);
            searchEdit.setSubmitButtonEnabled(true);
            searchEdit.setQueryHint(query);
            search(query);
        }
    }

    public void search(String query) {
        nominatimList.clear();
        recyClerView.setVisibility(View.VISIBLE);
        //Lacement de la requete RxJAVA
        Single<Response<List<Nominatim>>> testObservable = apiInterface.nominatimRx(query, "json", 1, "cm");
        testObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getRequestObserver(searchNominAdapter, query));
    }

    //Requete RxJava pour recuprer la liste des Lieux nominatims
    private SingleObserver getRequestObserver(SearchNominAdapter adapter, String query) {
        return new SingleObserver<Response<List<Nominatim>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Response<List<Nominatim>> listResponse) {
                try {
                    if ((listResponse.body() != null ? listResponse.body().size() : 0) != 0) {
                        Timber.tag("resultats").d(String.valueOf(listResponse.body().size()));
                        nominatimList.addAll(listResponse.body());
                        loadingSearch.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();
                    } else {

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onError(Throwable e) {
            }

        };
    }

    public void onSuggestionClick(String lon, String lat, String name) {
        pref.setLon(lon);
        pref.setLat(lat);
      /*  Log.d("EXTRA",extra);
        if(extra.equals("origin")) {
            pref.setNameORI(name);
            pref.setType("origin");
        } if (extra.equals("destination")) {
            pref.setNameDest(name);
            pref.setType("destination");
        }*/
        finish();

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}