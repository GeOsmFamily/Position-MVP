package com.sogefi.position.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.sogefi.position.R;

public class NewBusiness2Activity extends AppCompatActivity {
    TextView address,indication_adresse,code_postal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_business2);

        String idEtablissement = getIntent().getStringExtra("idEtablissement");


    }
}