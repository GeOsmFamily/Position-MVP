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
    Spinner city;
    LinearLayout lladdress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_business2);

        String adresse = getIntent().getStringExtra("adresse");


        address = findViewById(R.id.address);


        if(adresse != null) {
            address.setText(adresse);
        }

        lladdress = findViewById(R.id.lladdress);

        lladdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewBusiness2Activity.this, PicklocationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}