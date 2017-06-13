package com.bakeli.mobilebanking.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import com.bakeli.mobilebanking.database.DatabaseManager;
import com.bakeli.mobilebanking.models.Account;
import com.bakeli.mobilebanking.models.User;
import com.bakeli.mobilebanking.R;

import io.realm.Realm;

public class InsertUser extends AppCompatActivity {

    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_user);
        realm.init(getApplicationContext());

        final EditText etNom = (EditText) findViewById(R.id.etNom);
        final EditText etPrenom = (EditText) findViewById(R.id.etPrenom);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etLogin = (EditText) findViewById(R.id.etLogin);
        final EditText etCompte = (EditText) findViewById(R.id.etTypeCompte);
        final EditText etSolde = (EditText) findViewById(R.id.etSolde);
        Button bSinscrire = (Button) findViewById(R.id.bSinscrire);
        Button bAnnuler = (Button) findViewById(R.id.bAnnuler);

        bSinscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Account compte = new Account();
                compte.setType(etCompte.getText().toString());
                compte.setSolde(etSolde.getText().toString());*/

                String prenom = etPrenom.getText().toString();
                String nom = etNom.getText().toString();
                String login = etLogin.getText().toString();
                String password = etPassword.getText().toString();
                DatabaseManager db = new DatabaseManager();
                db.addUser(login,password,prenom,nom);
                db.addAccount(etCompte.getText().toString(),etSolde.getText().toString(),login+password);

                Intent LoginIntent = new Intent(getApplicationContext(), DisplayInfo.class);
                InsertUser.this.startActivity(LoginIntent);
            }
        });

        bAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                InsertUser.this.startActivity(LoginIntent);
            }
        });
    }
}
