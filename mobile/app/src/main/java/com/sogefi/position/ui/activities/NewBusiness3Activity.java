package com.sogefi.position.ui.activities;

import static com.sogefi.position.utils.Constants.API_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sogefi.position.R;
import com.sogefi.position.api.APIClient;
import com.sogefi.position.api.ApiInterface;
import com.sogefi.position.models.Batiments;
import com.sogefi.position.models.Etablissements;
import com.sogefi.position.models.Horaires;
import com.sogefi.position.models.Images;
import com.sogefi.position.models.Telephones;
import com.sogefi.position.models.data.DataEtablissements;
import com.sogefi.position.utils.Function;
import com.sogefi.position.utils.PreferenceManager;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class NewBusiness3Activity extends AppCompatActivity {
    TextView openLundi,closedLundi,openSamedi,closedSamedi,openDimanche,closedDimanche;
    Button next3,back3;
    PreferenceManager pref;
    ProgressBar progressBar;
    CheckBox checkBoxSamedi,checkBoxDimanche;
    LinearLayout llbedSamedi,llbedDimanche;
    DataEtablissements dataEtablissements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_business3);
        pref = new PreferenceManager(this);

        String etablissement = getIntent().getStringExtra("etablissement");

        dataEtablissements = new Gson().fromJson(etablissement, DataEtablissements.class);

        checkBoxSamedi = findViewById(R.id.checkBoxSamedi);
        checkBoxDimanche = findViewById(R.id.checkBoxDimanche);
        llbedSamedi = findViewById(R.id.llbedSamedi);
        llbedDimanche = findViewById(R.id.llbedDimanche);

        checkBoxSamedi.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                llbedSamedi.setVisibility(View.VISIBLE);
            } else {
                llbedSamedi.setVisibility(View.GONE);
            }
            checkBoxSamedi.setChecked(isChecked);
        });

        checkBoxDimanche.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                llbedDimanche.setVisibility(View.VISIBLE);
            } else {
                llbedDimanche.setVisibility(View.GONE);
            }
            checkBoxDimanche.setChecked(isChecked);
        });



        openLundi = findViewById(R.id.openLundi);
        openSamedi = findViewById(R.id.openSamedi);
        openDimanche = findViewById(R.id.openDimanche);

        closedLundi = findViewById(R.id.closedLundi);
        closedSamedi = findViewById(R.id.closedSamedi);
        closedDimanche = findViewById(R.id.closedDimanche);

        progressBar = findViewById(R.id.progress3);

        next3 = findViewById(R.id.next3);

        back3 = findViewById(R.id.back3);

        back3.setOnClickListener(v -> finish());

        if(dataEtablissements != null) {
            next3.setText("Mettre à Jour");
            back3.setText("Accueil");
            back3.setOnClickListener(v -> {
                Intent intent = new Intent(NewBusiness3Activity.this, MapActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            });
        }


        if(dataEtablissements != null) {

            for (int i = 0; i < dataEtablissements.getHoraires().size(); i++) {
                if(dataEtablissements.getHoraires().get(i).getJour().equals("Lundi")) {
                    openLundi.setText(dataEtablissements.getHoraires().get(i).getHeureOuverture());
                    closedLundi.setText(dataEtablissements.getHoraires().get(i).getHeureFermeture());
                }
                if(dataEtablissements.getHoraires().get(i).getJour().equals("Samedi")) {
                    checkBoxSamedi.setChecked(true);
                    openSamedi.setText(dataEtablissements.getHoraires().get(i).getHeureOuverture());
                    closedSamedi.setText(dataEtablissements.getHoraires().get(i).getHeureFermeture());
                } if(dataEtablissements.getHoraires().get(i).getJour().equals("Dimanche")) {
                    checkBoxDimanche.setChecked(true);
                    openDimanche.setText(dataEtablissements.getHoraires().get(i).getHeureOuverture());
                    closedDimanche.setText(dataEtablissements.getHoraires().get(i).getHeureFermeture());
                }
            }







        }

        next3.setOnClickListener(v -> {
            String getopenLundi = openLundi.getText().toString();
            String getopenMardi = openLundi.getText().toString();
            String getopenMercredi = openLundi.getText().toString();
            String getopenJeudi = openLundi.getText().toString();
            String getopenVendredi = openLundi.getText().toString();
            String getopenSamedi = openSamedi.getText().toString();
            String getopenDimanche = openDimanche.getText().toString();

            String getclosedLundi = closedLundi.getText().toString();
            String getclosedMardi = closedLundi.getText().toString();
            String getclosedMercredi = closedLundi.getText().toString();
            String getclosedJeudi = closedLundi.getText().toString();
            String getclosedVendredi = closedLundi.getText().toString();
            String getclosedSamedi = closedSamedi.getText().toString();
            String getclosedDimanche = closedDimanche.getText().toString();

            if(!TextUtils.isEmpty(getopenLundi) && TextUtils.isEmpty(getclosedLundi)) {
                Toast.makeText(NewBusiness3Activity.this, "Entrez les heures de fermetures", Toast.LENGTH_SHORT).show();
            }
            else if(!TextUtils.isEmpty(getopenSamedi) && TextUtils.isEmpty(getclosedSamedi)) {
                Toast.makeText(NewBusiness3Activity.this, "Entrez les heures de fermetures", Toast.LENGTH_SHORT).show();
            }
            else if(!TextUtils.isEmpty(getopenDimanche) && TextUtils.isEmpty(getclosedDimanche)) {
                Toast.makeText(NewBusiness3Activity.this, "Entrez les heures de fermetures", Toast.LENGTH_SHORT).show();
            } else if(dataEtablissements != null) {
                updateData(getopenLundi,getclosedLundi,getopenMardi,getclosedMardi,getopenMercredi,getclosedMercredi,getopenJeudi,getclosedJeudi,getopenVendredi,getclosedVendredi,getopenSamedi,getclosedSamedi,getopenDimanche,getclosedDimanche);
            }
            else {

                sendData(getopenLundi,getclosedLundi,getopenMardi,getclosedMardi,getopenMercredi,getclosedMercredi,getopenJeudi,getclosedJeudi,getopenVendredi,getclosedVendredi,getopenSamedi,getclosedSamedi,getopenDimanche,getclosedDimanche);
            }


        });

        openLundi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opentime(openLundi);
            }
        });

        closedLundi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closedtime(closedLundi,openLundi.getText().toString());
            }
        });

      /*  openMardi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opentime(openMardi);
            }
        });

        closedMardi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closedtime(closedMardi);
            }
        });

        openMercredi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opentime(openMercredi);
            }
        });

        closedMercredi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closedtime(closedMercredi);
            }
        });

        openJeudi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opentime(openJeudi);
            }
        });

        closedJeudi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closedtime(closedJeudi);
            }
        });

        openVendredi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opentime(openVendredi);
            }
        });

        closedVendredi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closedtime(closedVendredi);
            }
        });*/

        openSamedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opentime(openSamedi);
            }
        });

        closedSamedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closedtime(closedSamedi,openSamedi.getText().toString());
            }
        });

        openDimanche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opentime(openDimanche);
            }
        });

        closedDimanche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closedtime(closedDimanche,openDimanche.getText().toString());
            }
        });

    }

    public void updateData(String openLundi,String closedLundi,String openMardi,String closedMardi,String openMercredi,String closedMercredi,String openJeudi,String closedJeudi,String openVendredi,String closedVendredi,String openSamedi,String closedSamedi,String openDimanche,String closedDimanche) {
        File coverEtablissement = (File) getIntent().getExtras().get("coverEtablissement");

        String idBatiment = getIntent().getStringExtra("idBatiment");
        updateEtablissement(idBatiment,coverEtablissement,openLundi,closedLundi,openMardi,closedMardi,openMercredi,closedMercredi,openJeudi,closedJeudi,openVendredi,closedVendredi,openSamedi,closedSamedi,openDimanche,closedDimanche);
    }

    public void sendData(String openLundi,String closedLundi,String openMardi,String closedMardi,String openMercredi,String closedMercredi,String openJeudi,String closedJeudi,String openVendredi,String closedVendredi,String openSamedi,String closedSamedi,String openDimanche,String closedDimanche) {
       // Toast.makeText(getApplicationContext(), getIntent().getStringExtra("rue"), Toast.LENGTH_LONG).show();
        RequestBody requestBody;
       File imageBatiment = (File) getIntent().getExtras().get("imageBatiment");
       File coverEtablissement = (File) getIntent().getExtras().get("coverEtablissement");

        String idBatiment = getIntent().getStringExtra("idBatiment");


        progressBar.setVisibility(View.VISIBLE);

        if(idBatiment != null) {
            addEtablissement(idBatiment,coverEtablissement,openLundi,closedLundi,openMardi,closedMardi,openMercredi,closedMercredi,openJeudi,closedJeudi,openVendredi,closedVendredi,openSamedi,closedSamedi,openDimanche,closedDimanche);
        } else {


            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), imageBatiment);
            requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("nom", getIntent().getStringExtra("nomBatiment"))
                    .addFormDataPart("nombreNiveaux", getIntent().getStringExtra("nombreNiveaux"))
                    .addFormDataPart("codeBatiment", getIntent().getStringExtra("codeBatiment"))
                    .addFormDataPart("longitude", getIntent().getStringExtra("longitude"))
                    .addFormDataPart("latitude", getIntent().getStringExtra("latitude"))
                    .addFormDataPart("indication", getIntent().getStringExtra("indication"))
                    .addFormDataPart("rue", getIntent().getStringExtra("rue"))
                    .addFormDataPart("ville", getIntent().getStringExtra("ville"))
                    .addFormDataPart("commune", getIntent().getStringExtra("commune"))
                    .addFormDataPart("quartier", getIntent().getStringExtra("quartier"))
                    .addFormDataPart("file", imageBatiment.getName(), requestFile)
                    .build();


            if (Function.isNetworkAvailable(getApplicationContext())) {
                ApiInterface apiService =
                        APIClient.getNewClient3().create(ApiInterface.class);
                Call<Batiments> call = apiService.addbatiments(API_KEY,"Bearer "+pref.getToken(),requestBody);
                call.enqueue(new Callback<Batiments>() {
                    @Override
                    public void onResponse(@NotNull Call<Batiments> call, @NotNull Response<Batiments> response) {
                        if(response.code() == 401 || response.code() == 500) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Error Create Batiment", Toast.LENGTH_LONG).show();
                        } else {
                            int idBatiment = response.body().getData().getId();
                            addEtablissement(String.valueOf(idBatiment),coverEtablissement,openLundi,closedLundi,openMardi,closedMardi,openMercredi,closedMercredi,openJeudi,closedJeudi,openVendredi,closedVendredi,openSamedi,closedSamedi,openDimanche,closedDimanche);

                        }

                    }

                    @Override
                    public void onFailure(@NotNull Call<Batiments> call, @NotNull Throwable t) {
                        // Log error here since request failed
                        progressBar.setVisibility(View.GONE);
                        Timber.tag("batiments").e(t.toString());
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

    public void updateEtablissement(String idBatiment,File coverEtablissement,String openLundi,String closedLundi,String openMardi,String closedMardi,String openMercredi,String closedMercredi,String openJeudi,String closedJeudi,String openVendredi,String closedVendredi,String openSamedi,String closedSamedi,String openDimanche,String closedDimanche) {
RequestBody requestBody;
        if(getIntent().getStringExtra("imageN").equals("non")) {
            requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("nom", getIntent().getStringExtra("nomEtablissement"))
                    .addFormDataPart("idBatiment", dataEtablissements.getId().toString())
                    .addFormDataPart("idSousCategorie", getIntent().getStringExtra("idSousCategorie"))
                    .addFormDataPart("autres", getIntent().getStringExtra("autres"))
                    .addFormDataPart("etage", getIntent().getStringExtra("etage"))
                    .addFormDataPart("description", getIntent().getStringExtra("description"))
                    .addFormDataPart("indicationAdresse", getIntent().getStringExtra("indicationAdresse"))
                    .addFormDataPart("codepostal", getIntent().getStringExtra("codepostal"))
                    .addFormDataPart("siteinternet", getIntent().getStringExtra("siteinternet"))
                    .addFormDataPart("_method", "put")
                    .build();
        } else {
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), coverEtablissement);

            requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("nom", getIntent().getStringExtra("nomEtablissement"))
                    .addFormDataPart("idBatiment", dataEtablissements.getId().toString())
                    .addFormDataPart("idSousCategorie", getIntent().getStringExtra("idSousCategorie"))
                    .addFormDataPart("autres", getIntent().getStringExtra("autres"))
                    .addFormDataPart("etage", getIntent().getStringExtra("etage"))
                    .addFormDataPart("description", getIntent().getStringExtra("description"))
                    .addFormDataPart("indicationAdresse", getIntent().getStringExtra("indicationAdresse"))
                    .addFormDataPart("codepostal", getIntent().getStringExtra("codepostal"))
                    .addFormDataPart("siteinternet", getIntent().getStringExtra("siteinternet"))
                    .addFormDataPart("_method", "put")
                    .addFormDataPart("file", coverEtablissement.getName(), requestFile)
                    .build();
        }


        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<Etablissements> call = apiService.updateetablissements(API_KEY,"Bearer "+pref.getToken(),requestBody,dataEtablissements.getId());
            call.enqueue(new Callback<Etablissements>() {
                @Override
                public void onResponse(@NotNull Call<Etablissements> call, @NotNull Response<Etablissements> response) {
                    if(response.code() == 401 || response.code() == 500) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Error Create Etablissement", Toast.LENGTH_LONG).show();
                    } else {
                        int idEtablissement = response.body().getData().getId();

                        updateTelephones(String.valueOf(idEtablissement),getIntent().getStringExtra("telephone"),getIntent().getStringExtra("whatsapp1"),getIntent().getStringExtra("whatsapp2"),openLundi,closedLundi,openMardi,closedMardi,openMercredi,closedMercredi,openJeudi,closedJeudi,openVendredi,closedVendredi,openSamedi,closedSamedi,openDimanche,closedDimanche);

                    }


                }

                @Override
                public void onFailure(@NotNull Call<Etablissements> call, @NotNull Throwable t) {
                    // Log error here since request failed
                    progressBar.setVisibility(View.GONE);
                    Timber.tag("etablissements").e(t.toString());
                    Log.e("error update", t.toString());
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }
    }

    public void addEtablissement(String idBatiment,File coverEtablissement,String openLundi,String closedLundi,String openMardi,String closedMardi,String openMercredi,String closedMercredi,String openJeudi,String closedJeudi,String openVendredi,String closedVendredi,String openSamedi,String closedSamedi,String openDimanche,String closedDimanche) {

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), coverEtablissement);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("nom", getIntent().getStringExtra("nomEtablissement"))
                .addFormDataPart("idBatiment", idBatiment)
                .addFormDataPart("idSousCategorie", getIntent().getStringExtra("idSousCategorie"))
                .addFormDataPart("autres", getIntent().getStringExtra("autres"))
                .addFormDataPart("etage", getIntent().getStringExtra("etage"))
                .addFormDataPart("description", getIntent().getStringExtra("description"))
                .addFormDataPart("indicationAdresse", getIntent().getStringExtra("indicationAdresse"))
                .addFormDataPart("codePostal", getIntent().getStringExtra("codepostal"))
                .addFormDataPart("siteInternet", getIntent().getStringExtra("siteinternet"))
                .addFormDataPart("file", coverEtablissement.getName(), requestFile)
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
                        Toast.makeText(getApplicationContext(), "Error Create Etablissement", Toast.LENGTH_LONG).show();
                    } else {
                        int idEtablissement = response.body().getData().getId();

                        addTelephones(String.valueOf(idEtablissement),getIntent().getStringExtra("telephone"),getIntent().getStringExtra("whatsapp1"),getIntent().getStringExtra("whatsapp2"),openLundi,closedLundi,openMardi,closedMardi,openMercredi,closedMercredi,openJeudi,closedJeudi,openVendredi,closedVendredi,openSamedi,closedSamedi,openDimanche,closedDimanche);

                    }


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



    public void addTelephones(String idEtablissement,String phone,String whatsapp1,String whatsapp2,String openLundi,String closedLundi,String openMardi,String closedMardi,String openMercredi,String closedMercredi,String openJeudi,String closedJeudi,String openVendredi,String closedVendredi,String openSamedi,String closedSamedi,String openDimanche,String closedDimanche) {
        uploadPhone(phone,idEtablissement,1,openLundi,closedLundi,openMardi,closedMardi,openMercredi,closedMercredi,openJeudi,closedJeudi,openVendredi,closedVendredi,openSamedi,closedSamedi,openDimanche,closedDimanche);
        uploadPhone(whatsapp1,idEtablissement,0,openLundi,closedLundi,openMardi,closedMardi,openMercredi,closedMercredi,openJeudi,closedJeudi,openVendredi,closedVendredi,openSamedi,closedSamedi,openDimanche,closedDimanche);
        if(!TextUtils.isEmpty(whatsapp2)) {
            uploadPhone(whatsapp2,idEtablissement,0,openLundi,closedLundi,openMardi,closedMardi,openMercredi,closedMercredi,openJeudi,closedJeudi,openVendredi,closedVendredi,openSamedi,closedSamedi,openDimanche,closedDimanche);
        }
    }

    public void updateTelephones(String idEtablissement,String phone,String whatsapp1,String whatsapp2,String openLundi,String closedLundi,String openMardi,String closedMardi,String openMercredi,String closedMercredi,String openJeudi,String closedJeudi,String openVendredi,String closedVendredi,String openSamedi,String closedSamedi,String openDimanche,String closedDimanche) {
        uploadUpdatePhone(phone,idEtablissement,1,openLundi,closedLundi,openMardi,closedMardi,openMercredi,closedMercredi,openJeudi,closedJeudi,openVendredi,closedVendredi,openSamedi,closedSamedi,openDimanche,closedDimanche);
        uploadUpdatePhone(whatsapp1,idEtablissement,0,openLundi,closedLundi,openMardi,closedMardi,openMercredi,closedMercredi,openJeudi,closedJeudi,openVendredi,closedVendredi,openSamedi,closedSamedi,openDimanche,closedDimanche);
        if(!TextUtils.isEmpty(whatsapp2)) {
            uploadUpdatePhone(whatsapp2,idEtablissement,0,openLundi,closedLundi,openMardi,closedMardi,openMercredi,closedMercredi,openJeudi,closedJeudi,openVendredi,closedVendredi,openSamedi,closedSamedi,openDimanche,closedDimanche);
        }
    }


    public void uploadPhone(String phone,String idEtablissement,int principal,String openLundi,String closedLundi,String openMardi,String closedMardi,String openMercredi,String closedMercredi,String openJeudi,String closedJeudi,String openVendredi,String closedVendredi,String openSamedi,String closedSamedi,String openDimanche,String closedDimanche) {
        RequestBody requestBody;
        if(principal == 1) {
            requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("idEtablissement", idEtablissement)
                    .addFormDataPart("numero", phone)
                    .addFormDataPart("principal", "1")
                    .addFormDataPart("whatsapp", "0")
                    .build();
        } else {
            requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("idEtablissement", idEtablissement)
                    .addFormDataPart("numero", phone)
                    .addFormDataPart("principal", "0")
                    .addFormDataPart("whatsapp", "1")
                    .build();
        }



        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<Telephones> call = apiService.addtelephone(API_KEY,"Bearer "+pref.getToken(),requestBody);
            call.enqueue(new Callback<Telephones>() {
                @Override
                public void onResponse(@NotNull Call<Telephones> call, @NotNull Response<Telephones> response) {
                    if(response.code() == 401 || response.code() == 500) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Error create Phone", Toast.LENGTH_LONG).show();
                    } else {
                        addHoraires(idEtablissement,openLundi,closedLundi,openMardi,closedMardi,openMercredi,closedMercredi,openJeudi,closedJeudi,openVendredi,closedVendredi,openSamedi,closedSamedi,openDimanche,closedDimanche);
                    }



                }

                @Override
                public void onFailure(@NotNull Call<Telephones> call, @NotNull Throwable t) {
                    // Log error here since request failed
                    progressBar.setVisibility(View.GONE);
                    Timber.tag("telephones").e(t.toString());
                    Log.e("error create", t.toString());
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }
    }

    public void uploadUpdatePhone(String phone,String idEtablissement,int principal,String openLundi,String closedLundi,String openMardi,String closedMardi,String openMercredi,String closedMercredi,String openJeudi,String closedJeudi,String openVendredi,String closedVendredi,String openSamedi,String closedSamedi,String openDimanche,String closedDimanche) {
       int idPhone = 0;
         for (int i = 0; i < dataEtablissements.getTelephones().size(); i++) {
                if(dataEtablissements.getTelephones().get(i).getPrincipal().equals(String.valueOf(principal))) {
                    idPhone = dataEtablissements.getTelephones().get(i).getId();
                }
            }

        RequestBody requestBody;
        if(principal == 1) {
            requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("idEtablissement", idEtablissement)
                    .addFormDataPart("numero", phone)
                    .addFormDataPart("principal", "1")
                    .addFormDataPart("whatsapp", "0")
                    .addFormDataPart("_method", "put")
                    .build();
        } else {
            requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("idEtablissement", idEtablissement)
                    .addFormDataPart("numero", phone)
                    .addFormDataPart("principal", "0")
                    .addFormDataPart("whatsapp", "1")
                    .addFormDataPart("_method", "put")
                    .build();
        }



        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<Telephones> call = apiService.updatetelephone(API_KEY,"Bearer "+pref.getToken(),requestBody,idPhone);
            call.enqueue(new Callback<Telephones>() {
                @Override
                public void onResponse(@NotNull Call<Telephones> call, @NotNull Response<Telephones> response) {
                    if(response.code() == 401 || response.code() == 500) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Error update Phone", Toast.LENGTH_LONG).show();
                    } else {
                        updateHoraires(idEtablissement,openLundi,closedLundi,openMardi,closedMardi,openMercredi,closedMercredi,openJeudi,closedJeudi,openVendredi,closedVendredi,openSamedi,closedSamedi,openDimanche,closedDimanche);
                    }



                }

                @Override
                public void onFailure(@NotNull Call<Telephones> call, @NotNull Throwable t) {
                    // Log error here since request failed
                    progressBar.setVisibility(View.GONE);
                    Timber.tag("telephones").e(t.toString());
                    Log.e("error update", t.toString());
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }
    }




    public void addHoraires(String idEtablissement,String openLundi,String closedLundi,String openMardi,String closedMardi,String openMercredi,String closedMercredi,String openJeudi,String closedJeudi,String openVendredi,String closedVendredi,String openSamedi,String closedSamedi,String openDimanche,String closedDimanche) {
        if(!TextUtils.isEmpty(openLundi)) {
            uploadHoraire(openLundi,closedLundi, idEtablissement,"Lundi");
        }if(!TextUtils.isEmpty(openMardi)) {
            uploadHoraire(openMardi,closedMardi, idEtablissement,"Mardi");
        }if(!TextUtils.isEmpty(openMercredi)) {
            uploadHoraire(openMercredi,closedMercredi, idEtablissement,"Mercredi");
        }if(!TextUtils.isEmpty(openJeudi)) {
            uploadHoraire(openJeudi,closedJeudi, idEtablissement,"Jeudi");
        }if(!TextUtils.isEmpty(openVendredi)) {
            uploadHoraire(openVendredi,closedVendredi, idEtablissement,"Vendredi");
        }if(!TextUtils.isEmpty(openSamedi)) {
            uploadHoraire(openSamedi,closedSamedi, idEtablissement,"Samedi");
        }if(!TextUtils.isEmpty(openDimanche)) {
            uploadHoraire(openDimanche,closedDimanche, idEtablissement,"Dimanche");
        }

        addImage(idEtablissement);
    }


    public void updateHoraires(String idEtablissement,String openLundi,String closedLundi,String openMardi,String closedMardi,String openMercredi,String closedMercredi,String openJeudi,String closedJeudi,String openVendredi,String closedVendredi,String openSamedi,String closedSamedi,String openDimanche,String closedDimanche) {
        if(!TextUtils.isEmpty(openLundi)) {
            uploadUHoraire(openLundi,closedLundi, idEtablissement,"Lundi");
        }if(!TextUtils.isEmpty(openMardi)) {
            uploadUHoraire(openMardi,closedMardi, idEtablissement,"Mardi");
        }if(!TextUtils.isEmpty(openMercredi)) {
            uploadUHoraire(openMercredi,closedMercredi, idEtablissement,"Mercredi");
        }if(!TextUtils.isEmpty(openJeudi)) {
            uploadUHoraire(openJeudi,closedJeudi, idEtablissement,"Jeudi");
        }if(!TextUtils.isEmpty(openVendredi)) {
            uploadUHoraire(openVendredi,closedVendredi, idEtablissement,"Vendredi");
        }if(!TextUtils.isEmpty(openSamedi)) {
            uploadUHoraire(openSamedi,closedSamedi, idEtablissement,"Samedi");
        }if(!TextUtils.isEmpty(openDimanche)) {
            uploadUHoraire(openDimanche,closedDimanche, idEtablissement,"Dimanche");
        }

        if(!getIntent().getStringExtra("imageN").equals("non")) {
            updateImage(idEtablissement);
        }

        else{
            Toast.makeText(getApplicationContext(), "Mise à jour de l'etablissement reussie", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(NewBusiness3Activity.this, MapActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }


    }

    public void addImage(String idEtablissement) {
        File imageEtablissement = (File) getIntent().getExtras().get("imageEtablissement");
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), imageEtablissement);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("idEtablissement", idEtablissement)
                .addFormDataPart("file", imageEtablissement.getName(), requestFile)
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
                        Toast.makeText(getApplicationContext(), "Ajout de l'etablissement reussi", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(NewBusiness3Activity.this, MapActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

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

    public void updateImage(String idEtablissement) {
        File imageEtablissement = (File) getIntent().getExtras().get("imageEtablissement");
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), imageEtablissement);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("idEtablissement", idEtablissement)
                .addFormDataPart("file", imageEtablissement.getName(), requestFile)
                .addFormDataPart("_method", "put")
                .build();

        int idImage = dataEtablissements.getImages().get(0).getId();

        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<Images> call = apiService.updateimage(API_KEY,"Bearer "+pref.getToken(),requestBody,idImage);
            call.enqueue(new Callback<Images>() {
                @Override
                public void onResponse(@NotNull Call<Images> call, @NotNull Response<Images> response) {
                    if(response.code() == 401 || response.code() == 500) {
                        progressBar.setVisibility(View.GONE);
                        //   Toast.makeText(getApplicationContext(), "Error upload image", Toast.LENGTH_LONG).show();
                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Mise à jour de l'etablissement reussie", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(NewBusiness3Activity.this, MapActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    }



                }

                @Override
                public void onFailure(@NotNull Call<Images> call, @NotNull Throwable t) {
                    // Log error here since request failed
                    progressBar.setVisibility(View.GONE);
                    Timber.tag("images").e(t.toString());
                    Log.e("error update", t.toString());
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }

    }


    public void uploadHoraire(String open,String closed,String idEtablissement,String jour) {


        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("idEtablissement", idEtablissement)
                .addFormDataPart("jour", jour)
                .addFormDataPart("ouvert", "1")
                .addFormDataPart("heureOuverture", open)
                .addFormDataPart("heureFermeture", closed)
                .build();




        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<Horaires> call = apiService.addhoraire(API_KEY,"Bearer "+pref.getToken(),requestBody);
            call.enqueue(new Callback<Horaires>() {
                @Override
                public void onResponse(@NotNull Call<Horaires> call, @NotNull Response<Horaires> response) {
                    if(response.code() == 401 || response.code() == 500) {
                        progressBar.setVisibility(View.GONE);
                    } else {

                    }



                }

                @Override
                public void onFailure(@NotNull Call<Horaires> call, @NotNull Throwable t) {
                    // Log error here since request failed
                    progressBar.setVisibility(View.GONE);
                    Timber.tag("horaires").e(t.toString());
                    Log.e("error create", t.toString());
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }


    }


    public void uploadUHoraire(String open,String closed,String idEtablissement,String jour) {
        int idHoraire = 0;
        for (int i = 0; i < dataEtablissements.getHoraires().size(); i++) {
            if(dataEtablissements.getHoraires().get(i).getJour().equals(jour)) {
                idHoraire = dataEtablissements.getHoraires().get(i).getId();
            }
        }

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("idEtablissement", idEtablissement)
                .addFormDataPart("jour", jour)
                .addFormDataPart("ouvert", "1")
                .addFormDataPart("heureOuverture", open)
                .addFormDataPart("heureFermeture", closed)
                .addFormDataPart("_method", "put")
                .build();




        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<Horaires> call = apiService.updatehoraire(API_KEY,"Bearer "+pref.getToken(),requestBody,idHoraire);
            call.enqueue(new Callback<Horaires>() {
                @Override
                public void onResponse(@NotNull Call<Horaires> call, @NotNull Response<Horaires> response) {
                    if(response.code() == 401 || response.code() == 500) {
                        progressBar.setVisibility(View.GONE);
                    } else {

                    }



                }

                @Override
                public void onFailure(@NotNull Call<Horaires> call, @NotNull Throwable t) {
                    // Log error here since request failed
                    progressBar.setVisibility(View.GONE);
                    Timber.tag("horaires").e(t.toString());
                    Log.e("error update", t.toString());
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }


    }

    private void opentime(TextView open) {
        Calendar cur_calender = Calendar.getInstance();
        TimePickerDialog datePicker = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                try {

                    String timeString = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    Date time = sdf.parse(timeString);

                    sdf = new SimpleDateFormat("HH:mm");
                    String formatedTime = sdf.format(time);
                    open.setText(formatedTime);

                } catch (Exception e) {}
            }
        }, cur_calender.get(Calendar.HOUR_OF_DAY), cur_calender.get(Calendar.MINUTE), true);
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.colorPrimary));
        datePicker.show(getFragmentManager(), "Timepickerdialog");
    }

    private void closedtime(TextView closed,String openTime) {
        Calendar cur_calender = Calendar.getInstance();
        TimePickerDialog datePicker = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                try {

                    String timeString = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    Date time = sdf.parse(timeString);

                    sdf = new SimpleDateFormat("HH:mm");
                    String formatedTime = sdf.format(time);
                    Date open = sdf.parse(openTime);
                    if(open.compareTo(time) < 0) {
                        closed.setText(formatedTime);
                    } else {
                        Toast.makeText(NewBusiness3Activity.this, "Heure de fermeture non valide", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {}
            }
        }, cur_calender.get(Calendar.HOUR_OF_DAY), cur_calender.get(Calendar.MINUTE), true);
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.colorPrimary));
        datePicker.show(getFragmentManager(), "Timepickerdialog");
    }
}