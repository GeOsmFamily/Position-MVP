package com.sogefi.position.ui.activities;

import static com.sogefi.position.utils.Constants.API_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sogefi.position.R;
import com.sogefi.position.api.APIClient;
import com.sogefi.position.api.ApiInterface;
import com.sogefi.position.models.Etablissements;
import com.sogefi.position.models.Telephones;
import com.sogefi.position.models.data.DataEtablissements;
import com.sogefi.position.utils.Function;
import com.sogefi.position.utils.PreferenceManager;

import org.jetbrains.annotations.NotNull;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class NewBusiness2Activity extends AppCompatActivity {
    EditText phone,whatsapp1,whatsapp2,indication, code_postal ,site_internet;
    Button next2;
    ScrollView scrollView;
    ProgressBar progress;
    PreferenceManager pref;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_business2);

        pref = new PreferenceManager(this);

        String idEtablissement = getIntent().getStringExtra("idEtablissement");

        phone = findViewById(R.id.phone);
        whatsapp1 = findViewById(R.id.whatsapp1);
        whatsapp2 = findViewById(R.id.whatsapp2);
        indication = findViewById(R.id.indication_adresse);
        code_postal = findViewById(R.id.code_postal);
        site_internet = findViewById(R.id.site_internet);
        next2 = findViewById(R.id.next2);
        scrollView = findViewById(R.id.scrollView2);
        progress = findViewById(R.id.progressBar2);
        progressBar = findViewById(R.id.progress2);

        progressBar.setVisibility(View.GONE);



        next2.setOnClickListener(v -> {
            String getphone = phone.getText().toString();
            String getwhatsapp1 = whatsapp1.getText().toString();
            String getwhatsapp2 = whatsapp2.getText().toString();
            String getindication = indication.getText().toString();
            String getcodepostal = code_postal.getText().toString();
            String getsiteinternet = site_internet.getText().toString();


            if(TextUtils.isEmpty(getphone)){
                Toast.makeText(this, "Entrez le numéro de l'entreprise", Toast.LENGTH_SHORT).show();
            }
            if(TextUtils.isEmpty(getwhatsapp1)){
                Toast.makeText(this, "Entrez le numéro whatsapp de l'entreprise", Toast.LENGTH_SHORT).show();
            }

            else {
                sendData(idEtablissement,getphone,getwhatsapp1,getwhatsapp2,getindication,getcodepostal,getsiteinternet);
            }

        });


    }

    public void sendData(String idEtablissement,String phone,String whatsapp1,String whatsapp2, String indication,String codepostal,String siteinternet) {
        progressBar.setVisibility(View.VISIBLE);
        DataEtablissements dataEtablissements = new DataEtablissements();
        dataEtablissements.setIndicationAdresse(indication);
        dataEtablissements.setCodePostal(codepostal);
        dataEtablissements.setSiteInternet(siteinternet);


        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<Etablissements> call = apiService.updateetablissements(API_KEY,"Bearer "+pref.getToken(),dataEtablissements,Integer.parseInt(idEtablissement));
            call.enqueue(new Callback<Etablissements>() {
                @Override
                public void onResponse(@NotNull Call<Etablissements> call, @NotNull Response<Etablissements> response) {
                    if(response.code() == 401 || response.code() == 500) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Error Create", Toast.LENGTH_LONG).show();
                    } else {
                        int idEtablissement = response.body().getData().getId();
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        addTelephones(String.valueOf(idEtablissement),phone,whatsapp1,whatsapp2);

                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(NewBusiness2Activity.this, NewBusiness3Activity.class);
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


    public void addTelephones(String idEtablissement,String phone,String whatsapp1,String whatsapp2) {
            uploadPhone(phone,idEtablissement,1);
            uploadPhone(whatsapp1,idEtablissement,0);
        if(!TextUtils.isEmpty(whatsapp2)) {
            uploadPhone(whatsapp2,idEtablissement,0);
        }
    }

    public void uploadPhone(String phone,String idEtablissement,int principal) {
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
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
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


}
