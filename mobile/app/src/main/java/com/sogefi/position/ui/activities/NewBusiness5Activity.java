package com.sogefi.position.ui.activities;

import static com.sogefi.position.utils.Constants.API_KEY;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.sogefi.position.R;
import com.sogefi.position.api.APIClient;
import com.sogefi.position.api.ApiInterface;
import com.sogefi.position.models.Batiments;
import com.sogefi.position.models.Nominatim;
import com.sogefi.position.utils.Function;
import com.sogefi.position.utils.PreferenceManager;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class NewBusiness5Activity extends AppCompatActivity {

    EditText name5,niveaux5,indication5,quartier5;
    LinearLayout lladdress5;
    ImageView image5,backbtn5,add;
    PreferenceManager pref;
    ScrollView scrollView5;
    ProgressBar progressBar5;
    Button next;
    TextView address;
    File image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_business5);

        pref = new PreferenceManager(this);

        String adresse = getIntent().getStringExtra("adresse");

        address = findViewById(R.id.address);

        if(adresse != null) {
            address.setText(adresse);
        }

        name5 = findViewById(R.id.name5);
        niveaux5 = findViewById(R.id.niveaux5);
        indication5 = findViewById(R.id.indication5);
        quartier5 = findViewById(R.id.quartier5);
        lladdress5 = findViewById(R.id.lladdress5);
        next = findViewById(R.id.next5);
        image5 = findViewById(R.id.image5);
        scrollView5 = findViewById(R.id.scrollView5);
        progressBar5 = findViewById(R.id.progressBar5);
        backbtn5 = findViewById(R.id.back_btn5);
        add = findViewById(R.id.add);

        progressBar5.setVisibility(View.GONE);
        scrollView5.setVisibility(View.VISIBLE);

        lladdress5.setOnClickListener(v -> {
            Intent intent = new Intent(NewBusiness5Activity.this, PicklocationActivity.class);
            startActivity(intent);
            finish();
        });

        image5.setOnClickListener(v -> chooseImage());

        backbtn5.setOnClickListener(v -> finish());

        next.setOnClickListener(v -> {
            getCoordinatesBatiment();
        });
    }

    public void getCoordinatesBatiment() {
        if(TextUtils.isEmpty(name5.getText().toString())){
            Toast.makeText(this, "Entrez le nom du batiment", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(niveaux5.getText().toString())){
            Toast.makeText(this, "Entrez le nombre de niveaux", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(address.getText().toString())){
            Toast.makeText(this, "Selectionnez une adresse", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(quartier5.getText().toString())){
            Toast.makeText(this, "Entrez le quartier du batiment", Toast.LENGTH_SHORT).show();
        } else {
            progressBar5.setVisibility(View.VISIBLE);

            String[] coordinates = address.getText().toString().split(",");
            String longitude = coordinates[0];
            String latitude = coordinates[1];
            searchPoint(longitude,latitude);
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
                    String rue = response.body().getAddress().getRoad();
                    String ville = response.body().getAddress().getCity();
                    String commune = response.body().getAddress().getCityDistrict() != null ? response.body().getAddress().getCityDistrict() : ville;
                    String quartierCapital = quartier5.getText().toString().toUpperCase();
                    Random random = new Random();
                    int y = random.nextInt(100000);
                    String codeBatiment = "BATIMENT_"+quartierCapital+"_"+String.valueOf(y);
                    saveBatiment(name5.getText().toString(),niveaux5.getText().toString(),codeBatiment,lon,lat,indication5.getText().toString(),rue,ville,commune,quartier5.getText().toString());
                }

                @Override
                public void onFailure(@NotNull Call<Nominatim> call, @NotNull Throwable t) {
                    progressBar5.setVisibility(View.GONE);
                    // Log error here since request failed
                    Timber.tag("main2").e(t.toString());
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            progressBar5.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }


    }

    public void saveBatiment(String nom, String nombreNiveaux, String codeBatiment, String longitude, String latitude, String indication, String rue, String ville, String commune, String quartier) {


        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), image);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("nom", nom)
                .addFormDataPart("nombreNiveaux", nombreNiveaux)
                .addFormDataPart("codeBatiment", codeBatiment)
                .addFormDataPart("longitude", longitude)
                .addFormDataPart("latitude", latitude)
                .addFormDataPart("indication", indication)
                .addFormDataPart("rue", rue)
                .addFormDataPart("ville", ville)
                .addFormDataPart("commune", commune)
                .addFormDataPart("quartier", quartier)
                .addFormDataPart("file", image.getName(), requestFile)
                .build();


        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<Batiments> call = apiService.addbatiments(API_KEY,"Bearer "+pref.getToken(),requestBody);
            call.enqueue(new Callback<Batiments>() {
                @Override
                public void onResponse(@NotNull Call<Batiments> call, @NotNull Response<Batiments> response) {
                    if(response.code() == 401 || response.code() == 500) {
                        progressBar5.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Error Create", Toast.LENGTH_LONG).show();
                    } else {
                        int idBatiment = response.body().getData().getId();
                        String nombreNiveau = response.body().getData().getNombreNiveaux();
                        progressBar5.setVisibility(View.GONE);
                        Intent intent = new Intent(NewBusiness5Activity.this, NewBusinessActivity.class);
                        intent.putExtra("idBatiment",String.valueOf(idBatiment));
                        intent.putExtra("nombreNiveau",String.valueOf(nombreNiveau));
                        startActivity(intent);
                        finish();
                    }

                    progressBar5.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(@NotNull Call<Batiments> call, @NotNull Throwable t) {
                    // Log error here since request failed
                    progressBar5.setVisibility(View.GONE);
                    Timber.tag("batiments").e(t.toString());
                    Log.e("error create", t.toString());
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            progressBar5.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }
    }

    public void chooseImage() {
        ImagePicker.with(this)
                .crop()
                .compress(10000)
                .start(201);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 201) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                Uri uri = data.getData();
                image = new File(uri.getPath());
                add.setVisibility(View.GONE);
                Picasso.get().load(uri).into(image5);

            }
        }

    }
}