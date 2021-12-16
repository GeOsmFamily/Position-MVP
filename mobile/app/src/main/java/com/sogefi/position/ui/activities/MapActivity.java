package com.sogefi.position.ui.activities;

import static com.google.android.gms.common.util.CollectionUtils.listOf;
import static com.mapbox.core.constants.Constants.PRECISION_6;
import static com.mapbox.mapboxsdk.style.expressions.Expression.accumulated;
import static com.mapbox.mapboxsdk.style.expressions.Expression.all;
import static com.mapbox.mapboxsdk.style.expressions.Expression.concat;
import static com.mapbox.mapboxsdk.style.expressions.Expression.division;
import static com.mapbox.mapboxsdk.style.expressions.Expression.exponential;
import static com.mapbox.mapboxsdk.style.expressions.Expression.get;
import static com.mapbox.mapboxsdk.style.expressions.Expression.gt;
import static com.mapbox.mapboxsdk.style.expressions.Expression.gte;
import static com.mapbox.mapboxsdk.style.expressions.Expression.has;
import static com.mapbox.mapboxsdk.style.expressions.Expression.interpolate;
import static com.mapbox.mapboxsdk.style.expressions.Expression.literal;
import static com.mapbox.mapboxsdk.style.expressions.Expression.lt;
import static com.mapbox.mapboxsdk.style.expressions.Expression.max;
import static com.mapbox.mapboxsdk.style.expressions.Expression.neq;
import static com.mapbox.mapboxsdk.style.expressions.Expression.rgb;
import static com.mapbox.mapboxsdk.style.expressions.Expression.stop;
import static com.mapbox.mapboxsdk.style.expressions.Expression.toNumber;
import static com.mapbox.mapboxsdk.style.expressions.Expression.zoom;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.circleColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.circleRadius;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillOpacity;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillOutlineColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconSize;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.symbolSortKey;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textAnchor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textField;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textJustify;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textSize;
import static com.sogefi.position.R2.id.all;
import static com.sogefi.position.R2.id.stop;
import static com.sogefi.position.utils.Constants.API_KEY;
import static com.sogefi.position.utils.Constants.IMAGEURL;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.gson.Gson;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.directions.v5.DirectionsCriteria;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.OnSymbolClickListener;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;
import com.mapbox.mapboxsdk.style.expressions.Expression;
import com.mapbox.mapboxsdk.style.layers.CircleLayer;
import com.mapbox.mapboxsdk.style.layers.FillLayer;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.layers.TransitionOptions;
import com.mapbox.mapboxsdk.style.sources.GeoJsonOptions;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.utils.BitmapUtils;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;
import com.mapbox.turf.TurfJoins;
import com.nguyenhoanglam.imagepicker.view.GridSpacingItemDecoration;
import com.sogefi.position.BuildConfig;
import com.sogefi.position.R;
import com.sogefi.position.api.APIClient;
import com.sogefi.position.api.ApiInterface;
import com.sogefi.position.database.PositionDataBase;
import com.sogefi.position.models.Batiments;
import com.sogefi.position.models.BatimentsModel;
import com.sogefi.position.models.Categories;
import com.sogefi.position.models.Etablissements;
import com.sogefi.position.models.Favorite;
import com.sogefi.position.models.Language;
import com.sogefi.position.models.Nominatim;
import com.sogefi.position.models.ResponseApi;
import com.sogefi.position.models.ResponseModel;
import com.sogefi.position.models.Search;
import com.sogefi.position.models.SearchEtablissement;
import com.sogefi.position.models.Tracking;
import com.sogefi.position.models.UserModel;
import com.sogefi.position.models.Zones;
import com.sogefi.position.models.data.DataBatiments;
import com.sogefi.position.models.data.DataCategories;
import com.sogefi.position.models.data.DataEtablissements;
import com.sogefi.position.models.data.DataSearchEtablissement;
import com.sogefi.position.models.data.DataTracking;
import com.sogefi.position.repositories.FavoriteRepository;
import com.sogefi.position.repositories.TrackingRepository;
import com.sogefi.position.ui.TopIconButton;
import com.sogefi.position.ui.activities.adapters.CategoriesAdapter;
import com.sogefi.position.ui.activities.adapters.EtablissementAdapter;
import com.sogefi.position.ui.activities.adapters.LanguagesAdapter;
import com.sogefi.position.ui.activities.adapters.SearchAdapter;
import com.sogefi.position.utils.Function;
import com.sogefi.position.utils.MapBoxUtils;
import com.sogefi.position.utils.PreferenceManager;
import com.sogefi.position.workers.UpdateLocationWorker;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MapActivity extends AppCompatActivity implements
        OnMapReadyCallback, PermissionsListener ,MapboxMap.OnMapLongClickListener, MapboxMap.OnMapClickListener, MaterialSearchBar.OnSearchActionListener {

    private static final String DEEPLINK_QUERY_FRIEND_POSITION = "friend_position";
    private static final String ORIGIN_ICON_ID = "origin-icon-id";
    private static final String DESTINATION_ICON_ID = "destination-icon-id";
    private static final String ROUTE_LAYER_ID = "route-layer-id";
    private static final String ROUTE_LINE_SOURCE_ID = "route-source-id";
    private static final String ICON_LAYER_ID = "icon-layer-id";
    private static final String ICON_SOURCE_ID = "icon-source-id";
    private static final String GEOJSON_SOURCE_ID = "geojson-source-id";
    private static final String UNCLUSTERED_POINTS = "unclustered-points";
    private static final String GEOJSON_ICON_ID = "geojson-icon-id";
    private static final String TAG = "LocationUpdate";
    FloatingActionButton location;
    FloatingActionButton layer;
    FloatingActionButton zoomIn;
    FloatingActionButton zoomOut;
    FloatingActionButton findPositionDialog;
   // FloatingActionButton routePosition;
  //  FloatingActionButton addToFavoritePosition;
    FloatingActionButton sharePosition;
    FloatingActionButton clearButton;
    FloatingActionButton clearRouteButton;
    FloatingActionButton newBusiness;
    ExtendedFloatingActionButton findPosition;
    ProgressBar bottomSheetProgress;
    TextView lieu;
    TextView adresseV;
    TextView arrival;
    TextView duration;
    TextView lenght;
    TextView positionToSave;
    TextView user_label;
    TextView user_sub_label;
    EditText saveName;
    EditText origin;
    EditText destination;
    Group groupMyPosition;
    Group groupSharePosition;
    Group groupRouteSearch;
    Group groupRoute;
    Group groupSavePosition;
    MaterialButton run;
    MaterialButton saveSubmit;
    int checkItem, checkProfile = 0;
   // TopIconButton route;
   // TopIconButton save;
    TopIconButton share;
   // TopIconButton near;
    String friendPosition , latTv,lonTv;
    FavoriteRepository favoriteRepository;
    TrackingRepository trackingRepository;
    PositionDataBase mDb;
    MaterialToolbar toolbar;
    NavigationView nav;
    DrawerLayout drawer;
    PreferenceManager pref;
    MaterialSearchBar search;
    MaterialButton searchB;
    CircularImageView user_image;
    private MapboxMap mapboxMap;
    private MapView mapView;
    private BottomSheetBehavior sheetBehavior;
    private ConstraintLayout bottom_sheet;
    private SymbolManager symbolManager;
    private Symbol symbol;
    private static final int REQUEST_CODE = 1234;
    private SearchAdapter searchAdapter;

    JSONObject featureCollection = new JSONObject();
    MapBoxUtils mapBoxUtils;

    RecyclerView chipsLayout;

    List<DataSearchEtablissement> searchResult = new ArrayList<>();
    List<Nominatim> nominatimList = new ArrayList<>();

    String longo,latgo;

    AlertDialog mat;

    private GeoJsonSource clusterSource;
    private int clickOptionCounter = 0;
    private static final double CAMERA_ZOOM_DELTA = 0.01;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_map);






        //  mapBoxUtils= new MapBoxUtils(mapboxMap);
        pref = new PreferenceManager(this);



        friendPosition = getIntent().getStringExtra(DEEPLINK_QUERY_FRIEND_POSITION);


        mDb = PositionDataBase.getInstance(getApplicationContext());

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer);
        nav = findViewById(R.id.navigation);




        nav.setItemIconTintList(null);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> drawer.openDrawer(GravityCompat.START));
        setNavigationDrawer();
        whiteNotificationBar(toolbar);

        favoriteRepository = new FavoriteRepository(mDb);

        search = findViewById(R.id.search);
        search.setCardViewElevation(50);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
         searchAdapter = new SearchAdapter(inflater,this);
        searchAdapter.setSuggestions(searchResult);
        search.setCustomSuggestionAdapter(searchAdapter);
        search.setOnSearchActionListener(this);
        search.setNavButtonEnabled(true);


        search.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                search.clearSuggestions();
                searchResult.clear();
                if(search.getText().length() > 3) {
                    searchApi(search.getText());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });


       /* SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));



        search.setIconifiedByDefault(false);
        search.setSubmitButtonEnabled(true);


        search.setQueryHint(getString(R.string.search));*/


        location = findViewById(R.id.location);
        layer = findViewById(R.id.layers);
        zoomIn = findViewById(R.id.zoomIn);
        zoomOut = findViewById(R.id.zoomOut);
        findPositionDialog = findViewById(R.id.findPositionDialog);
        //routePosition = findViewById(R.id.routePosition);
        //addToFavoritePosition = findViewById(R.id.addToFavoritePosition);
        sharePosition = findViewById(R.id.sharePosition);
        clearButton = findViewById(R.id.clearButton);
        clearRouteButton = findViewById(R.id.clearRouteButton);
        newBusiness = findViewById(R.id.newBussiness);
        chipsLayout = findViewById(R.id.recycler_chips);


        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        chipsLayout.setLayoutManager(layoutManager);




        findPosition = findViewById(R.id.findPosition);

        bottomSheetProgress = findViewById(R.id.bottomSheetProgress);


        lieu = findViewById(R.id.lieu);
        adresseV = findViewById(R.id.adresseV);
        arrival = findViewById(R.id.arrival);
        duration = findViewById(R.id.duration);
        lenght = findViewById(R.id.length);
        positionToSave = findViewById(R.id.positionToSave);
        View headerView = nav.getHeaderView(0);
        user_label = headerView.findViewById(R.id.user_label);
        user_sub_label = headerView.findViewById(R.id.user_sub_label);

        user_image = headerView.findViewById(R.id.user_image);

        saveName = findViewById(R.id.saveName);

        origin = findViewById(R.id.origin);

        destination = findViewById(R.id.destination);


        groupMyPosition = findViewById(R.id.groupMyPosition);
        groupSharePosition = findViewById(R.id.groupSharedPosition);
        groupRouteSearch = findViewById(R.id.groupRouteSearch);
        groupRoute = findViewById(R.id.groupRoute);
        groupSavePosition = findViewById(R.id.groupSavePosition);


        bottom_sheet = findViewById(R.id.bottomSheet);
        sheetBehavior = BottomSheetBehavior.from(bottom_sheet);
      //  route = findViewById(R.id.route);
      //  save = findViewById(R.id.save);
        share = findViewById(R.id.share);
     //   near = findViewById(R.id.near);

        run = findViewById(R.id.run);
        saveSubmit = findViewById(R.id.saveSubmit);
        searchB = findViewById(R.id.searchB);

        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        launchWorker();

        if(!pref.getToken().equals("token")) {
            getUsers();
        }



        location.setOnClickListener(v -> {
            Objects.requireNonNull(mapboxMap.getStyle()).removeLayer(ROUTE_LAYER_ID);
            mapboxMap.getStyle().removeLayer(ICON_LAYER_ID);
            mapboxMap.getStyle().removeSource(ICON_SOURCE_ID);
            mapboxMap.getStyle().removeSource(ROUTE_LINE_SOURCE_ID);

            LocationComponent locationComponent = mapboxMap.getLocationComponent();

            locationComponent.setCameraMode(CameraMode.TRACKING);
            locationComponent.setRenderMode(RenderMode.COMPASS);
            locationComponent.zoomWhileTracking(18);


        });

        layer.setOnClickListener(v -> alertDialog());

        zoomIn.setOnClickListener(v -> mapBoxUtils.incrZoom(0.5));

        zoomOut.setOnClickListener(v -> mapBoxUtils.incrZoom(-0.5));

        findPosition.setOnClickListener(v -> {
            if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                getPositionCode();
            } else {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });


        findPositionDialog.setOnClickListener(v -> getPositionCode());

        sheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    findPosition.setVisibility(View.VISIBLE);
                    findPositionDialog.setVisibility(View.GONE);
                } else {
                    findPosition.setVisibility(View.GONE);
                    findPositionDialog.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

     //   route.setOnClickListener(v -> showRouteSearch());

        share.setOnClickListener(v -> sharePosition(latTv+ ","+lonTv));

    //    save.setOnClickListener(v -> showSave(lieu.getText().toString(), latTv, lonTv));

      //  near.setOnClickListener(view -> Toast.makeText(getApplicationContext(), "Bientôt disponible", Toast.LENGTH_LONG).show());

    //    routePosition.setOnClickListener(v -> alertDialogProfile());

      //  addToFavoritePosition.setOnClickListener(view -> showSave(adresseV.getText().toString(), latTv, lonTv));

        sharePosition.setOnClickListener(view -> sharePosition(latTv+ ","+lonTv));

        searchB.setOnClickListener(view -> {
            pref.setNameORI("non");
            pref.setNameDest("non");
            //  Toast.makeText(getApplicationContext(),pref.getLonori(),Toast.LENGTH_LONG);
            routeSearch(pref.getLonori(), pref.getLatori(), pref.getLondest(), pref.getLatdest());
        });

        origin.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), getString(R.string.origin), Toast.LENGTH_LONG).show();
            pref.setNameORI("oui");
          //  searchManager.startSearch(" ", true, getComponentName(), null, false);

        });

        destination.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), getString(R.string.destination), Toast.LENGTH_LONG).show();
            pref.setNameDest("oui");
          //  searchManager.startSearch(" ", true, getComponentName(), null, false);

        });


        clearButton.setOnClickListener(v -> clearAll());

        clearRouteButton.setOnClickListener(v -> clearAll());

        newBusiness.setOnClickListener(v -> {
            if(pref.getRoleid().equals("2") || pref.getRoleid().equals("1")) {
                LocationComponent locationComponent = mapboxMap.getLocationComponent();
                Location location = locationComponent.getLastKnownLocation();
                String lon = String.valueOf(location != null ? location.getLongitude() : 0);
                String lat = String.valueOf(location != null ? location.getLatitude() : 0);
                Intent intent = new Intent(MapActivity.this, NewBusiness5Activity.class);
                intent.putExtra("longitude",longo);
                intent.putExtra("latitude",latgo);
                intent.putExtra("adresseName",longo+","+latgo);
                drawer.closeDrawers();
                startActivity(intent);
            } else if(pref.getRoleid().equals("roleid")) {
                Toast.makeText(getApplicationContext(), getString(R.string.connectToSaveBusiness), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MapActivity.this, LoginActivity.class);
                drawer.closeDrawers();
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.noCommercial), Toast.LENGTH_LONG).show();
            }

        });

        if(!pref.getName().equals("name")) {
           user_label.setText(pref.getName());
           user_sub_label.setVisibility(View.VISIBLE);
           user_sub_label.setText(pref.getEmail());

           if(pref.getProfileimage() != "profileimage") {
               Picasso.get().load(IMAGEURL+pref.getProfileimage()).into(user_image);
           }

           user_image.setOnClickListener(v -> {
             /*  Intent intent = new Intent(MapActivity.this, ProfileActivity.class);
               startActivity(intent);*/
           });
        } else {
            user_label.setText("Position");
        }

    getCategories();



    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void clearAll() {
        if (symbolManager != null) symbolManager.deleteAll();
        Objects.requireNonNull(mapboxMap.getStyle()).removeLayer(ROUTE_LAYER_ID);
        mapboxMap.getStyle().removeLayer(ICON_LAYER_ID);
        mapboxMap.getStyle().removeSource(ICON_SOURCE_ID);
        mapboxMap.getStyle().removeSource(ROUTE_LINE_SOURCE_ID);
        BottomSheetBehavior.from(bottom_sheet).setState(BottomSheetBehavior.STATE_COLLAPSED);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //Methode pour gerer les elements du DrawerNavigation
    private void setNavigationDrawer() {
        nav.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if(itemId == R.id.loginActivity) {
                if(pref.getToken().equals("token")) {
                    Intent intent = new Intent(this, LoginActivity.class);
                    drawer.closeDrawers();
                    startActivity(intent);
                } else {
                    drawer.closeDrawers();
                    logout();
                }

            }
          /*  else if(itemId == R.id.userProfile) {
                if(!pref.getToken().equals("token")) {
                    item.setVisible(true);
                    Intent intent = new Intent(this, ProfileActivity.class);
                    drawer.closeDrawers();
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, LoginActivity.class);
                    drawer.closeDrawers();
                    startActivity(intent);
                    finish();
                }

            }*/
            else if(itemId == R.id.myBussiness) {
                if(pref.getRoleid().equals("3")) {
                    item.setVisible(true);
                    Intent intent = new Intent(this, BusinessActivity.class);
                    drawer.closeDrawers();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.connectToViewBusiness), Toast.LENGTH_LONG).show();

                }

            } else if(itemId == R.id.settings) {
                Intent intent = new Intent(this, SettingsActivity.class);
                drawer.closeDrawers();
                startActivity(intent);
            }
            else if (itemId == R.id.favoritesFragment) {
                Intent intent = new Intent(this, FavoriteActivity.class);
                drawer.closeDrawers();
                startActivity(intent);
                finish();
            } else if (itemId == R.id.menuLanguage) {
                drawer.closeDrawers();
                View dialogLanguage = LayoutInflater.from(MapActivity.this).inflate(R.layout.dialog_language, null, false);

                RecyclerView languages = dialogLanguage.findViewById(R.id.languages);
                Language french = new Language();
                french.setName(R.string.french);
                french.setPicture(R.drawable.fr_flag);
                french.setLocale("fr");

                Language english = new Language();
                english.setName(R.string.english);
                english.setPicture(R.drawable.uk_flag);
                english.setLocale("en");

                List<Language> lang = new ArrayList<>();
                lang.add(french);
                lang.add(english);

                languages.setAdapter(new LanguagesAdapter(R.layout.item_language, MapActivity.this, lang));

                new MaterialAlertDialogBuilder(MapActivity.this)
                        .setView(dialogLanguage)
                        .show();
            } /*else if (itemId == R.id.menuRecommend) {
                shareRecommend();
                drawer.closeDrawers();
            } else if (itemId == R.id.tutorielFragment) {
                pref.firstConnect("no");
                Intent intent = new Intent(MapActivity.this, TutorielActivity.class);
                drawer.closeDrawers();
                startActivity(intent);
                finish();
            }*/ else if (itemId == R.id.aboutDialog) {
                Intent intent = new Intent(MapActivity.this, AboutActivity.class);
                drawer.closeDrawers();
                startActivity(intent);
               /* View dialogAbout = LayoutInflater.from(MapActivity.this).inflate(R.layout.dialog_about, null, false);
                AlertDialog alertDialog = new MaterialAlertDialogBuilder(MapActivity.this)
                        .setView(dialogAbout)
                        .show();
                TextView version = dialogAbout.findViewById(R.id.version);
                MaterialButton ok = dialogAbout.findViewById(R.id.ok);
                version.setText(BuildConfig.VERSION_NAME);
                ok.setOnClickListener(view -> alertDialog.dismiss());*/
            }
            return false;
        });
    }

    //Methode pour gerer le changement de langue de l'application
    public void onLocaleChanged(String locale) {
        Locale myLocale = new Locale(locale);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

        Intent refresh = new Intent(this, MapActivity.class);
        finish();
        startActivity(refresh);
    }

    //Méthode pour changer le style de la carte
    public void newStyle(String style) {
        mapboxMap.setStyle(style, style1 -> {
            initSpaceStationSymbolLayer(style1);
            mapboxMap.addOnMapLongClickListener(MapActivity.this);
            mapboxMap.addOnMapClickListener(MapActivity.this);
            if(pref.getRoleid().equals("2") || pref.getRoleid().equals("1")) {
                //  getBatiment();

                geojsonBatiment(style1);


            }
        });
        mapBoxUtils.setMapSetting();

        pref.setStyle(style);




    }

    public void alertDialog() {
        final String[] items = {"STREET", "SATELLITE"};
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme));
        builder.setTitle( getString(R.string.selectStyle));
        builder.setSingleChoiceItems(items, checkItem, (dialog, which) -> {
            switch (which) {
                case 0:
                    newStyle(Style.MAPBOX_STREETS);
                    checkItem = which;
                    dialog.dismiss();
                    break;
                case 1:
                    newStyle(Style.SATELLITE);
                    checkItem = which;
                    dialog.dismiss();
                    break;
                case 2:
                    newStyle(Style.LIGHT);
                    checkItem = which;
                    dialog.dismiss();
                    break;
                case 3:
                    newStyle(Style.TRAFFIC_NIGHT);
                    checkItem = which;
                    dialog.dismiss();
                    break;
                case 4:
                    newStyle("mapbox://styles/mapbox/cj3kbeqzo00022smj7akz3o1e");
                    checkItem = which;
                    dialog.dismiss();
                    break;
            }

        });
        AlertDialog ad = builder.create();
        ad.show();
    }

    public void alertDialogProfile() {
        final String[] items = {getString(R.string.walking), getString(R.string.traffic_driving), getString(R.string.driving)};
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme));
        builder.setTitle(getString(R.string.selectProfile));
        builder.setSingleChoiceItems(items, checkProfile, (dialog, which) -> {
            switch (which) {
                case 0:
                    routePosition(DirectionsCriteria.PROFILE_WALKING);
                    checkProfile = which;
                    dialog.dismiss();
                    break;
                case 1:
                    routePosition(DirectionsCriteria.PROFILE_DRIVING_TRAFFIC);
                    checkProfile = which;
                    dialog.dismiss();
                    break;
                case 2:
                    routePosition(DirectionsCriteria.PROFILE_DRIVING);
                    checkProfile = which;
                    dialog.dismiss();
                    break;
            }

        });
        AlertDialog ad = builder.create();
        ad.show();
    }


    //Methode pour recuperer la position de l'utilisateur
    public void getPositionCode() {
      search.closeSearch();
        if (symbolManager != null) symbolManager.deleteAll();
        Objects.requireNonNull(mapboxMap.getStyle()).removeLayer(ROUTE_LAYER_ID);
        mapboxMap.getStyle().removeLayer(ICON_LAYER_ID);
        mapboxMap.getStyle().removeSource(ICON_SOURCE_ID);
        mapboxMap.getStyle().removeSource(ROUTE_LINE_SOURCE_ID);


        onBottomSheetLoading(1);
        groupMyPosition.setVisibility(View.VISIBLE);
        groupSharePosition.setVisibility(View.GONE);
        groupRouteSearch.setVisibility(View.GONE);
        groupRoute.setVisibility(View.GONE);
        groupSavePosition.setVisibility(View.GONE);

       // route.setVisibility(View.VISIBLE);
       // save.setVisibility(View.VISIBLE);
        share.setVisibility(View.VISIBLE);
      //  near.setVisibility(View.VISIBLE);
        LocationComponent locationComponent = mapboxMap.getLocationComponent();
        locationComponent.setCameraMode(CameraMode.TRACKING);
        locationComponent.setRenderMode(RenderMode.COMPASS);
        locationComponent.zoomWhileTracking(18);
        Location location = locationComponent.getLastKnownLocation();
        String lon = String.valueOf(location != null ? location.getLongitude() : 0);
        String lat = String.valueOf(location != null ? location.getLatitude() : 0);
        sendLocation(location);



        if (Function.isNetworkAvailable(getApplicationContext())) {
            nominatimCoord(lat,lon,lieu);
            onBottomSheetLoading(0);
        } else {
            onBottomSheetLoading(0);
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();

        }


if(pref.getRoleid().equals("2")) {
    addZone(mapboxMap.getStyle(),Integer.parseInt(pref.getZoneid()));
}



    }

    //Afficher ou reduire le bottomSheet
    public void onBottomSheetLoading(int loading) {
        if (loading > 0) {
            bottomSheetProgress.setVisibility(View.VISIBLE);
            expandBottomSheet();
        } else {
            bottomSheetProgress.setVisibility(View.GONE);
        }
    }

    //surelever le bootomSheet
    public void expandBottomSheet() {
        BottomSheetBehavior.from(bottom_sheet).setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    //afficher la boite avec les parametres de route
    public void showRouteSearch() {
        if (symbolManager != null) symbolManager.deleteAll();
        groupMyPosition.setVisibility(View.GONE);
        groupSharePosition.setVisibility(View.GONE);
        groupRouteSearch.setVisibility(View.VISIBLE);
        groupRoute.setVisibility(View.GONE);
        groupSavePosition.setVisibility(View.GONE);

      //  route.setVisibility(View.VISIBLE);
      //  save.setVisibility(View.VISIBLE);
        share.setVisibility(View.VISIBLE);
       // near.setVisibility(View.VISIBLE);

        expandBottomSheet();
    }

    //Generer un lien dynamique et le partager
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
                        startActivity(Intent.createChooser(shareIntent, getString(R.string.chooseApp)));
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), getString(R.string.shareError), Toast.LENGTH_LONG).show();
                    }
                });
    }

    //Afficher et sauvegerder une position
    public void showSave(String displayName, String lat, String lon) {
        positionToSave.setText(displayName);

        Favorite favorite = new Favorite();
        favorite.setDisplayName(displayName);
        favorite.setLat(lat);
        favorite.setLon(lon);

        saveSubmit.setOnClickListener(view -> {
           // hideSoftKeyboard();
            saveFavorite(saveName.getText().toString(), favorite);

            clearAll();

            BottomSheetBehavior.from(bottom_sheet).setState(BottomSheetBehavior.STATE_COLLAPSED);

        });

        saveName.setOnEditorActionListener((textView, i, keyEvent) -> {
            hideSoftKeyboard();
            if (i == EditorInfo.IME_ACTION_DONE) {
                saveFavorite(saveName.getText().toString(), favorite);
            }
            return false;
        });

        groupMyPosition.setVisibility(View.GONE);
        groupSharePosition.setVisibility(View.GONE);
        groupRouteSearch.setVisibility(View.GONE);
        groupRoute.setVisibility(View.GONE);
        groupSavePosition.setVisibility(View.VISIBLE);

      //  route.setVisibility(View.VISIBLE);
       // save.setVisibility(View.VISIBLE);
        share.setVisibility(View.VISIBLE);
       // near.setVisibility(View.VISIBLE);
    }

    //Masquer le clavier
    public void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

    }

    //Sauvegarder la position
    public void saveFavorite(String name, Favorite favorite) {

        favoriteRepository.onSave(name, favorite);
        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
        saveName.setText("");
    }

    //afficher la route
    public void routePosition(String profile) {
        groupMyPosition.setVisibility(View.GONE);
        groupSharePosition.setVisibility(View.GONE);
        groupRouteSearch.setVisibility(View.GONE);
        groupRoute.setVisibility(View.VISIBLE);
        groupSavePosition.setVisibility(View.GONE);

       // route.setVisibility(View.VISIBLE);
       // save.setVisibility(View.VISIBLE);
        share.setVisibility(View.VISIBLE);
       // near.setVisibility(View.VISIBLE);

        LocationComponent locationComponent = mapboxMap.getLocationComponent();
        Point destinationPoint = Point.fromLngLat(symbol.getLatLng().getLongitude(), symbol.getLatLng().getLatitude());
        Point originPoint = Point.fromLngLat(locationComponent.getLastKnownLocation() != null ? locationComponent.getLastKnownLocation().getLongitude() : 0,
                locationComponent.getLastKnownLocation() != null ? locationComponent.getLastKnownLocation().getLatitude() : 0);

        getRoute(originPoint, destinationPoint,profile);


    }

    //Recherche de route et application du style de route
    public void getRoute(Point origin, Point destination,String profile) {
        if (symbolManager != null) symbolManager.deleteAll();
        if (Function.isNetworkAvailable(getApplicationContext())) {
            assert Mapbox.getAccessToken() != null;
            NavigationRoute.builder(getApplication())
                    .accessToken(Mapbox.getAccessToken())
                    .origin(origin)
                    .destination(destination)
                    .profile(profile)
                    .alternatives(true)
                    .build()
                    .getRoute(new Callback<DirectionsResponse>() {

                        @Override
                        public void onResponse(@NotNull Call<DirectionsResponse> call, @NotNull Response<DirectionsResponse> response) {
                            Timber.d("Response code: %s", response.code());
                            if (response.body() == null) {
                                Toast.makeText(getApplicationContext(), getString(R.string.noRouteFound), Toast.LENGTH_LONG).show();
                                return;
                            } else if (response.body().routes().size() < 1) {
                                Toast.makeText(getApplicationContext(), getString(R.string.noRouteFound), Toast.LENGTH_LONG).show();
                                return;
                            }
                            mapboxMap.getStyle(style -> {
                                initSpaceStationSymbolLayer(style);
                                style.addSource(new GeoJsonSource(ICON_SOURCE_ID, mapBoxUtils.getOriginAndDestinationFeatureCollection(origin, destination)));

                                GeoJsonSource originDestinationPointGeoJsonSource = style.getSourceAs(ICON_SOURCE_ID);

                                if (originDestinationPointGeoJsonSource != null) {
                                    originDestinationPointGeoJsonSource.setGeoJson(mapBoxUtils.getOriginAndDestinationFeatureCollection(origin, destination));
                                }

                                GeoJsonSource lineLayerRouteGeoJsonSource = style.getSourceAs(ROUTE_LINE_SOURCE_ID);

                                if (lineLayerRouteGeoJsonSource != null) {
                                    LineString lineString = LineString.fromPolyline(Objects.requireNonNull(response.body().routes().get(0).geometry()), PRECISION_6);
                                    lineLayerRouteGeoJsonSource.setGeoJson(Feature.fromGeometry(lineString));
                                }
                            });


                            long duree = (Objects.requireNonNull(response.body().routes().get(0).duration()).longValue());

                            DateTimeFormatter arrivalFormatter = DateTimeFormat.forPattern(getString(R.string.format_arrival));
                            DateTime end = DateTime.now().plus(duree * 1000);
                            Duration d = new Duration(DateTime.now(), end);

                            arrival.setText(arrivalFormatter.print(end));
                            duration.setText(getString(R.string.format_duration, d.getStandardMinutes()));
                            lenght.setText(getString(R.string.format_length, response.body().routes().get(0).distance() / 1000));

                            run.setOnClickListener(view -> {
                                Intent intent = new Intent(getBaseContext(), NavigationActivity.class);
                                intent.putExtra("ROUTE", response.body().routes().get(0).toJson());
                                startActivity(intent);
                            });

                            expandBottomSheet();

                        }

                        @Override
                        public void onFailure(@NotNull Call<DirectionsResponse> call, @NotNull Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        }
                    });

        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }
    }

    //Methode pour recommender l'appli
    public void shareRecommend() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Position");
            String shareMessage = getString(R.string.recommend);
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, getString(R.string.chooseApp)));
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), getString(R.string.shareError), Toast.LENGTH_LONG).show();
        }
    }

    //Convertir les drawable en bitmap pour les symboles
    private void initSpaceStationSymbolLayer(@NonNull Style style) {

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_destination);
        assert drawable != null;
        style.addImage(
                "markerImage",
                Function.getBitmapFromDrawable(drawable)
        );

        Drawable drawableOrigin = ContextCompat.getDrawable(this, R.drawable.ic_origin);
        assert drawableOrigin != null;
        style.addImage(
                "markerOriginImage",
                Function.getBitmapFromDrawable(drawableOrigin)
        );

       Drawable drawableBatiment = ContextCompat.getDrawable(this, R.drawable.building);
        assert drawableBatiment != null;
        style.addImage(
                "markerBatimentImage",
                Function.getBitmapFromDrawable(drawableBatiment)
        );



        mapBoxUtils.initSources(style);
        mapBoxUtils.initLayers(style);

    }


    @Override
    public void onExplanationNeeded(List<String> list) {
        Toast.makeText(this, "Demande de permission", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean b) {
        if (b) {
            Intent intent = new Intent(MapActivity.this, TutorielActivity.class);
            intent.putExtra(DEEPLINK_QUERY_FRIEND_POSITION, friendPosition);
            startActivity(intent);
        } else Toast.makeText(this, "Permission non accordée", Toast.LENGTH_LONG).show();
        finish();
    }





    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        MapActivity.this.mapboxMap = mapboxMap;
        mapBoxUtils= new MapBoxUtils(mapboxMap);




        String styleLaunch = Style.MAPBOX_STREETS;

        if(!pref.getStyle().equals("style")) {
            styleLaunch = pref.getStyle();
        }



        mapboxMap.setStyle(new Style.Builder().fromUri(styleLaunch)
             /*  .withSource(clusterSource = createClusterSource())
                .withLayer(createSymbolLayer())
                .withLayer(createClusterLevelLayer(0, clusterLayers))
                .withLayer(createClusterLevelLayer(1, clusterLayers))
                .withLayer(createClusterLevelLayer(2, clusterLayers))
                .withLayer(createClusterTextLayer())*/
                .withImage(ORIGIN_ICON_ID, Objects.requireNonNull(BitmapUtils.getBitmapFromDrawable(
                        getResources().getDrawable(R.drawable.ic_origin))))
                .withImage(DESTINATION_ICON_ID, Objects.requireNonNull(BitmapUtils.getBitmapFromDrawable(
                        getResources().getDrawable(R.drawable.ic_finish)))), style -> {
            enableLocationComponent(style);
            initSpaceStationSymbolLayer(style);

         //   style.setTransition(new TransitionOptions(0, 0, false));

            mapboxMap.addOnMapLongClickListener(MapActivity.this);
            mapboxMap.addOnMapClickListener(MapActivity.this);
            if (friendPosition != null) {
                getSharedPosition(friendPosition);
            }
            if(pref.getRoleid().equals("2") || pref.getRoleid().equals("1")) {
              //  getBatiment();

                geojsonBatiment(style);


            }

        });


        mapBoxUtils.setMapSetting();
    }

    //Methode pour zoomer sur la position de l'utilisateur après l'avoir recuperée
    @SuppressLint("MissingPermission")
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            LocationComponent locationComponent = mapboxMap.getLocationComponent();
            locationComponent.activateLocationComponent(LocationComponentActivationOptions.builder(this, loadedMapStyle).build());
            locationComponent.setLocationComponentEnabled(true);
            locationComponent.setCameraMode(CameraMode.TRACKING);
            locationComponent.setRenderMode(RenderMode.COMPASS);
            locationComponent.zoomWhileTracking(15);

        } else {
            PermissionsManager permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    //Recuperer et afficher la position partagée
    public void getSharedPosition(String friendPosition) {
        String[] coordonees =  friendPosition.split(",");
        String lat = coordonees[0];
        String lon = coordonees[1];
        Objects.requireNonNull(mapboxMap.getStyle()).removeLayer(ROUTE_LAYER_ID);
        mapboxMap.getStyle().removeLayer(ICON_LAYER_ID);
        mapboxMap.getStyle().removeSource(ICON_SOURCE_ID);
        mapboxMap.getStyle().removeSource(ROUTE_LINE_SOURCE_ID);


        arrival.setText("");
        duration.setText("");
        lenght.setText("");
        onBottomSheetLoading(1);
        groupMyPosition.setVisibility(View.GONE);
        groupSharePosition.setVisibility(View.VISIBLE);
        groupRouteSearch.setVisibility(View.GONE);
        groupRoute.setVisibility(View.GONE);
        groupSavePosition.setVisibility(View.GONE);

      //  route.setVisibility(View.GONE);
      //  save.setVisibility(View.GONE);
        share.setVisibility(View.GONE);
       // near.setVisibility(View.GONE);

        if (symbolManager != null) symbolManager.deleteAll();

        symbolManager = new SymbolManager(mapView, mapboxMap, Objects.requireNonNull(mapboxMap.getStyle()));



        symbolManager.setIconAllowOverlap(true);
        symbolManager.setTextAllowOverlap(true);

        if (Function.isNetworkAvailable(getApplicationContext())) {
            onBottomSheetLoading(0);
            nominatimCoord(lat, lon, adresseV);
            LatLng point = new LatLng(Double.parseDouble(lat),Double.parseDouble(lon));

            symbol = symbolManager.create(new SymbolOptions()
                    .withLatLng(point)
                    .withIconImage("markerImage")
                    .withIconSize(1.3f)
                    .withSymbolSortKey(10.0f));

            CameraPosition cam = new CameraPosition.Builder()
                    .target(point)
                    .zoom(15)
                    .build();

            mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(cam), 5000);


        } else {
            onBottomSheetLoading(0);
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();

        }


    }

    //Methode pour tracer l'itinéraire entre deux route
    public void routeSearch(String lonOrigin, String latOrigin, String lonDest, String latDest) {
        if (symbolManager != null) symbolManager.deleteAll();
        hideSoftKeyboard();
        double lon1 = Double.parseDouble(lonOrigin);
        double lat1 = Double.parseDouble(latOrigin);

        double lon2 = Double.parseDouble(lonDest);
        double lat2 = Double.parseDouble(latDest);
        Point ori = Point.fromLngLat(lon1, lat1);
        Point dest = Point.fromLngLat(lon2, lat2);

        LatLng latLng = new LatLng(lat1, lon1);

        CameraPosition cam = new CameraPosition.Builder()
                .target(latLng)
                .zoom(13)
                .build();

        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(cam), 5000);

        hideSoftKeyboard();

        pref.setLonori("lonori");
        pref.setLatori("latori");

        pref.setLondest("londest");
        pref.setLatdest("latdest");

        pref.setType("type");

        origin.setText("");
        destination.setText("");

        groupMyPosition.setVisibility(View.GONE);
        groupSharePosition.setVisibility(View.GONE);
        groupRouteSearch.setVisibility(View.GONE);
        groupRoute.setVisibility(View.VISIBLE);
        groupSavePosition.setVisibility(View.GONE);

       // route.setVisibility(View.VISIBLE);
       // save.setVisibility(View.VISIBLE);
        share.setVisibility(View.VISIBLE);
       // near.setVisibility(View.VISIBLE);

        getRoute(ori, dest,DirectionsCriteria.PROFILE_DRIVING_TRAFFIC);
    }

    //Selectionner une suggestion de recherche
    public void onSuggestionClick(String lon, String lat) {

        if (pref.getNameORI() == "oui") {

            //   Toast.makeText(getApplicationContext(), "Pressed Ori", Toast.LENGTH_LONG).show();
            search.clearFocus();

            if (Function.isNetworkAvailable(getApplicationContext())) {
                nominatimCoord(lat, lon, origin);
                pref.setLonori(lon);
                pref.setLatori(lat);
                pref.setLon("lon");
                pref.setLat("lat");
                pref.setNameORI("non");
                onBottomSheetLoading(0);


            } else {
                onBottomSheetLoading(0);
                Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();

            }
        } else if (pref.getNameDest() == "oui") {

            //  Toast.makeText(getApplicationContext(), "Pressed Dest", Toast.LENGTH_LONG).show();
            search.clearFocus();

            if (Function.isNetworkAvailable(getApplicationContext())) {
                nominatimCoord(lat, lon, destination);
                pref.setLondest(lon);
                pref.setLatdest(lat);
                pref.setLon("lon");
                pref.setLat("lat");
                pref.setNameDest("non");
                onBottomSheetLoading(0);


            } else {
                onBottomSheetLoading(0);
                Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();

            }
        } else {
            //search.setIconified(false);
            search.clearFocus();
            // hideSoftKeyboard();
            LatLng point = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));
            if (symbolManager != null) symbolManager.deleteAll();
            Objects.requireNonNull(mapboxMap.getStyle()).removeLayer(ROUTE_LAYER_ID);
            mapboxMap.getStyle().removeLayer(ICON_LAYER_ID);
            mapboxMap.getStyle().removeSource(ICON_SOURCE_ID);
            mapboxMap.getStyle().removeSource(ROUTE_LINE_SOURCE_ID);
           // getBatiment();

            arrival.setText("");
            duration.setText("");
            lenght.setText("");
            onBottomSheetLoading(1);
            groupMyPosition.setVisibility(View.GONE);
            groupSharePosition.setVisibility(View.VISIBLE);
            groupRouteSearch.setVisibility(View.GONE);
            groupRoute.setVisibility(View.GONE);
            groupSavePosition.setVisibility(View.GONE);

          //  route.setVisibility(View.GONE);
          //  save.setVisibility(View.GONE);
            share.setVisibility(View.GONE);
          //  near.setVisibility(View.GONE);

            symbolManager = new SymbolManager(mapView, mapboxMap, mapboxMap.getStyle());

            symbolManager.setIconAllowOverlap(true);
            symbolManager.setTextAllowOverlap(true);

            symbol = symbolManager.create(new SymbolOptions()
                    .withLatLng(point)
                    .withIconImage("markerImage")
                    .withIconSize(1.3f)
                    .withSymbolSortKey(10.0f));

            CameraPosition cam = new CameraPosition.Builder()
                    .target(point)
                    .zoom(15)
                    .build();

            mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(cam), 5000);

            pref.setLon("lon");
            pref.setLat("lat");

            if (Function.isNetworkAvailable(getApplicationContext())) {
                nominatimCoord(lat, lon, adresseV);
                onBottomSheetLoading(0);


            } else {
                onBottomSheetLoading(0);
                Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();

            }
        }

    }

    //Methode pour recuperer les données nominatim
    public void nominatimCoord(String lat, String lon, TextView displayNameTv) {
        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient().create(ApiInterface.class);
            Call<Nominatim> call = apiService.nominatimCoord(lat, lon, 1, "json");
            call.enqueue(new Callback<Nominatim>() {
                @Override
                public void onResponse(@NotNull Call<Nominatim> call, @NotNull Response<Nominatim> response) {
                    String[] name = response.body() != null ? response.body().getDisplayName().split(",") : null;
                    displayNameTv.setText(name[0]+","+name[1]+","+name[2]+",\n"+name[3]);
                    latTv = (response.body() != null ? response.body().getLat():null);
                    lonTv = (response.body() != null ? response.body().getLon():null);
                    Timber.tag("displayName").d(response.body() != null ? response.body().getDisplayName() : null);
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

    //Methode pour deconnecter l'utilisateur
    public void logout() {
        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<ResponseApi> call = apiService.logout(API_KEY,pref.getToken());

            call.enqueue(new Callback<ResponseApi>() {
                @Override
                public void onResponse(@NotNull Call<ResponseApi> call, @NotNull Response<ResponseApi> response) {
                    pref.setToken("token");
                    pref.setEmail("email");
                    pref.setName("name");
                    pref.setPhone("phone");
                    pref.setId("id");
                    pref.setRoleid("roleid");
                    Intent intent = new Intent(MapActivity.this, SplashActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                }

                @Override
                public void onFailure(@NotNull Call<ResponseApi> call, @NotNull Throwable t) {
                    // Log error here since request failed
                    Timber.tag("logout").e(t.toString());
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
        String lon = pref.getLon();
        String lat = pref.getLat();
        //  Toast.makeText(getApplicationContext(), lon, Toast.LENGTH_LONG).show();
        if (!lon.equals("lon")) {
            onSuggestionClick(lon, lat);
        }



        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        setNavigationDrawer();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        setNavigationDrawer();
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setNavigationDrawer();
        if (symbolManager != null) symbolManager.onDestroy();
        mapView.onDestroy();
    }

    public void resultSearch(String lon,String lat,String type) {
        if (symbolManager != null) symbolManager.deleteAll();
        Objects.requireNonNull(mapboxMap.getStyle()).removeLayer(ROUTE_LAYER_ID);
        mapboxMap.getStyle().removeLayer(ICON_LAYER_ID);
        mapboxMap.getStyle().removeSource(ICON_SOURCE_ID);
        mapboxMap.getStyle().removeSource(ROUTE_LINE_SOURCE_ID);
       // getBatiment();

        arrival.setText("");
        duration.setText("");
        lenght.setText("");
        onBottomSheetLoading(1);
        groupMyPosition.setVisibility(View.GONE);
        groupSharePosition.setVisibility(View.VISIBLE);
        groupRouteSearch.setVisibility(View.GONE);
        groupRoute.setVisibility(View.GONE);
        groupSavePosition.setVisibility(View.GONE);

        share.setVisibility(View.GONE);


        if (symbolManager != null) symbolManager.deleteAll();

        symbolManager = new SymbolManager(mapView, mapboxMap, mapboxMap.getStyle());

        symbolManager.setIconAllowOverlap(true);
        symbolManager.setTextAllowOverlap(true);

        LatLng point = new LatLng(Double.parseDouble(lat),Double.parseDouble(lon));

        symbol = symbolManager.create(new SymbolOptions()
                .withLatLng(point)
                .withIconImage("markerImage")
                .withIconSize(1.3f)
                .withSymbolSortKey(10.0f));



        if(type.equals("nominatim")) {
            if (Function.isNetworkAvailable(getApplicationContext())) {
                nominatimCoord(lat,lon, adresseV);

                CameraPosition cam = new CameraPosition.Builder()
                        .target(point)
                        .zoom(15)
                        .build();

                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(cam), 5000);
                search.closeSearch();
                onBottomSheetLoading(0);


            } else {
                onBottomSheetLoading(0);
                Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();

            }
        } else {

        }
    }



    @Override
    public boolean onMapLongClick(@NonNull LatLng point) {



        longo = String.valueOf(point.getLongitude());
        latgo = String.valueOf(point.getLatitude());



        arrival.setText("");
        duration.setText("");
        lenght.setText("");
        onBottomSheetLoading(1);
        groupMyPosition.setVisibility(View.GONE);
        groupSharePosition.setVisibility(View.VISIBLE);
        groupRouteSearch.setVisibility(View.GONE);
        groupRoute.setVisibility(View.GONE);
        groupSavePosition.setVisibility(View.GONE);

       // route.setVisibility(View.GONE);
       // save.setVisibility(View.GONE);
        share.setVisibility(View.GONE);
       // near.setVisibility(View.GONE);


        if (symbolManager != null) symbolManager.deleteAll();

        symbolManager = new SymbolManager(mapView, mapboxMap, mapboxMap.getStyle());

        symbolManager.setIconAllowOverlap(true);
        symbolManager.setTextAllowOverlap(true);

        symbol = symbolManager.create(new SymbolOptions()
                .withLatLng(point)
                .withIconImage("markerImage")
                .withIconSize(1.3f)
                .withSymbolSortKey(10.0f));



        String lon = String.valueOf(point.getLongitude());
        String lat = String.valueOf(point.getLatitude());

        //On interroge les apis pour recuperer la position
        if (Function.isNetworkAvailable(getApplicationContext())) {
            nominatimCoord(lat,lon, adresseV);
            onBottomSheetLoading(0);

        } else {
            onBottomSheetLoading(0);
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();

        }


        return true;
    }

    public void getBatimentById(String id){
        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<Batiments> call = apiService.getbatimentsbyid(API_KEY,Integer.parseInt(id));

            call.enqueue(new Callback<Batiments>() {
                @Override
                public void onResponse(@NotNull Call<Batiments> call, @NotNull Response<Batiments> response) {
                    LatLng point = new LatLng(Double.parseDouble(response.body().getData().getLatitude()),Double.parseDouble(response.body().getData().getLongitude()));

                    CameraPosition cam = new CameraPosition.Builder()
                            .target(point)
                            .zoom(17)
                            .build();

                    mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(cam), 5000);

                    List<DataEtablissements> listetablissements = response.body().getData().getEtablissements();


                    View dialogEtablissement = LayoutInflater.from(MapActivity.this).inflate(R.layout.dialog_etablissement, null, false);

                    RecyclerView etablissements = dialogEtablissement.findViewById(R.id.etablissements);

                    Button addEtablissement = dialogEtablissement.findViewById(R.id.add_etablissement);
                    ImageView closeButton = dialogEtablissement.findViewById(R.id.close_dialog);
                    ImageView logo_dialog = dialogEtablissement.findViewById(R.id.logo_dialog);
                    TextView textView9 = dialogEtablissement.findViewById(R.id.textView99);
                    if(response.body().getData().getNombreNiveaux() > 1) {

                        textView9.setText(response.body().getData().getNom()+" ("+response.body().getData().getNombreNiveaux()+" étages)");
                    } else {

                        textView9.setText(response.body().getData().getNom()+" ("+response.body().getData().getNombreNiveaux()+" étage)");
                    }
                    closeButton.setOnClickListener(v -> mat.dismiss());

                    addEtablissement.setOnClickListener(v -> {
                        Intent intent = new Intent(MapActivity.this, NewBusinessActivity.class);
                        intent.putExtra("idBatiment",String.valueOf(response.body().getData().getId()));
                        intent.putExtra("nombreNiveau",String.valueOf(response.body().getData().getNombreNiveaux()));
                        startActivity(intent);
                    });



                    etablissements.setAdapter(new EtablissementAdapter(R.layout.item_etablissement, MapActivity.this, listetablissements));

                    mat =  new MaterialAlertDialogBuilder(MapActivity.this)
                            .setView(dialogEtablissement)
                            .show();

                }

                @Override
                public void onFailure(@NotNull Call<Batiments> call, @NotNull Throwable t) {
                    // Log error here since request failed
                    Timber.tag("logout").e(t.toString());
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }
    }

    public void launchWorker() {
        try {
            if (isWorkScheduled(WorkManager.getInstance().getWorkInfosByTag(TAG).get())) {
                // worker en cours
               // WorkManager.getInstance().cancelAllWorkByTag(TAG);
                Log.d(TAG, "WORKER : " + "En cours");
            } else {
                // worker stoppé
                Log.d(TAG, "WORKER : " + "Arreté");
                startWorker();

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private boolean isWorkScheduled(List<WorkInfo> workInfos) {
        boolean running = false;
        if (workInfos == null || workInfos.size() == 0) return false;
        for (WorkInfo workStatus : workInfos) {
            running = workStatus.getState() == WorkInfo.State.RUNNING | workStatus.getState() == WorkInfo.State.ENQUEUED;
        }
        return running;
    }
   //Start My Worker
    public void startWorker() {
        PeriodicWorkRequest periodicWork = new PeriodicWorkRequest.Builder(UpdateLocationWorker.class, 15, TimeUnit.MINUTES)
                .addTag(TAG)
                .build();
        WorkManager.getInstance().enqueueUniquePeriodicWork(TAG, ExistingPeriodicWorkPolicy.REPLACE, periodicWork);

    }

    public  void getBatiment() {
        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<BatimentsModel> call = apiService.getbatiments(API_KEY);
            call.enqueue(new Callback<BatimentsModel>() {
                @Override
                public void onResponse(@NotNull Call<BatimentsModel> call, @NotNull Response<BatimentsModel> response) {
                    if(response.code() == 401 || response.code() == 500) {

                          Toast.makeText(getApplicationContext(), "Error get batiments", Toast.LENGTH_LONG).show();
                    } else {
                        //   Toast.makeText(getApplicationContext(), "Add Success", Toast.LENGTH_LONG).show();

                        /*  featureCollection.put("type", "FeatureCollection");
                          JSONObject properties = new JSONObject();
                          properties.put("name", "ESPG:4326");
                          JSONObject crs = new JSONObject();
                          crs.put("type", "name");
                          crs.put("properties", properties);
                          featureCollection.put("crs", crs);

                          JSONArray features = new JSONArray();
                          JSONObject feature = new JSONObject();
                          feature.put("type", "Feature");*/

                        for (int i = 0; i < response.body().getData().size(); i++) {

                            symbolManager = new SymbolManager(mapView, mapboxMap, mapboxMap.getStyle());

                            symbolManager.setIconAllowOverlap(true);
                            symbolManager.setTextAllowOverlap(true);

                            LatLng point = new LatLng(Double.parseDouble(response.body().getData().get(i).getLatitude()),Double.parseDouble(response.body().getData().get(i).getLongitude()));

                            symbol = symbolManager.create(new SymbolOptions()
                                    .withLatLng(point)
                                    .withIconImage("markerBatimentImage")
                                    .withIconSize(0.06f)
                                  //  .withTextField(response.body().getData().get(i).getNom())
                                    .withTextAnchor("top")
                                    .withTextJustify("center")
                                    .withTextSize(15f)

                                    .withSymbolSortKey(10.0f));




                            int idBatiment = response.body().getData().get(i).getId();
                            String nombreNiveau = response.body().getData().get(i).getNombreNiveaux().toString();
                            String nomBatiment = response.body().getData().get(i).getNom();


                            List<DataEtablissements> listetablissements = response.body().getData().get(i).getEtablissements();

                            symbolManager.addClickListener(symbol -> {

                                View dialogEtablissement = LayoutInflater.from(MapActivity.this).inflate(R.layout.dialog_etablissement, null, false);

                                RecyclerView etablissements = dialogEtablissement.findViewById(R.id.etablissements);

                                Button addEtablissement = dialogEtablissement.findViewById(R.id.add_etablissement);
                                ImageView closeButton = dialogEtablissement.findViewById(R.id.close_dialog);
                                ImageView logo_dialog = dialogEtablissement.findViewById(R.id.logo_dialog);
                                TextView textView9 = dialogEtablissement.findViewById(R.id.textView99);
                                if(Integer.parseInt(nombreNiveau) > 1 ) {
                                    textView9.setText(nomBatiment+" ("+nombreNiveau+" étages)");
                                } else {
                                    textView9.setText(nomBatiment+" ("+nombreNiveau+" étage)");
                                }

                                closeButton.setOnClickListener(v -> mat.dismiss());

                                addEtablissement.setOnClickListener(v -> {
                                    Intent intent = new Intent(MapActivity.this, NewBusinessActivity.class);
                                    intent.putExtra("idBatiment",String.valueOf(idBatiment));
                                    intent.putExtra("nombreNiveau",String.valueOf(nombreNiveau));
                                    startActivity(intent);
                                });



                                etablissements.setAdapter(new EtablissementAdapter(R.layout.item_etablissement, MapActivity.this, listetablissements));

                              mat =  new MaterialAlertDialogBuilder(MapActivity.this)
                                        .setView(dialogEtablissement)
                                        .show();
                            });

                           /* feature.put("properties", response.body().getData().get(i));
                            JSONObject geometry = new JSONObject();

                            JSONArray jsonArrayCoord = new JSONArray();

                            jsonArrayCoord.put(0,response.body().getData().get(i).getLongitude());
                            jsonArrayCoord.put(1,response.body().getData().get(i).getLatitude());
                            geometry.put("type", "Point");
                            geometry.put("coordinates", jsonArrayCoord);
                            feature.put("geometry", geometry);

                            features.put(feature);*/
                        }
                          /*  featureCollection.put("features", features);

                            GeoJsonSource geoJsonSource = new GeoJsonSource("geojson-source",featureCollection.toString());
                            style.addSource(geoJsonSource);
                            Toast.makeText(getApplicationContext(), geoJsonSource.toString(), Toast.LENGTH_LONG).show();*/

                    }



                }

                @Override
                public void onFailure(@NotNull Call<BatimentsModel> call, @NotNull Throwable t) {

                    // Log error here since request failed
                    Timber.tag("images").e(t.toString());
                    Log.e("error create", t.toString());
                     Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
              Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
        }


    }

    public void clickDialog(DataEtablissements dataEtablissements) {

        if(pref.getId().equals(String.valueOf(dataEtablissements.getIdCommercial()))) {
            Intent intent = new Intent(MapActivity.this, NewBusinessActivity.class);
            intent.putExtra("etablissement",  (new Gson()).toJson(dataEtablissements));
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Vous ne pouvez pas modifier cette fiche", Toast.LENGTH_LONG).show();
        }

    }

    public void openFiche(DataEtablissements dataEtablissements) {

        Intent intent = new Intent(MapActivity.this, DetailsBusinessActivity.class);
        intent.putExtra("etablissement",  (new Gson()).toJson(dataEtablissements));
        startActivity(intent);
    }

    public void deleteFiche(DataEtablissements dataEtablissements) {
        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<ResponseModel> call = apiService.deleteetablissements(API_KEY,pref.getToken(), dataEtablissements.getId());

            call.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(@NotNull Call<ResponseModel> call, @NotNull Response<ResponseModel> response) {
                    if(response.code() == 401 || response.code() == 500) {
                        Toast.makeText(getApplicationContext(), "Vous ne pouvez pas supprimer cette entreprise", Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(getApplicationContext(), "Suppression de l'entreprise reussie", Toast.LENGTH_LONG).show();
                    geojsonBatiment(Objects.requireNonNull(mapboxMap.getStyle()));

                }

                @Override
                public void onFailure(@NotNull Call<ResponseModel> call, @NotNull Throwable t) {
                    // Log error here since request failed
                    Timber.tag("logout").e(t.toString());
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }

    }

    public void resultSearch(String id) {

        search.closeSearch();
        getBatimentById(id);
    }



    private void geojsonBatiment(@NonNull Style style) {
        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<BatimentsModel> call = apiService.getbatiments(API_KEY);
            call.enqueue(new Callback<BatimentsModel>() {
                @Override
                public void onResponse(@NotNull Call<BatimentsModel> call, @NotNull Response<BatimentsModel> response) {
                    if(response.code() == 401 || response.code() == 500) {
                        Toast.makeText(getApplicationContext(), "Error get batiments", Toast.LENGTH_LONG).show();
                    } else {
                        //   Toast.makeText(getApplicationContext(), "Add Success", Toast.LENGTH_LONG).show();

                        try {
                            featureCollection.put("type", "FeatureCollection");
                            JSONObject properties = new JSONObject();
                            properties.put("name", "urn:ogc:def:crs:OGC:1.3:CRS84");
                            JSONObject crs = new JSONObject();
                            crs.put("type", "name");

                            crs.put("properties", properties);
                            featureCollection.put("crs", crs);


                        JSONArray features = new JSONArray();


                        for (int i = 0; i < response.body().getData().size(); i++) {
                            JSONObject feature = new JSONObject();

                                feature.put("type", "Feature");
                                JSONObject propertiesData = new JSONObject();
                            JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body().getData().get(i)));
                                propertiesData.put("batiment", jsonObject);
                                propertiesData.put("nom",response.body().getData().get(i).getNom());
                                propertiesData.put("codeBatiment",response.body().getData().get(i).getCodeBatiment());
                                propertiesData.put("id",response.body().getData().get(i).getId());
                                propertiesData.put("commune",response.body().getData().get(i).getCommune());
                                propertiesData.put("indication",response.body().getData().get(i).getIndication());
                                propertiesData.put("nombreNiveaux",response.body().getData().get(i).getNombreNiveaux());
                                propertiesData.put("quartier",response.body().getData().get(i).getQuartier());
                                propertiesData.put("ville",response.body().getData().get(i).getVille());
                                feature.put("properties", propertiesData);
                                JSONObject geometry = new JSONObject();
                                JSONArray jsonArrayCoord = new JSONArray();
                                jsonArrayCoord.put(0,Double.parseDouble(response.body().getData().get(i).getLongitude()) );
                                jsonArrayCoord.put(1,Double.parseDouble(response.body().getData().get(i).getLatitude()) );
                                geometry.put("type", "Point");
                                geometry.put("coordinates", jsonArrayCoord);
                                feature.put("geometry", geometry);

                                features.put(feature);

                            }

                            featureCollection.put("features", features);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        int[][] clusterLayers = new int[][] {
                                new int[] {150, ContextCompat.getColor(MapActivity.this, R.color.green)},
                                new int[] {20, ContextCompat.getColor(MapActivity.this, R.color.green)},
                                new int[] {0, ContextCompat.getColor(MapActivity.this, R.color.green)}
                        };

                       // FeatureCollection featureCollectionFromJson = FeatureCollection.fromJson(featureCollection.toString());

                        try {
                            style.setTransition(new TransitionOptions(0, 0, false));
                            style.addSource(clusterSource = createClusterSource(featureCollection.toString()));
                            style.addLayer(createSymbolLayer());
                            style.addLayer(createClusterLevelLayer(0, clusterLayers));
                            style.addLayer(createClusterLevelLayer(1, clusterLayers));
                            style.addLayer(createClusterLevelLayer(2, clusterLayers));
                            style.addLayer(createClusterTextLayer());
                          //  Log.d("SOURCES", featureCollection.toString());
                          //  Toast.makeText(getApplicationContext(), featureCollection.toString(), Toast.LENGTH_LONG).show();
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }


                        //   GeoJsonSource geoJsonSource = new GeoJsonSource("geojson-source",featureCollection.toString());

                      /*      style.addSource(new GeoJsonSource(GEOJSON_SOURCE_ID,featureCollection.toString(),new GeoJsonOptions().withCluster(true).withClusterMaxZoom(14).withClusterRadius(10)));
                         //   Toast.makeText(getApplicationContext(), featureCollection.toString(), Toast.LENGTH_LONG).show();

                        SymbolLayer unclustered = new SymbolLayer("unclustered-points", GEOJSON_SOURCE_ID);


                        unclustered.setProperties(
                                iconImage("markerBatimentImage"),
                                iconSize(
                                        division(
                                                get("mag"), literal(400.0f)
                                        )
                                )
                        );
                        unclustered.setFilter(has("mag"));
                        style.addLayer(unclustered);

                        int[][] layers = new int[][] {
                                new int[] {150, ContextCompat.getColor(MapActivity.this, R.color.green)},
                                new int[] {20, ContextCompat.getColor(MapActivity.this, R.color.green)},
                                new int[] {0, ContextCompat.getColor(MapActivity.this, R.color.green)}
                        };

                        for (int i = 0; i < layers.length; i++) {
//Add clusters' circles
                            CircleLayer circles = new CircleLayer("cluster-" + i, GEOJSON_SOURCE_ID);
                            circles.setProperties(
                                    circleColor(layers[i][1]),
                                    circleRadius(18f)
                            );


                            Expression pointCount = toNumber(get("point_count"));

// Add a filter to the cluster layer that hides the circles based on "point_count"
                            circles.setFilter(
                                    i == 0
                                            ? all(has("point_count"),
                                            gte(pointCount, literal(layers[i][0]))
                                    ) : all(has("point_count"),
                                            gte(pointCount, literal(layers[i][0])),
                                            lt(pointCount, literal(layers[i - 1][0]))
                                    )
                            );
                            style.addLayer(circles);
                    }

                        SymbolLayer count = new SymbolLayer("count", GEOJSON_SOURCE_ID);
                        count.setProperties(
                                textField(Expression.toString(get("point_count"))),
                                textSize(12f),
                                textColor(Color.WHITE),
                                textIgnorePlacement(true),
                                textAllowOverlap(true)
                        );
                        style.addLayer(count);
                        Log.d("STYLE",style.getJson());
                        Log.d("FEATURES",featureCollection.toString());*/




                }
                }

                @Override
                public void onFailure(@NotNull Call<BatimentsModel> call, @NotNull Throwable t) {

                    // Log error here since request failed
                    Timber.tag("images").e(t.toString());
                    Log.e("error create", t.toString());
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
        }
    }


    private void onClusterClick(Feature cluster, android.graphics.Point clickPoint) {

       if(cluster.id().equals("")) {
           DataBatiments batiments = new Gson().fromJson(cluster.properties().get("batiment"), DataBatiments.class);

           List<DataEtablissements> listetablissements = batiments.getEtablissements();


           View dialogEtablissement = LayoutInflater.from(MapActivity.this).inflate(R.layout.dialog_etablissement, null, false);

           RecyclerView etablissements = dialogEtablissement.findViewById(R.id.etablissements);

           Button addEtablissement = dialogEtablissement.findViewById(R.id.add_etablissement);
           ImageView closeButton = dialogEtablissement.findViewById(R.id.close_dialog);
           ImageView logo_dialog = dialogEtablissement.findViewById(R.id.logo_dialog);
           TextView textView9 = dialogEtablissement.findViewById(R.id.textView99);
           if(batiments.getNombreNiveaux() > 1) {

               textView9.setText(batiments.getNom()+" ("+batiments.getNombreNiveaux()+" étages)");
           } else {

               textView9.setText(batiments.getNom()+" ("+batiments.getNombreNiveaux()+" étage)");
           }
           closeButton.setOnClickListener(v -> mat.dismiss());

           addEtablissement.setOnClickListener(v -> {
               Intent intent = new Intent(MapActivity.this, NewBusinessActivity.class);
               intent.putExtra("idBatiment",String.valueOf(batiments.getId()));
               intent.putExtra("nombreNiveau",String.valueOf(batiments.getNombreNiveaux()));
               startActivity(intent);
           });



           etablissements.setAdapter(new EtablissementAdapter(R.layout.item_etablissement, MapActivity.this, listetablissements));

           mat =  new MaterialAlertDialogBuilder(MapActivity.this)
                   .setView(dialogEtablissement)
                   .show();
        } else {
            double nextZoomLevel = clusterSource.getClusterExpansionZoom(cluster);
            double zoomDelta = nextZoomLevel - mapboxMap.getCameraPosition().zoom;
            mapboxMap.animateCamera(CameraUpdateFactory.zoomBy(zoomDelta + CAMERA_ZOOM_DELTA, clickPoint));

        }




    }

    private GeoJsonSource createClusterSource(String features) throws URISyntaxException {
        return new GeoJsonSource(GEOJSON_SOURCE_ID,features, new GeoJsonOptions()
                .withCluster(true)
                .withClusterMaxZoom(16)
                .withClusterRadius(50)

        );
    }

    private SymbolLayer createSymbolLayer() {
        return new SymbolLayer(UNCLUSTERED_POINTS, GEOJSON_SOURCE_ID)
                .withProperties(
                        iconImage("markerBatimentImage"),
                        iconSize(
                              0.06f
                        ),
                        iconAllowOverlap(true),
                        symbolSortKey(10.0f)

                );

      /*  CircleLayer circles = new CircleLayer(UNCLUSTERED_POINTS , GEOJSON_SOURCE_ID);
        circles.setProperties(
                circleColor(ContextCompat.getColor(MapActivity.this, R.color.mapbox_blue)),
                circleRadius(10f),
                textField("Manger"),
                textAnchor(Property.TEXT_ANCHOR_TOP),
                textJustify(Property.TEXT_JUSTIFY_CENTER)
                );
        circles.setFilter(has("mag"));*/

    }

    private CircleLayer createClusterLevelLayer(int level, int[][] layerColors) {
        CircleLayer circles = new CircleLayer("cluster-" + level, GEOJSON_SOURCE_ID);
        circles.setProperties(
                circleColor(layerColors[level][1]),
                circleRadius(18f)
        );



        Expression pointCount = toNumber(get("point_count"));
        circles.setFilter(
                level == 0
                        ? all(has("point_count"),
                        gte(pointCount, literal(layerColors[level][0]))
                ) : all(has("point_count"),
                        gte(pointCount, literal(layerColors[level][0])),
                        lt(pointCount, literal(layerColors[level - 1][0]))
                )
        );
        return circles;
    }

    private SymbolLayer createClusterTextLayer() {
        return new SymbolLayer("property", GEOJSON_SOURCE_ID)
                .withProperties(
                        textField(concat(get("point_count"))),
                        textSize(12f),
                        textColor(Color.WHITE),
                        textIgnorePlacement(true),
                        textAllowOverlap(true)
                );
    }




    public void getCategories() {
        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<Categories> call = apiService.getCategories(API_KEY);
            call.enqueue(new Callback<Categories>() {
                @Override
                public void onResponse(@NotNull Call<Categories> call, @NotNull Response<Categories> response) {
                    Timber.tag("categories").e(response.toString());

//                    chipsLayout.setAdapter(new CategoriesAdapter(R.layout.item_chip,MapActivity.this,response.body().getData()));


                }

                @Override
                public void onFailure(@NotNull Call<Categories> call, @NotNull Throwable t) {
                    // Log error here since request failed
                    Timber.tag("logout").e(t.toString());
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {
    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        searchApi(search.getText());
    }

    @Override
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode) {
            case MaterialSearchBar.BUTTON_NAVIGATION:
                drawer.openDrawer(GravityCompat.START);
                break;
            case MaterialSearchBar.BUTTON_SPEECH:
                startVoiceRecognitionActivity();
                break;
            case MaterialSearchBar.BUTTON_BACK:
                search.closeSearch();
                break;
        }
    }

    /**
     * Fire an intent to start the voice recognition activity.
     */
    private void startVoiceRecognitionActivity()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Recherche de la voix...");
        startActivityForResult(intent, REQUEST_CODE);
    }
    /**
     * Handle the results from the voice recognition activity.
     */
    @
            Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK)
        {
            // Populate the wordsList with the String values the recognition engine thought it heard
            final ArrayList < String > matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (!matches.isEmpty())
            {
                String Query = matches.get(0);
                search.openSearch();
                search.setText(Query);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void startSearch(String query) {


    }
    
    public void searchApi(String query) {
search.clearSuggestions();
searchResult.clear();

        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<SearchEtablissement> call = apiService.searchetablissement(API_KEY,query);
            call.enqueue(new Callback<SearchEtablissement>() {
                @Override
                public void onResponse(@NotNull Call<SearchEtablissement> call, @NotNull Response<SearchEtablissement> response) {
                    if(response.code() == 401 || response.code() == 500) {
                        Toast.makeText(getApplicationContext(), "Error Search", Toast.LENGTH_LONG).show();
                    } else {

                        searchAdapter.setSuggestions(response.body().getData());
                        search.setCustomSuggestionAdapter(searchAdapter);
                        search.showSuggestionsList();
                    }


                }

                @Override
                public void onFailure(@NotNull Call<SearchEtablissement> call, @NotNull Throwable t) {
                    // Log error here since request failed
                    Timber.tag("etablissements").e(t.toString());
                    Log.e("error create", t.toString());
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }

    }


    private void sendLocation(Location location) {
        String lon = String.valueOf(location != null ? location.getLongitude() : 0);
        String lat = String.valueOf(location != null ? location.getLatitude() : 0);
        trackingRepository = new TrackingRepository(mDb);

        DataTracking dataTracking = new DataTracking(lon,lat);


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

    public void addZone( @NotNull Style style,int idZone) {

        if (Function.isNetworkAvailable(getApplicationContext())) {

            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<Zones> call = apiService.getzone(API_KEY,"Bearer "+pref.getToken(),idZone);
            call.enqueue(new Callback<Zones>() {
                @Override
                public void onResponse(@NotNull Call<Zones> call, @NotNull Response<Zones> response) {
                    if(response.code() == 401 || response.code() == 500) {
                          Toast.makeText(getApplicationContext(), "Error get zone", Toast.LENGTH_LONG).show();
                    } else {
                        try {
                            GeoJsonSource geoJsonSource = new GeoJsonSource("zone-geojson",new URI(IMAGEURL+response.body().getData().getLimite()));

                            if(style.getSource("zone-geojson") != null) {
                                style.removeSource("zone-geojson");
                                style.removeLayer("fill-layer");
                            } else {
                                style.addSource(geoJsonSource);

                                FillLayer fillLayer = new FillLayer("fill-layer","zone-geojson");
                                fillLayer.setProperties(
                                        fillColor(ContextCompat.getColor(getApplicationContext(), R.color.green)),
                                        fillOpacity(0.5f)
                                );


                                style.addLayer(fillLayer);

                                CameraPosition cam = mapboxMap.getCameraPosition();
                                mapboxMap.animateCamera(
                                        CameraUpdateFactory.newCameraPosition(
                                                new CameraPosition.Builder()
                                                        .zoom(15)
                                                        .target(new LatLng(cam.target))
                                                        .build()

                                        )
                                );

                            }

                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }

                    }



                }

                @Override
                public void onFailure(@NotNull Call<Zones> call, @NotNull Throwable t) {
                    Log.e("error create", t.toString());
                    // Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
             Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
        }
    }

    public void getUsers() {
        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<UserModel> call = apiService.getUser( API_KEY,"Bearer "+pref.getToken());
            call.enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(@NotNull Call<UserModel> call, @NotNull Response<UserModel> response) {
                    if(response.code() == 401) {
                        Toast.makeText(getApplicationContext(), "Reconnectez Vous", Toast.LENGTH_LONG).show();
                        logout();
                    } else {
                        pref.setEmail(response.body().getData().getUser(). getEmail());
                        pref.setName(response.body().getData().getUser().getName());
                        pref.setPhone(response.body().getData().getUser().getPhone().toString());
                        pref.setId(response.body().getData().getUser().toString());
                        if(response.body().getData().getUser().getCommercial().getImageProfil() != null) {
                            pref.setProfileimage(response.body().getData().getUser().getCommercial().getImageProfil());
                        } else {
                            pref.setProfileimage("");
                        }
                        pref.setActive(response.body().getData().getUser().getCommercial().getActif().toString());
                        pref.setRoleid(response.body().getData().getUser().getRole().toString());
                        pref.setZoneid(response.body().getData().getUser().getCommercial().getIdZone().toString());

                        if(!response.body().getData().getUser().getCommercial().getActif().toString().equals("1")) {
                            Toast.makeText(getApplicationContext(), "Votre compte à été désactivé", Toast.LENGTH_LONG).show();
                            logout();
                        }
                        //   Toast.makeText(getApplicationContext(), response.body().getData().getUser().getRole().toString(), Toast.LENGTH_LONG).show();

                    }

                }

                @Override
                public void onFailure(@NotNull Call<UserModel> call, @NotNull Throwable t) {
                    // Log error here since request failed
                    Timber.tag("users").e(t.toString());
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onMapClick(@NonNull LatLng point) {

        PointF pointf = mapboxMap.getProjection().toScreenLocation(point);

        List<Feature> features = mapboxMap.queryRenderedFeatures(pointf, "cluster-0", "cluster-1", "cluster-2",UNCLUSTERED_POINTS);
        if (!features.isEmpty()) {
            onClusterClick(features.get(0), new android.graphics.Point((int) pointf.x, (int) pointf.y));
        }
        return true;

      /*  RectF rectF = new RectF(pointf.x - 10, pointf.y - 10, pointf.x + 10, pointf.y + 10);
        List<Feature> featureList = mapboxMap.queryRenderedFeatures(rectF, UNCLUSTERED_POINTS);
        if (featureList.size() > 0) {
            for (Feature feature : featureList) {
                DataBatiments batiments = new Gson().fromJson(feature.properties().get("batiment"), DataBatiments.class);

                List<DataEtablissements> listetablissements = batiments.getEtablissements();


                View dialogEtablissement = LayoutInflater.from(MapActivity.this).inflate(R.layout.dialog_etablissement, null, false);

                RecyclerView etablissements = dialogEtablissement.findViewById(R.id.etablissements);

                Button addEtablissement = dialogEtablissement.findViewById(R.id.add_etablissement);
                ImageView closeButton = dialogEtablissement.findViewById(R.id.close_dialog);
                ImageView logo_dialog = dialogEtablissement.findViewById(R.id.logo_dialog);
                TextView textView9 = dialogEtablissement.findViewById(R.id.textView99);
                textView9.setText(batiments.getNom()+" ("+batiments.getNombreNiveaux()+" étages)");
                closeButton.setOnClickListener(v -> mat.dismiss());

                addEtablissement.setOnClickListener(v -> {
                    Intent intent = new Intent(MapActivity.this, NewBusinessActivity.class);
                    intent.putExtra("idBatiment",String.valueOf(batiments.getId()));
                    intent.putExtra("nombreNiveau",String.valueOf(batiments.getNombreNiveaux()));
                    startActivity(intent);
                });



                etablissements.setAdapter(new EtablissementAdapter(R.layout.item_etablissement, MapActivity.this, listetablissements));

                mat =  new MaterialAlertDialogBuilder(MapActivity.this)
                        .setView(dialogEtablissement)
                        .show();
            }
            return true;
        }*/
    //    return false;
    }
}