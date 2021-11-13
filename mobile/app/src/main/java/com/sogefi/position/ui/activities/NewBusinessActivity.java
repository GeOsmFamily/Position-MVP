package com.sogefi.position.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.sogefi.position.R;
import com.sogefi.position.api.APIClient;
import com.sogefi.position.api.ApiInterface;
import com.sogefi.position.models.Categories;
import com.sogefi.position.models.Datum;
import com.sogefi.position.models.ResponseApi;
import com.sogefi.position.models.SousCategory;
import com.sogefi.position.utils.Function;
import com.sogefi.position.utils.PreferenceManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class NewBusinessActivity extends AppCompatActivity {
    EditText name, phone, whatsapp1,whatsapp2, description;
    TextView  toolbartext;
    Button next;
    ArrayList<String> propertyPurpose, catNameList, sousCatNameList;
    ArrayList<Categories> categoriList;
    ArrayList<SousCategory> sousCategoriesList;
    boolean isimage = false;
    String message;
    ImageView  backbtn;
    Spinner sous_categories, category;
    ProgressDialog dialog;
    String Id;
    ScrollView scrollView;
    ProgressBar progress;
    PreferenceManager pref;
    Categories categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_business);

        pref = new PreferenceManager(this);

        dialog = new ProgressDialog(this);
        propertyPurpose = new ArrayList<>();
        categoriList = new ArrayList<>();
        catNameList = new ArrayList<>();
        sousCatNameList = new ArrayList<>();
        sousCategoriesList = new ArrayList<>();
        Intent i = getIntent();
        Id = i.getStringExtra("Id");

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        whatsapp1 = findViewById(R.id.whatsapp1);
        whatsapp2 = findViewById(R.id.whatsapp2);
        description = findViewById(R.id.description);
        next = findViewById(R.id.next);
        category = findViewById(R.id.category);
        sous_categories = findViewById(R.id.sous_categories);
        backbtn = findViewById(R.id.back_btn);
        progress = findViewById(R.id.progressBar);
        scrollView = findViewById(R.id.scrollView);
        toolbartext = findViewById(R.id.toolbartext);


        progress.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        next.setOnClickListener(v -> {
            String getname = name.getText().toString();
            String getphone = phone.getText().toString();
            String getWhatsapp1 = whatsapp1.getText().toString();
            String getWhatsapp2 = whatsapp2.getText().toString();
            String getsouscategory = (String) sous_categories.getSelectedItem();
            String getcategory = (String) category.getSelectedItem();
            String getdescription = description.getText().toString();
            if(TextUtils.isEmpty(getname)){
                Toast.makeText(this, "Entrez le nom de l'entreprise", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(getphone)){
                Toast.makeText(this, "Entrez le numéro de Téléphone", Toast.LENGTH_SHORT).show();
            }
            else if(getsouscategory.isEmpty()){
                Toast.makeText(this, "Selectionner une sous-categorie", Toast.LENGTH_SHORT).show();
            }
            else if(getcategory.isEmpty()){
                Toast.makeText(this, "Selectionner une catégorie", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(getdescription)){
                Toast.makeText(this, "Entrez une catégorie", Toast.LENGTH_SHORT).show();
            }
            else {
                uploadData();
            }
        });

        backbtn.setOnClickListener(v -> finish());
        next.setOnClickListener(v -> uploadData());

      /*  category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getSousCategories(position);
            }
        });*/


        getCategory();


    }

    private void getCategory(){
        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient4().create(ApiInterface.class);
            Call<Categories> call = apiService.getCategories("Bearer " + pref.getToken());
            call.enqueue(new Callback<Categories>() {
                @Override
                public void onResponse(@NotNull Call<Categories> call, @NotNull Response<Categories> response) {
                    Timber.tag("categories").e(response.toString());
                    categories = response.body();

                    List<Datum> CategoryList = response.body().getData();



                    String[] Categorys = new String[CategoryList.size()];

                    for(int i=0; i< CategoryList.size(); i++){
                        Categorys[i]= CategoryList.get(i).getNom();

                       /* String[] SousCategorys = new String[CategoryList.get(i).getSousCategories().size()];

                        for (int j=0; j<CategoryList.get(i).getSousCategories().size(); j++ ) {
                            SousCategorys[j] = CategoryList.get(i).getSousCategories().get(j).getNom();

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(NewBusinessActivity.this, android.R.layout.simple_spinner_item, SousCategorys);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            sous_categories.setAdapter(spinnerArrayAdapter);

                        }*/

                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(NewBusinessActivity.this, android.R.layout.simple_spinner_item, Categorys);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        category.setAdapter(spinnerArrayAdapter);


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

    private void getSousCategories() {
     int select = category.getSelectedItemPosition() + 1;

       /* String[] SousCategorys = new String[categories.getData().get(select).getSousCategories().size()];

        for (int j=0; j<categories.getData().get(select).getSousCategories().size(); j++ ) {
            SousCategorys[j] = categories.getData().get(select).getSousCategories().get(j).getNom();

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(NewBusinessActivity.this, android.R.layout.simple_spinner_item, SousCategorys);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            sous_categories.setAdapter(spinnerArrayAdapter);

        }*/

        Toast.makeText(getApplicationContext(), select, Toast.LENGTH_LONG).show();

    }

    private void uploadData() {
        Intent intent = new Intent(NewBusinessActivity.this, NewBusiness2Activity.class);
        startActivity(intent);
        finish();
    }
}