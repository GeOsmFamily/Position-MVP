package com.sogefi.position.ui.activities;

import static com.sogefi.position.utils.Constants.API_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.sogefi.position.R;
import com.sogefi.position.api.APIClient;
import com.sogefi.position.api.ApiInterface;
import com.sogefi.position.models.Etablissements;
import com.sogefi.position.models.Horaires;
import com.sogefi.position.models.Images;
import com.sogefi.position.models.Telephones;
import com.sogefi.position.utils.Function;
import com.sogefi.position.utils.PreferenceManager;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class NewBusiness4Activity extends AppCompatActivity {
    PreferenceManager pref;
    ProgressBar progressBar;
    Button submit,back4;
    ImageView image41,image42,image43,image44,image45,add41,add42,add43,add44,add45;
    File image1 ,image2,image3,image4,image5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_business4);

        String idEtablissement = getIntent().getStringExtra("idEtablissement");

        pref = new PreferenceManager(this);

        progressBar = findViewById(R.id.progress4);
        submit = findViewById(R.id.submit);
        image41 = findViewById(R.id.image41);
        image42 = findViewById(R.id.image42);
        image43 = findViewById(R.id.image43);
        image44 = findViewById(R.id.image44);
        image45 = findViewById(R.id.image45);

        add41 = findViewById(R.id.add41);
        add42 = findViewById(R.id.add42);
        add43 = findViewById(R.id.add43);
        add44 = findViewById(R.id.add44);
        add45 = findViewById(R.id.add45);

        back4 = findViewById(R.id.back4);


        progressBar.setVisibility(View.GONE);

        image41.setOnClickListener(v -> chooseImage(201));
        image42.setOnClickListener(v -> chooseImage(202));
        image43.setOnClickListener(v -> chooseImage(203));
        image44.setOnClickListener(v -> chooseImage(204));
        image45.setOnClickListener(v -> chooseImage(205));

        submit.setOnClickListener(v -> updateEtablissement(idEtablissement));
        back4.setOnClickListener(v -> finish());

    }
    
    public void updateEtablissement(String idEtablissement) {
       addImages(idEtablissement);

        Intent intent = new Intent(NewBusiness4Activity.this, MapActivity.class);
        startActivity(intent);
        finish();


    }

    public void chooseImage(int code) {
        ImagePicker.with(this)
                .crop()
                .compress(5096)
                .maxResultSize(1080,1080)
                .start(code);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 201) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                Uri uri = data.getData();
                image1 = new File(uri.getPath());
                add41.setVisibility(View.GONE);
                Picasso.get().load(uri).into(image41);

            }
        }

        if (requestCode == 202) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                Uri uri = data.getData();
                image2 = new File(uri.getPath());
                add42.setVisibility(View.GONE);
                Picasso.get().load(uri).into(image42);

            }
        }

        if (requestCode == 203) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                Uri uri = data.getData();
                image3 = new File(uri.getPath());
                add43.setVisibility(View.GONE);
                Picasso.get().load(uri).into(image43);

            }
        }

        if (requestCode == 204) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                Uri uri = data.getData();
                image4 = new File(uri.getPath());
                add44.setVisibility(View.GONE);
                Picasso.get().load(uri).into(image44);

            }
        }

        if (requestCode == 205) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                Uri uri = data.getData();
                image5 = new File(uri.getPath());
                add45.setVisibility(View.GONE);
                Picasso.get().load(uri).into(image45);

            }
        }



    }

    public void addImages(String idEtablissement) {
        if(image1 != null) {
            uploadImage(image1,idEtablissement);
        }if(image2 != null) {
            uploadImage(image2,idEtablissement);
        }if(image3 != null) {
            uploadImage(image3,idEtablissement);
        }if(image4 != null) {
            uploadImage(image4,idEtablissement);
        }if(image5 != null) {
            uploadImage(image5,idEtablissement);
        }

        Toast.makeText(getApplicationContext(), "Ajout de l'entreprise reussi", Toast.LENGTH_LONG).show();
    }




    public void uploadImage(File image,String idEtablissement) {
        progressBar.setVisibility(View.VISIBLE);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), image);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("idEtablissement", idEtablissement)
                .addFormDataPart("file", image.getName(), requestFile)
                .build();

        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<Images> call = apiService.addimage(API_KEY,"Bearer "+pref.getToken(),requestBody);
            call.enqueue(new Callback<Images>() {
                @Override
                public void onResponse(@NotNull Call<Images> call, @NotNull Response<Images> response) {
                    if(response.code() == 401 || response.code() == 500) {
                        progressBar.setVisibility(View.GONE);
                     //   Toast.makeText(getApplicationContext(), "Error upload image", Toast.LENGTH_LONG).show();
                    } else {
                        progressBar.setVisibility(View.GONE);

                    }



                }

                @Override
                public void onFailure(@NotNull Call<Images> call, @NotNull Throwable t) {
                    // Log error here since request failed
                    progressBar.setVisibility(View.GONE);
                    Timber.tag("images").e(t.toString());
                    Log.e("error create", t.toString());
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }


    }



}