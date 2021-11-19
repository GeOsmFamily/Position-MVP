package com.sogefi.position.ui.activities;

import static com.sogefi.position.utils.Constants.API_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.sogefi.position.R;
import com.sogefi.position.api.APIClient;
import com.sogefi.position.api.ApiInterface;
import com.sogefi.position.models.Batiments;
import com.sogefi.position.models.Categories;
import com.sogefi.position.models.Etablissements;
import com.sogefi.position.models.SousCategory;
import com.sogefi.position.models.data.DataCategories;
import com.sogefi.position.utils.Function;
import com.sogefi.position.utils.PreferenceManager;
import com.squareup.picasso.Picasso;

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

public class NewBusinessActivity extends AppCompatActivity {
    EditText name, description,etage;
    Button next;
    ImageView  backbtn,cover,add1;
    Spinner sous_categories, category;
    ScrollView scrollView;
    ProgressBar progress;
    ProgressBar progressBar;
    PreferenceManager pref;
    Categories categories;

    int idSousCategorie;
    File image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_business);

        pref = new PreferenceManager(this);

        String idBatiment = getIntent().getStringExtra("idBatiment");
        String nombreNiveau = getIntent().getStringExtra("nombreNiveau");


        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        next = findViewById(R.id.next);
        category = findViewById(R.id.category);
        sous_categories = findViewById(R.id.sous_categories);
        etage = findViewById(R.id.etage);
        backbtn = findViewById(R.id.back_btn);
        progress = findViewById(R.id.progressBar);
        scrollView = findViewById(R.id.scrollView);
        cover = findViewById(R.id.cover);
        add1 = findViewById(R.id.add1);

        progressBar = findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.GONE);



        progress.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        cover.setOnClickListener(v -> chooseImage());

        next.setOnClickListener(v -> {
            String getname = name.getText().toString();
            String getetage = etage.getText().toString();
            String getsouscategory = (String) sous_categories.getSelectedItem();
            String getcategory = (String) category.getSelectedItem();
            String getdescription = description.getText().toString();
            if(TextUtils.isEmpty(getname)){
                Toast.makeText(this, "Entrez le nom de l'entreprise", Toast.LENGTH_SHORT).show();
            }
            else if(getetage.isEmpty()){
                Toast.makeText(this, "Selectionner un etage", Toast.LENGTH_SHORT).show();
            }
            else if(getsouscategory.isEmpty()){
                Toast.makeText(this, "Selectionner une sous-categorie", Toast.LENGTH_SHORT).show();
            }
            else if(getcategory.isEmpty()){
                Toast.makeText(this, "Selectionner une catégorie", Toast.LENGTH_SHORT).show();
            }
            else {
                uploadData(idBatiment,getname,String.valueOf(idSousCategorie),getetage,getdescription);
            }
        });

        backbtn.setOnClickListener(v -> finish());


        getCategory();


    }

    private void getCategory(){
        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<Categories> call = apiService.getCategories(API_KEY);
            call.enqueue(new Callback<Categories>() {
                @Override
                public void onResponse(@NotNull Call<Categories> call, @NotNull Response<Categories> response) {
                    Timber.tag("categories").e(response.toString());
                    categories = response.body();

                    List<DataCategories> CategoryList = response.body().getData();



                    String[] Categorys = new String[CategoryList.size()];

                    for(int i=0; i< CategoryList.size(); i++){
                        Categorys[i]= CategoryList.get(i).getNom();


                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(NewBusinessActivity.this, android.R.layout.simple_spinner_item, Categorys);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        category.setAdapter(spinnerArrayAdapter);

                        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                                String[] SousCategorys = new String[categories.getData().get(position).getSousCategories().size()];
                                Integer[] SousCategorysId = new Integer[categories.getData().get(position).getSousCategories().size()];

                                for (int j=0; j<categories.getData().get(position).getSousCategories().size(); j++ ) {
                                    SousCategorys[j] = categories.getData().get(position).getSousCategories().get(j).getNom();
                                    SousCategorysId[j] = categories.getData().get(position).getSousCategories().get(j).getId();

                                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(NewBusinessActivity.this, android.R.layout.simple_spinner_item, SousCategorys);
                                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                    sous_categories.setAdapter(spinnerArrayAdapter);

                                    sous_categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            idSousCategorie = SousCategorysId[position];

                                            Toast.makeText(getApplicationContext(), String.valueOf(idSousCategorie), Toast.LENGTH_LONG).show();

                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parentView) {
                                            // your code here
                                        }
                                    });

                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parentView) {
                                // your code here
                            }

                        });


                    }

                    progress.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);


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


    private void uploadData(String idBatiment, String nom, String idSousCategorie, String etage,String description) {

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), image);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("nom", nom)
                .addFormDataPart("idBatiment", idBatiment)
                .addFormDataPart("idSousCategorie", idSousCategorie)
                .addFormDataPart("etage", etage)
                .addFormDataPart("description", description)
                .addFormDataPart("file", image.getName(), requestFile)
                .build();

        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<Etablissements> call = apiService.addetablissements(API_KEY,"Bearer "+pref.getToken(),requestBody);
            call.enqueue(new Callback<Etablissements>() {
                @Override
                public void onResponse(@NotNull Call<Etablissements> call, @NotNull Response<Etablissements> response) {
                    if(response.code() == 401 || response.code() == 500) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Error Create", Toast.LENGTH_LONG).show();
                    } else {
                        int idEtablissement = response.body().getData().getId();
                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(NewBusinessActivity.this, NewBusiness2Activity.class);
                        intent.putExtra("idEtablissement",String.valueOf(idEtablissement));
                        startActivity(intent);
                        finish();
                    }

                    progressBar.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(@NotNull Call<Etablissements> call, @NotNull Throwable t) {
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
                add1.setVisibility(View.GONE);
                Picasso.get().load(uri).into(cover);

            }
        }

    }
}