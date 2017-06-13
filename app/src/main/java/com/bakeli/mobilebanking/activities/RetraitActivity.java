package com.bakeli.mobilebanking.activities;

import android.content.Intent;
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

public class RetraitActivity extends AppCompatActivity {
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrait);
        realm = Realm.getDefaultInstance();

        final EditText mntDepot = (EditText) findViewById(R.id.etMntDepot);
        Button bValiderRetrait = (Button) findViewById(R.id.bRetrait);

        bValiderRetrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseManager db = new DatabaseManager();
                RealmList<User> users = new RealmList<>();
                RealmList <Account> accounts = new RealmList<>();

                accounts.addAll(db.getAllAccounts().subList(0,db.getAllAccounts().size()));
                users.addAll(db.getAllUsers().subList(0, db.getAllUsers().size()));
                for(int i = 0; i < users.size(); i++)
                {
                    for(int j = 0; j<accounts.size(); j++) {
                        if(users.get(i).getId().equals(accounts.get(j).getId())) {
                            realm.beginTransaction();
                            if(Integer.parseInt(accounts.get(j).getSolde()) >= Integer.parseInt(mntDepot.getText().toString()))
                            {
                                int somme = Integer.parseInt(accounts.get(j).getSolde()) - Integer.parseInt(mntDepot.getText().toString());
                                accounts.get(j).setSolde(String.valueOf(somme));
                                Intent intent = new Intent(getApplicationContext(), ConsultationActivity.class);
                                RetraitActivity.this.startActivity(intent);
                            }else{
                                Toast.makeText(RetraitActivity.this, "Votre Solde est insuffisant!!!", Toast.LENGTH_SHORT).show();
                            }

                            realm.commitTransaction();
                        }
                    }

                }

            }
        });
    }
}
