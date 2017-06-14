package com.bakeli.mobilebanking.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.UUID;

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

        SharedPreferences settings = getSharedPreferences("info", Context.MODE_PRIVATE);
        final String prenom1 = settings.getString("prenom", "");
        final String nom1 = settings.getString("nom", "");
        final String login1 = settings.getString("log", "");
        final String password1 = settings.getString("pass", "");
        final String type1 = settings.getString("type", "");
        final String solde1 = settings.getString("solde", "");

        etCompte.setText(type1);
        etLogin.setText(login1);
        etPassword.setText(password1);
        etSolde.setText(solde1);
        etNom.setText(nom1);
        etPrenom.setText(prenom1);

        bSinscrire.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                //DatabaseManager db = new DatabaseManager();

               // Account ac = db.addAccount(etCompte.getText().toString(),etSolde.getText().toString());
                //String id = ac.getId();

                String prenom = etPrenom.getText().toString();
                String nom = etNom.getText().toString();
                String login = etLogin.getText().toString();
                String password = etPassword.getText().toString();
                String type = etCompte.getText().toString();
                String solde = etSolde.getText().toString();



                //db.addUser(login,password,prenom,nom,ac.getId());*/
                //db.addAccount(etCompte.getText().toString(),etSolde.getText().toString(),login+password);
                if(prenom.isEmpty()) {
                    Toast.makeText(InsertUser.this, "Le champ prenom est obligatoire!!!", Toast.LENGTH_SHORT).show();
                }else if(nom.isEmpty()) {
                    Toast.makeText(InsertUser.this, "Le champ nom est obligatoire!!!", Toast.LENGTH_SHORT).show();
                }else if(login.isEmpty()) {
                    Toast.makeText(InsertUser.this, "Le champ login est obligatoire!!!", Toast.LENGTH_SHORT).show();
                }else if(password.isEmpty()) {
                    Toast.makeText(InsertUser.this, "Le champ password est obligatoire!!!", Toast.LENGTH_SHORT).show();
                }else if(type.isEmpty()) {
                    Toast.makeText(InsertUser.this, "Veuillez definir un type de compte!!!", Toast.LENGTH_SHORT).show();
                }else if(solde.isEmpty()) {
                    Toast.makeText(InsertUser.this, "Un solde de depart est requis!!!", Toast.LENGTH_SHORT).show();
                }else{

                        SharedPreferences sharedpreferences = getSharedPreferences("info", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("prenom", prenom);
                        editor.putString("nom", nom);
                        editor.putString("log", login);
                        editor.putString("pass", password);
                        editor.putString("type", type);
                        editor.putString("solde", solde);
                        editor.commit();

                        Intent intent = new Intent(getApplicationContext(), DisplayInfo.class);
                        InsertUser.this.startActivity(intent);

                    }
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
