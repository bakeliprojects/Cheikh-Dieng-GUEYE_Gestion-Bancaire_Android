package com.bakeli.mobilebanking.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        final EditText mntDepot = (EditText) findViewById(R.id.etMntRetrait);
        Button bValider = (Button) findViewById(R.id.bVirement);

        bValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseManager db = new DatabaseManager();
                RealmList<User> users = new RealmList<User>();
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

                }

            }
        });


    }
}
