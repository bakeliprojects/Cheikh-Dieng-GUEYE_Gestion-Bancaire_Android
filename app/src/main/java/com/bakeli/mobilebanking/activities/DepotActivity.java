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

import com.bakeli.mobilebanking.R;
import com.bakeli.mobilebanking.database.DatabaseManager;
import com.bakeli.mobilebanking.models.Account;
import com.bakeli.mobilebanking.models.User;

import io.realm.Realm;
import io.realm.RealmList;

public class DepotActivity extends AppCompatActivity {
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depot);
        realm = Realm.getDefaultInstance();

        final EditText mntDepot = (EditText) findViewById(R.id.etMntDepot);
        Button bValider = (Button) findViewById(R.id.bDepot);
        Button bAnnuler = (Button) findViewById(R.id.bDepotCancel);

        bValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseManager db = new DatabaseManager();
                realm.beginTransaction();
                SharedPreferences settings = getSharedPreferences("credentials", Context.MODE_PRIVATE);
                 String loginS = settings.getString("login", "");
                 String passwordS = settings.getString("password", "");

                User u = db.getByUserByCredentials(realm, loginS, passwordS);
                Account ac = db.getByPrimaryKey(realm, u.getIdCompte());

                int somme = Integer.parseInt(ac.getSolde()) + Integer.parseInt(mntDepot.getText().toString());
                ac.setSolde(String.valueOf(somme));
                realm.commitTransaction();
                Toast.makeText(DepotActivity.this, "Operation effectué avec succès!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ConsultationActivity.class);
                DepotActivity.this.startActivity(intent);

                /*RealmList<User> users = new RealmList<User>();
                RealmList <Account> accounts = new RealmList<Account>();

                accounts.addAll(db.getAllAccounts().subList(0,db.getAllAccounts().size()));
                users.addAll(db.getAllUsers().subList(0, db.getAllUsers().size()));
                for(int i = 0; i < users.size(); i++)
                {
                    for(int j = 0; j<accounts.size(); j++) {
                        if(users.get(i).getId().equals(accounts.get(j).getId())) {
                            realm.beginTransaction();
                            int somme = Integer.parseInt(accounts.get(j).getSolde()) + Integer.parseInt(mntDepot.getText().toString());
                            accounts.get(j).setSolde(String.valueOf(somme));
                            Intent intent = new Intent(getApplicationContext(), ConsultationActivity.class);
                            DepotActivity.this.startActivity(intent);
                            realm.commitTransaction();
                        }
                    }

                }*/

            }
        });

        bAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                DepotActivity.this.startActivity(intent);

            }
        });


    }
}
