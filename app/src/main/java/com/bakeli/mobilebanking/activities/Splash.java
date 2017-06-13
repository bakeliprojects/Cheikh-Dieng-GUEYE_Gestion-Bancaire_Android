package com.bakeli.mobilebanking.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bakeli.mobilebanking.R;
import com.bakeli.mobilebanking.database.DatabaseManager;
import com.bakeli.mobilebanking.models.User;

import io.realm.Realm;

public class Splash extends AppCompatActivity {
    Intent intent;
    Realm realm;
    Class dest;
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        realm.init(getApplicationContext());

        SharedPreferences settings = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        String loginS = settings.getString("login", "");
        String passwordS = settings.getString("password", "");

        DatabaseManager db = new DatabaseManager();
        Boolean connected = db.login(loginS, passwordS);

        if(connected)
        {
            dest = HomeActivity.class;
        }else{
            dest = LoginActivity.class;
        }

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(Splash.this,dest);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);


    }
}
