package com.bakeli.mobilebanking.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bakeli.mobilebanking.database.DatabaseManager;
import com.bakeli.mobilebanking.models.Account;
import com.bakeli.mobilebanking.models.User;
import com.bakeli.mobilebanking.R;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class DisplayInfo extends AppCompatActivity {

    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);
        realm = Realm.getDefaultInstance();

        TextView prenom = (TextView) findViewById(R.id.tvWritenName);
        TextView nom = (TextView) findViewById(R.id.tvWritenName2);
        TextView login = (TextView) findViewById(R.id.tvWritenLogin);
        TextView password = (TextView) findViewById(R.id.tvWritenPassword);
        TextView type = (TextView) findViewById(R.id.tvWritenType);
        TextView solde = (TextView) findViewById(R.id.tvWritenSolde);
        Button valider = (Button) findViewById(R.id.bValidate);
        Button modifier = (Button) findViewById(R.id.bModifier);

        SharedPreferences settings = getSharedPreferences("info", Context.MODE_PRIVATE);
        final String prenom1 = settings.getString("prenom", "");
        final String nom1 = settings.getString("nom", "");
        final String login1 = settings.getString("log", "");
        final String password1 = settings.getString("pass", "");
        final String type1 = settings.getString("type", "");
        final String solde1 = settings.getString("solde", "");



        prenom.setText(prenom1);
        nom.setText(nom1);
        login.setText(login1);
        password.setText(password1);
        type.setText(type1);
        solde.setText(solde1);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseManager db = new DatabaseManager();

                Account ac = db.addAccount(type1,solde1);

                String id = ac.getId().toString();

                db.addUser(login1,password1,prenom1,nom1,id);

                Toast.makeText(DisplayInfo.this, "Success!!!\n Code Compte : "+id, Toast.LENGTH_LONG).show();

                Intent LoginIntent = new Intent(getApplicationContext(), HomeActivity.class);
                DisplayInfo.this.startActivity(LoginIntent);

                SharedPreferences sharedpreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("login", login1);
                editor.putString("password", password1);
                editor.commit();
            }
        });

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent LoginIntent = new Intent(getApplicationContext(), InsertUser.class);
                DisplayInfo.this.startActivity(LoginIntent);

            }
        });

                }
}


