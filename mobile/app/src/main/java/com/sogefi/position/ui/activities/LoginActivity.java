package com.sogefi.position.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sogefi.position.R;
import com.sogefi.position.api.APIClient;
import com.sogefi.position.api.ApiInterface;
import com.sogefi.position.models.Auth;
import com.sogefi.position.models.Nominatim;
import com.sogefi.position.models.Token;
import com.sogefi.position.models.Users;
import com.sogefi.position.utils.Function;
import com.sogefi.position.utils.PreferenceManager;

import org.jetbrains.annotations.NotNull;

import java.util.Base64;

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
            final Auth login = new Auth(email.getText().toString(), password.getText().toString());

            if (Function.isNetworkAvailable(getApplicationContext())) {
                ApiInterface apiService =
                        APIClient.getNewClient3().create(ApiInterface.class);
                Call<Auth> call = apiService.login(login);
                call.enqueue(new Callback<Auth>() {
                    @Override
                    public void onResponse(@NotNull Call<Auth> call, @NotNull Response<Auth> response) {

                        if(response.code() == 401) {
                            progressBarLogin.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Error Login", Toast.LENGTH_LONG).show();
                        } else {
                            pref.setToken(response.body().getAccessToken());
                            JWT jwt = new JWT(pref.getToken());
                            Integer[] roles_id = jwt.getClaim("roles_id").asArray(Integer.class);
                            pref.setRoleid(roles_id[0].toString());
                            getUsers(pref.getToken());
                            progressBarLogin.setVisibility(View.GONE);
                            Intent intent = new Intent(LoginActivity.this, SplashActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }

                    @Override
                    public void onFailure(@NotNull Call<Auth> call, @NotNull Throwable t) {
                        // Log error here since request failed
                        progressBarLogin.setVisibility(View.GONE);
                        Timber.tag("login").e(t.toString());
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
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
            Call<Users> call = apiService.getUser("Bearer"+token);
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(@NotNull Call<Users> call, @NotNull Response<Users> response) {
                    if(response.code() == 401) {
                        Toast.makeText(getApplicationContext(), "Error get User", Toast.LENGTH_LONG).show();
                    } else {
                        pref.setEmail(response.body().getEmail());
                        pref.setName(response.body().getName());
                        pref.setPhone(response.body().getPhone());
                        pref.setId(response.body().getId().toString());
                    }

                }

                @Override
                public void onFailure(@NotNull Call<Users> call, @NotNull Throwable t) {
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