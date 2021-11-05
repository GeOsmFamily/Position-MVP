package com.sogefi.position.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
import com.sogefi.position.models.Categories;
import com.sogefi.position.models.SousCategories;

import java.util.ArrayList;

public class NewBusinessActivity extends AppCompatActivity {
    EditText name, phone, whatsapp1,whatsapp2, description;
    TextView  toolbartext;
    Button next;
    ArrayList<String> propertyPurpose, catNameList, sousCatNameList;
    ArrayList<Categories> categoriList;
    ArrayList<SousCategories> sousCategoriesList;
    boolean isimage = false;
    String message;
    ImageView  backbtn;
    Spinner sous_categories, category;
    ProgressDialog dialog;
    String Id;
    ScrollView scrollView;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_business);

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
              //  uploadData();
            }
        });

        backbtn.setOnClickListener(v -> finish());

        getSousCategory();
        getCategory();
    }

    private void getCategory(){

    }
}