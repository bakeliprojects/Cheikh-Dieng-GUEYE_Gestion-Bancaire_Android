package com.bakeli.mobilebanking.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bakeli.mobilebanking.R;

import io.realm.Realm;

public class HomeActivity extends AppCompatActivity {
    Intent savingIntent;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        realm.init(getApplicationContext());

        Button saving = (Button) findViewById(R.id.bEpargne);
        Button checking = (Button) findViewById(R.id.bCourant);
        Button business = (Button) findViewById(R.id.bBusiness);
        Button disconnect = (Button) findViewById(R.id.bDisconnect);

        saving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savingIntent = new Intent(getApplicationContext(), Saving.class);
                HomeActivity.this.startActivity(savingIntent);
            }
        });

        checking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savingIntent = new Intent(getApplicationContext(), Checking.class);
                HomeActivity.this.startActivity(savingIntent);

            }
        });

        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savingIntent = new Intent(getApplicationContext(), Business.class);
                HomeActivity.this.startActivity(savingIntent);
            }
        });

        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savingIntent = new Intent(getApplicationContext(), Disconnect.class);
                //HomeActivity.this.startActivity(savingIntent);
                HomeActivity.this.finish();

            }
        });
    }
}
