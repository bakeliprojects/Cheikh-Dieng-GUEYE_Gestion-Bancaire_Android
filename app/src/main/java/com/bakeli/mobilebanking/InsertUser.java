package com.bakeli.mobilebanking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import Models.User;
import io.realm.Realm;

public class InsertUser extends AppCompatActivity {

    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_user);

        final EditText etNom = (EditText) findViewById(R.id.etNom);
        final EditText etPrenom = (EditText) findViewById(R.id.etPrenom);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etLogin = (EditText) findViewById(R.id.etLogin);
        Button bSinscrire = (Button) findViewById(R.id.bSinscrire);
        Button bAnnuler = (Button) findViewById(R.id.bAnnuler);

        bSinscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realm.beginTransaction();

                User user = realm.createObject(User.class);
                user.setId(new Date().toString());
                user.setNom(etNom.getText().toString());
                user.setPrenom(etPrenom.getText().toString());
                user.setLogin(etLogin.getText().toString());
                user.setPassword(etPassword.getText().toString());

                Intent LoginIntent = new Intent(getApplicationContext(), DisplayInfo.class);
                InsertUser.this.startActivity(LoginIntent);

                realm.commitTransaction();

            }
        });
    }
}
