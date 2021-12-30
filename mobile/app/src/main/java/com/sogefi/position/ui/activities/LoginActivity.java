package com.sogefi.position.ui.activities;

import static com.sogefi.position.utils.Constants.API_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sogefi.position.R;
import com.sogefi.position.api.APIClient;
import com.sogefi.position.api.ApiInterface;
import com.sogefi.position.models.UserModel;
import com.sogefi.position.utils.Function;
import com.sogefi.position.utils.PreferenceManager;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText email;
    EditText password;
    Button forgot;
    PreferenceManager pref;
    ProgressBar progressBarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = new PreferenceManager(this);

        login = findViewById(R.id.group89_button);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        forgot = findViewById(R.id.forgot_password_button);
        progressBarLogin = findViewById(R.id.progressBarLogin);

        login.setOnClickListener(v -> login());
    }

    public void login() {
        progressBarLogin.setVisibility(View.VISIBLE);
        if(email.getText().toString().equals("") || password.getText().toString().equals("") ) {
            Toast.makeText(getApplicationContext(),getString(R.string.fill),Toast.LENGTH_LONG).show();
            progressBarLogin.setVisibility(View.GONE);
        } else {
            final UserModel login = new UserModel(email.getText().toString(), password.getText().toString());

            if (Function.isNetworkAvailable(getApplicationContext())) {
                ApiInterface apiService =
                        APIClient.getNewClient3().create(ApiInterface.class);
                Call<UserModel> call = apiService.login(API_KEY, login);
                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(@NotNull Call<UserModel> call, @NotNull Response<UserModel> response) {
                        if(response.code() == 401) {
                            progressBarLogin.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Error Login", Toast.LENGTH_LONG).show();
                        } else {
                            pref.setToken(response.body().getData().getToken());
                            Log.d("Token", pref.getToken());
                            pref.setRoleid(response.body().getData().getUser().getRole().toString());
                            getUsers(pref.getToken());
                            progressBarLogin.setVisibility(View.GONE);
                            Intent intent = new Intent(LoginActivity.this, SplashActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }

                    @Override
                    public void onFailure(@NotNull Call<UserModel> call, @NotNull Throwable t) {
                        // Log error here since request failed
                        progressBarLogin.setVisibility(View.GONE);
                        Timber.tag("login").e(t.toString());
                        Toast.makeText(getApplicationContext(), "Error login", Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                progressBarLogin.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void getUsers(String token) {
        if (Function.isNetworkAvailable(getApplicationContext())) {
            ApiInterface apiService =
                    APIClient.getNewClient3().create(ApiInterface.class);
            Call<UserModel> call = apiService.getUser( API_KEY,"Bearer "+token);
            call.enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(@NotNull Call<UserModel> call, @NotNull Response<UserModel> response) {
                    if(response.code() == 401) {
                        Toast.makeText(getApplicationContext(), "Error get User", Toast.LENGTH_LONG).show();
                    } else {
                        pref.setEmail(response.body().getData().getUser(). getEmail());
                        pref.setName(response.body().getData().getUser().getName());
                        pref.setPhone(response.body().getData().getUser().getPhone().toString());
                        pref.setId(response.body().getData().getUser().getCommercial().getId().toString());


                        if(response.body().getData().getUser().getCommercial().getImageProfil() != null) {
                            pref.setProfileimage(response.body().getData().getUser().getCommercial().getImageProfil());
                        } else {
                            pref.setProfileimage("");
                        }
                        pref.setActive(response.body().getData().getUser().getCommercial().getActif().toString());
                        pref.setRoleid(response.body().getData().getUser().getRole().toString());
                        pref.setZoneid(response.body().getData().getUser().getCommercial().getIdZone().toString());
                     //   Toast.makeText(getApplicationContext(), response.body().getData().getUser().getRole().toString(), Toast.LENGTH_LONG).show();

                    }

                }

                @Override
                public void onFailure(@NotNull Call<UserModel> call, @NotNull Throwable t) {
                    // Log error here since request failed
                    progressBarLogin.setVisibility(View.GONE);
                    Timber.tag("users").e(t.toString());
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            progressBarLogin.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }
    }
}