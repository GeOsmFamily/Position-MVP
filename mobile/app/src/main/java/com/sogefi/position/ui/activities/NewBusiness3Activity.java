package com.sogefi.position.ui.activities;

import static com.sogefi.position.utils.Constants.API_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sogefi.position.R;
import com.sogefi.position.api.APIClient;
import com.sogefi.position.api.ApiInterface;
import com.sogefi.position.models.Horaires;
import com.sogefi.position.utils.Function;
import com.sogefi.position.utils.PreferenceManager;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_business3);
        pref = new PreferenceManager(this);

        String idEtablissement = getIntent().getStringExtra("idEtablissement");


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
            } else {

                sendData(idEtablissement,getopenLundi,getclosedLundi,getopenMardi,getclosedMardi,getopenMercredi,getclosedMercredi,getopenJeudi,getclosedJeudi,getopenVendredi,getclosedVendredi,getopenSamedi,getclosedSamedi,getopenDimanche,getclosedDimanche);
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

    public void sendData(String idEtablissement,String openLundi,String closedLundi,String openMardi,String closedMardi,String openMercredi,String closedMercredi,String openJeudi,String closedJeudi,String openVendredi,String closedVendredi,String openSamedi,String closedSamedi,String openDimanche,String closedDimanche) {

        addHoraires(String.valueOf(idEtablissement),openLundi,closedLundi,openMardi,closedMardi,openMercredi,closedMercredi,openJeudi,closedJeudi,openVendredi,closedVendredi,openSamedi,closedSamedi,openDimanche,closedDimanche);

        Intent intent = new Intent(NewBusiness3Activity.this, NewBusiness4Activity.class);
        intent.putExtra("idEtablissement",String.valueOf(idEtablissement));
        startActivity(intent);
        finish();
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
    }


    public void uploadHoraire(String open,String closed,String idEtablissement,String jour) {
        progressBar.setVisibility(View.VISIBLE);

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
                        progressBar.setVisibility(View.GONE);

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