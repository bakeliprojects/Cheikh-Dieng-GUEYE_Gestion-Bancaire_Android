package com.bakeli.mobilebanking.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bakeli.mobilebanking.R;
import com.bakeli.mobilebanking.database.DatabaseManager;
import com.bakeli.mobilebanking.models.Account;
import com.bakeli.mobilebanking.models.User;

import io.realm.Realm;
import io.realm.RealmList;

public class ConsultationActivity extends AppCompatActivity {
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);
        realm = Realm.getDefaultInstance();

        TextView solde = (TextView) findViewById(R.id.soldeConsultation);

        DatabaseManager db = new DatabaseManager();
        RealmList<User> users = new RealmList<User>();
        RealmList <Account> accounts = new RealmList<Account>();

        accounts.addAll(db.getAllAccounts().subList(0,db.getAllAccounts().size()));
        users.addAll(db.getAllUsers().subList(0, db.getAllUsers().size()));
        for(int i = 0; i < users.size(); i++)
        {
            for(int j = 0; j<accounts.size(); j++) {
                if(users.get(i).getId().equals(accounts.get(j).getId())) {
                    solde.setText(accounts.get(j).getSolde());
                }
            }

        }

    }
}
