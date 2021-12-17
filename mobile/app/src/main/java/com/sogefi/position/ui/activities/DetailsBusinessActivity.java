package com.sogefi.position.ui.activities;

import static com.sogefi.position.utils.Constants.API_KEY;
import static com.sogefi.position.utils.Constants.IMAGEURL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.sogefi.position.R;
import com.sogefi.position.api.APIClient;
import com.sogefi.position.api.ApiInterface;
import com.sogefi.position.models.Batiments;
import com.sogefi.position.models.Etablissements;
import com.sogefi.position.models.data.DataEtablissements;
import com.sogefi.position.models.data.DataHoraires;
import com.sogefi.position.ui.activities.adapters.EtablissementAdapter;
import com.sogefi.position.ui.activities.adapters.HorairesAdapter;
import com.sogefi.position.utils.Function;
import com.sogefi.position.utils.PreferenceManager;

import org.imaginativeworld.whynotimagecarousel.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class DetailsBusinessActivity extends AppCompatActivity {

    DataEtablissements dataEtablissements;
    ImageCarousel imageCarousel;
    TextView nomEntreprise,categorieEntreprise,adresse_entreprise,contact_entreprise,website;
    RecyclerView recyclerHoraire;
    LinearLayout llPhone,llShare,llrevoir;
    List<CarouselItem> list = new ArrayList<>();
    List<DataHoraires> listHoraires = new ArrayList<>();
    RelativeLayout relativeSite;
    PreferenceManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_business);

        pref = new PreferenceManager(this);

        String etablissement = getIntent().getStringExtra("etablissement");

        dataEtablissements = new Gson().fromJson(etablissement, DataEtablissements.class);

        imageCarousel = findViewById(R.id.carrousel);
        nomEntreprise = findViewById(R.id.nom_entreprise);
        categorieEntreprise = findViewById(R.id.categorie_entreprise);
        adresse_entreprise = findViewById(R.id.ville_entreprise);
        contact_entreprise = findViewById(R.id.contact_entreprise);
        website = findViewById(R.id.website);
        recyclerHoraire = findViewById(R.id.recycle_horaire);
       // llPhone = findViewById(R.id.llPhone_entreprise);
        llShare = findViewById(R.id.llShare_entreprise);
        relativeSite = findViewById(R.id.relativeSite);
        llrevoir = findViewById(R.id.llrevoir_entreprise);

getBatimentById(String.valueOf(dataEtablissements.getIdBatiment()));

llShare.setOnClickListener(v -> sharePage(String.valueOf(dataEtablissements.getId())));

llrevoir.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        updateEtablissement();
    }
});


    }


    public void getBatimentById(String id){
        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<Batiments> call = apiService.getbatimentsbyid(API_KEY,Integer.parseInt(id));

            call.enqueue(new Callback<Batiments>() {
                @Override
                public void onResponse(@NotNull Call<Batiments> call, @NotNull Response<Batiments> response) {


      CarouselItem carouselItem = new CarouselItem(IMAGEURL+response.body().getData().getImage(),dataEtablissements.getNom());
                    CarouselItem carouselItem1 = new CarouselItem(IMAGEURL+dataEtablissements.getCover(),dataEtablissements.getNom());
                    CarouselItem carouselItem2 = new CarouselItem(IMAGEURL+dataEtablissements.getImages().get(0).getImageUrl(),dataEtablissements.getNom());
                        list.add(carouselItem);
                    list.add(carouselItem1);
                    list.add(carouselItem2);

                    imageCarousel.addData(list);

                    nomEntreprise.setText(dataEtablissements.getNom());
                    categorieEntreprise.setText(dataEtablissements.getSousCategories().get(0).getNom());
                    adresse_entreprise.setText(response.body().getData().getQuartier()+","+response.body().getData().getRue());
                    contact_entreprise.setText(dataEtablissements.getTelephones().get(0).getNumero()+", "+dataEtablissements.getTelephones().get(1).getNumero());
                    if(dataEtablissements.getSiteInternet() != null) {
                        website.setText(dataEtablissements.getSiteInternet());
                    } else {
                        relativeSite.setVisibility(View.GONE);
                    }



                    recyclerHoraire.setAdapter(new HorairesAdapter(R.layout.item_horaire, DetailsBusinessActivity.this, dataEtablissements.getHoraires()));
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

    public void sharePage(String idEtablissement) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Position");
            String shareMessage = "Retrouvez mon entreprise sur la plateforme Position en suivant le lien : \n https://position.cm/home?etablissements="+idEtablissement+",16";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, getString(R.string.chooseApp)));
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), getString(R.string.shareError), Toast.LENGTH_LONG).show();
        }
    }


    public void updateEtablissement() {
        RequestBody requestBody;
        requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("revoir", "1")
                .addFormDataPart("_method", "put")
                .build();



        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<Etablissements> call = apiService.updateetablissements(API_KEY,"Bearer "+pref.getToken(),requestBody,dataEtablissements.getId());
            call.enqueue(new Callback<Etablissements>() {
                @Override
                public void onResponse(@NotNull Call<Etablissements> call, @NotNull Response<Etablissements> response) {
                    if(response.code() == 401 || response.code() == 500) {
                        Toast.makeText(getApplicationContext(), "Error Update", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Notification envoyée", Toast.LENGTH_LONG).show();
                    }


                }

                @Override
                public void onFailure(@NotNull Call<Etablissements> call, @NotNull Throwable t) {
                    // Log error here since request failed
                    Timber.tag("etablissements").e(t.toString());
                    Log.e("error update", t.toString());
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }
    }
}