package com.bakeli.mobilebanking.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bakeli.mobilebanking.R;
import com.bakeli.mobilebanking.database.DatabaseManager;

import io.realm.Realm;

public class Disconnect extends AppCompatActivity {
    Intent intent;
    Realm realm;
    Class dest;
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disconnect);
        realm.init(getApplicationContext());

        SharedPreferences preferences = getSharedPreferences("credentials", 0);
        preferences.edit().remove("login").commit();
        preferences.edit().remove("password").commit();

        SharedPreferences settings = getSharedPreferences("info", Context.MODE_PRIVATE);
        settings.edit().remove("log").commit();
        settings.edit().remove("pass").commit();
        settings.edit().remove("prenom").commit();
        settings.edit().remove("type").commit();
        settings.edit().remove("solde").commit();
        settings.edit().remove("nom").commit();


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Disconnect.this.finish();
                Disconnect.this.closeContextMenu();

            }
        }, SPLASH_DISPLAY_LENGTH);


    }
}
