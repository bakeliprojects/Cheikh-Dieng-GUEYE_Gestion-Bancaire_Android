package com.bakeli.mobilebanking.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bakeli.mobilebanking.R;

import io.realm.Realm;

public class Business extends AppCompatActivity {

    Intent intent;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving);
        realm.init(getApplicationContext());

        Button depot = (Button) findViewById(R.id.bDepot);
        Button retrait = (Button) findViewById(R.id.bRetrait);
        Button consultation = (Button) findViewById(R.id.bConsultation);
        Button virement = (Button) findViewById(R.id.bVirement);

        //Action sur bouton depot
        depot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), DepotActivity.class);
                Business.this.startActivity(intent);
            }
        });

        //Action sur bouton retrait
        retrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getApplicationContext(), RetraitActivity.class);
                Business.this.startActivity(intent);

            }
        });

        //Action sur bouton consultation
        consultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getApplicationContext(), ConsultationActivity.class);
                Business.this.startActivity(intent);

            }
        });

        //Action sur bouton virement
        virement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getApplicationContext(), VirementActivity.class);
                Business.this.startActivity(intent);

            }
        });
    }
}
