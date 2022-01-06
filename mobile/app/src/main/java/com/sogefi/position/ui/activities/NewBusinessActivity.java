package com.sogefi.position.ui.activities;

import static com.sogefi.position.utils.Constants.API_KEY;
import static com.sogefi.position.utils.Constants.IMAGEURL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.gson.Gson;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.sogefi.position.R;
import com.sogefi.position.api.APIClient;
import com.sogefi.position.api.ApiInterface;
import com.sogefi.position.models.Batiments;
import com.sogefi.position.models.Categories;
import com.sogefi.position.models.Etablissements;
import com.sogefi.position.models.SearchSousCategories;
import com.sogefi.position.models.SousCategory;
import com.sogefi.position.models.data.DataCategories;
import com.sogefi.position.models.data.DataEtablissements;
import com.sogefi.position.ui.activities.adapters.SearchSousCategoriesAdapter;
import com.sogefi.position.ui.activities.adapters.SpinnerAdapter;
import com.sogefi.position.utils.Function;
import com.sogefi.position.utils.PreferenceManager;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class NewBusinessActivity extends AppCompatActivity {
    EditText name, description,etage,searchcategorie,autres;
    Button next,back;
    ImageView  backbtn,cover,add1,cover2,add2;
   // Spinner sous_categories, category,sous_categories2;
    TextView category,sous_categories;
    ScrollView scrollView;
    ProgressBar progress;
    ProgressBar progressBar;
    PreferenceManager pref;
    Categories categories;
    LinearLayout llbuttonadd,llamenities2;

    int idSousCategorie,idSousCategorie2;
    File image,image2;
    RecyclerView recyclerViewSearch;
    private RecyclerView.LayoutManager mLayoutManager;
    DataEtablissements dataEtablissements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_business);

        pref = new PreferenceManager(this);

        mLayoutManager = new LinearLayoutManager(this);

        String idBatiment = getIntent().getStringExtra("idBatiment");
        String nombreNiveau = getIntent().getStringExtra("nombreNiveau");


        String etablissement = getIntent().getStringExtra("etablissement");

        dataEtablissements = new Gson().fromJson(etablissement, DataEtablissements.class);


        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        next = findViewById(R.id.next);
        category = findViewById(R.id.category);
        sous_categories = findViewById(R.id.sous_categories);
        searchcategorie = findViewById(R.id.searchcategorie);
        autres = findViewById(R.id.autres);
     //   sous_categories2 = findViewById(R.id.sous_categories2);
        etage = findViewById(R.id.etage);
        backbtn = findViewById(R.id.back_btn);
        back = findViewById(R.id.back);
        scrollView = findViewById(R.id.scrollView);
        cover = findViewById(R.id.cover);
        add1 = findViewById(R.id.add1);
        llbuttonadd = findViewById(R.id.llbuttonadd);
        llamenities2 = findViewById(R.id.llamenities2);

        cover2 = findViewById(R.id.cover2);
        add2 = findViewById(R.id.add2);
        progress = findViewById(R.id.progressBar);
        recyclerViewSearch =findViewById(R.id.recyclerViewSearch);

        progressBar = findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.GONE);
        llamenities2.setVisibility(View.GONE);
        recyclerViewSearch.setVisibility(View.GONE);


        if(dataEtablissements != null) {

            name.setText(dataEtablissements.getNom());
            etage.setText(dataEtablissements.getEtage().toString());
            category.setText(dataEtablissements.getSousCategories().get(0).getCategorie().getNom());
            sous_categories.setText(dataEtablissements.getSousCategories().get(0).getNom());
            autres.setText(dataEtablissements.getAutres());
            idSousCategorie = dataEtablissements.getSousCategories().get(0).getId();
            add1.setVisibility(View.GONE);
            add2.setVisibility(View.GONE);
            Picasso.get().load(IMAGEURL+dataEtablissements.getCover()).into(cover);
            Picasso.get().load(IMAGEURL+dataEtablissements.getImages().get(0).getImageUrl()).into(cover2);




        }

        searchcategorie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(searchcategorie.getText().length() >= 3) {
                    searchCategorie(searchcategorie.getText().toString());
                } if(searchcategorie.getText().length() == 0) {
                    recyclerViewSearch.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });



        progress.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);

        llbuttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamenities2.setVisibility(View.VISIBLE);
            }
        });

        cover.setOnClickListener(v -> chooseImage(600));

        cover2.setOnClickListener(v -> chooseImage(601));

        next.setOnClickListener(v -> {
            String getname = name.getText().toString();
            String getetage = etage.getText().toString();
            String getsouscategory =  sous_categories.getText().toString();
            String getautres =  autres.getText().toString();
            String getcategory =  category.getText().toString();
            String getdescription = description.getText().toString();
            if(TextUtils.isEmpty(getname)){
                Toast.makeText(this, "Entrez le nom de l'entreprise", Toast.LENGTH_SHORT).show();
            }
            else if(getetage.isEmpty()){
                Toast.makeText(this, "Selectionner un etage", Toast.LENGTH_SHORT).show();
            }
            else if (getautres.isEmpty()) {
                Toast.makeText(this, "Ajouter des mots clés", Toast.LENGTH_SHORT).show();
            }
            else {
                uploadData(idBatiment,getname,String.valueOf(idSousCategorie),getautres,getetage,getdescription);
            }
        });

        backbtn.setOnClickListener(v -> finish());

        back.setOnClickListener(v -> finish());


      //  getCategory();


    }

    public void searchCategorie(String query)  {

        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<SearchSousCategories> call = apiService.searchsouscategories(API_KEY,query);
            call.enqueue(new Callback<SearchSousCategories>() {
                @Override
                public void onResponse(@NotNull Call<SearchSousCategories> call, @NotNull Response<SearchSousCategories> response) {
                    if(response.code() == 401 || response.code() == 500) {
                        Toast.makeText(getApplicationContext(), "Error Search", Toast.LENGTH_LONG).show();
                    } else {
                        SearchSousCategoriesAdapter searchSousCategoriesAdapter = new SearchSousCategoriesAdapter(R.layout.item_spinner,NewBusinessActivity.this,response.body().getData());

                        if(response.body().getData().size() > 0 ) {
                            recyclerViewSearch.setHasFixedSize(true);

                            recyclerViewSearch.setLayoutManager(mLayoutManager);
                            recyclerViewSearch.setAdapter(searchSousCategoriesAdapter);
                            recyclerViewSearch.setVisibility(View.VISIBLE);

                        } else {
                            recyclerViewSearch.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Pas de Résultats", Toast.LENGTH_LONG).show();
                        }


                    }


                }

                @Override
                public void onFailure(@NotNull Call<SearchSousCategories> call, @NotNull Throwable t) {
                    // Log error here since request failed
                    progressBar.setVisibility(View.GONE);
                    Timber.tag("etablissements").e(t.toString());
                    Log.e("error create", t.toString());
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }
    }

    public void populateTv(String categorie,String souscategorie,int id) {
        category.setText(categorie);
        sous_categories.setText(souscategorie);
        idSousCategorie = id;
        searchcategorie.setText("");
        recyclerViewSearch.setVisibility(View.GONE);
    }



    private void uploadData(String idBatiment, String nom, String idSousCategorie,String autres, String etage,String description) {

        progressBar.setVisibility(View.VISIBLE);

        if(image != null && image2 != null) {

            progressBar.setVisibility(View.GONE);
            Intent intent = new Intent(NewBusinessActivity.this, NewBusiness2Activity.class);
            intent.putExtra("idBatiment", getIntent().getStringExtra("idBatiment"));
            intent.putExtra("nomBatiment", getIntent().getStringExtra("nomBatiment"));
            intent.putExtra("nombreNiveaux", getIntent().getStringExtra("nombreNiveaux"));
            intent.putExtra("codeBatiment", getIntent().getStringExtra("codeBatiment"));
            intent.putExtra("longitude", getIntent().getStringExtra("longitude"));
            intent.putExtra("latitude", getIntent().getStringExtra("latitude"));
            intent.putExtra("indication", getIntent().getStringExtra("indication"));
            intent.putExtra("rue", getIntent().getStringExtra("rue"));
            intent.putExtra("ville", getIntent().getStringExtra("ville"));
            intent.putExtra("commune", getIntent().getStringExtra("commune"));
            intent.putExtra("quartier", getIntent().getStringExtra("quartier"));
            intent.putExtra("imageBatiment", (File) getIntent().getExtras().get("imageBatiment"));

            intent.putExtra("nomEtablissement", nom);
            intent.putExtra("idSousCategorie", idSousCategorie);
            intent.putExtra("autres", autres);
            intent.putExtra("etage", etage);
            intent.putExtra("description", description);
            intent.putExtra("coverEtablissement", image);
            intent.putExtra("imageEtablissement", image2);
            if(dataEtablissements != null) {
                intent.putExtra("etablissement",  (new Gson()).toJson(dataEtablissements));
                intent.putExtra("imageN",  "non");
            } else {
                intent.putExtra("imageN",  "oui");
            }
            startActivity(intent);

        } else if(image == null && dataEtablissements != null) {

            progressBar.setVisibility(View.GONE);
            Intent intent = new Intent(NewBusinessActivity.this, NewBusiness2Activity.class);
            intent.putExtra("idBatiment", getIntent().getStringExtra("idBatiment"));
            intent.putExtra("nomBatiment", getIntent().getStringExtra("nomBatiment"));
            intent.putExtra("nombreNiveaux", getIntent().getStringExtra("nombreNiveaux"));
            intent.putExtra("codeBatiment", getIntent().getStringExtra("codeBatiment"));
            intent.putExtra("longitude", getIntent().getStringExtra("longitude"));
            intent.putExtra("latitude", getIntent().getStringExtra("latitude"));
            intent.putExtra("indication", getIntent().getStringExtra("indication"));
            intent.putExtra("rue", getIntent().getStringExtra("rue"));
            intent.putExtra("ville", getIntent().getStringExtra("ville"));
            intent.putExtra("commune", getIntent().getStringExtra("commune"));
            intent.putExtra("quartier", getIntent().getStringExtra("quartier"));
            intent.putExtra("imageBatiment", (File) getIntent().getExtras().get("imageBatiment"));

            intent.putExtra("nomEtablissement", nom);
            intent.putExtra("idSousCategorie", idSousCategorie);
            intent.putExtra("autres", autres);
            intent.putExtra("etage", etage);
            intent.putExtra("description", description);
            intent.putExtra("coverEtablissement", image);
            intent.putExtra("imageEtablissement", image2);

                intent.putExtra("etablissement",  (new Gson()).toJson(dataEtablissements));
            intent.putExtra("imageN",  "oui");

            startActivity(intent);

        } else if(image == null && image2 == null){
            progressBar.setVisibility(View.GONE);
            Intent intent = new Intent(NewBusinessActivity.this, NewBusiness2Activity.class);
            intent.putExtra("idBatiment", getIntent().getStringExtra("idBatiment"));
            intent.putExtra("nomBatiment", getIntent().getStringExtra("nomBatiment"));
            intent.putExtra("nombreNiveaux", getIntent().getStringExtra("nombreNiveaux"));
            intent.putExtra("codeBatiment", getIntent().getStringExtra("codeBatiment"));
            intent.putExtra("longitude", getIntent().getStringExtra("longitude"));
            intent.putExtra("latitude", getIntent().getStringExtra("latitude"));
            intent.putExtra("indication", getIntent().getStringExtra("indication"));
            intent.putExtra("rue", getIntent().getStringExtra("rue"));
            intent.putExtra("ville", getIntent().getStringExtra("ville"));
            intent.putExtra("commune", getIntent().getStringExtra("commune"));
            intent.putExtra("quartier", getIntent().getStringExtra("quartier"));
            intent.putExtra("imageBatiment", (File) getIntent().getExtras().get("imageBatiment"));

            intent.putExtra("nomEtablissement", nom);
            intent.putExtra("idSousCategorie", idSousCategorie);
            intent.putExtra("autres", autres);
            intent.putExtra("etage", etage);
            intent.putExtra("description", description);
            intent.putExtra("coverEtablissement", image);
            intent.putExtra("imageEtablissement", image2);

            intent.putExtra("etablissement",  (new Gson()).toJson(dataEtablissements));
            intent.putExtra("imageN",  "oui");

            startActivity(intent);
        }   else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Selectionnez une image", Toast.LENGTH_LONG).show();
        }

    }

    public void chooseImage(Integer code) {
        ImagePicker.with(this)
                .crop()
                .compress(5096)
                .maxResultSize(1080,1080)
                .start(code);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 600) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                Uri uri = data.getData();
                image = new File(uri.getPath());
                add1.setVisibility(View.GONE);
                Picasso.get().load(uri).into(cover);

            }
        }
        if (requestCode == 601) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                Uri uri = data.getData();
                image2 = new File(uri.getPath());
                add2.setVisibility(View.GONE);
                Picasso.get().load(uri).into(cover2);

            }
        }

    }
}