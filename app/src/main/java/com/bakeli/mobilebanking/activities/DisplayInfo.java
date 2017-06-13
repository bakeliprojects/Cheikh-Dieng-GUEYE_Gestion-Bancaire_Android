package com.bakeli.mobilebanking.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

        TextView prenom1 = (TextView) findViewById(R.id.tvWritenName);
        TextView nom = (TextView) findViewById(R.id.tvWritenName2);
        TextView login = (TextView) findViewById(R.id.tvWritenLogin);
        TextView password = (TextView) findViewById(R.id.tvWritenPassword);
        TextView id = (TextView) findViewById(R.id.tvWritenId);
        TextView type = (TextView) findViewById(R.id.tvWritenType);
        TextView solde = (TextView) findViewById(R.id.tvWritenSolde);

        DatabaseManager db = new DatabaseManager();
        RealmList <User> users = new RealmList<User>();
        RealmList <Account> accounts = new RealmList<Account>();

        accounts.addAll(db.getAllAccounts().subList(0,db.getAllAccounts().size()));
        users.addAll(db.getAllUsers().subList(0, db.getAllUsers().size()));
        for(int i = 0; i < users.size(); i++) {
            for (int j = 0; j < accounts.size(); j++) {
                if (users.get(i).getId().equals(accounts.get(j).getId())) {
                    prenom1.setText(users.get(i).getName());
                    nom.setText(users.get(i).getNom());
                    login.setText(users.get(i).getLogin());
                    password.setText(users.get(i).getPassword());
                    id.setText(users.get(i).getId());
                    type.setText(accounts.get(j).getType());
                    solde.setText(accounts.get(j).getSolde());
                }
            }
        }
    }
}
