package com.bakeli.mobilebanking.activities;

import android.content.Context;
import android.content.SharedPreferences;
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
        SharedPreferences settings = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        String loginS = settings.getString("login", "");
        String passwordS = settings.getString("password", "");

        User u = db.getByUserByCredentials(realm, loginS,passwordS);


        Account a1 = db.getByPrimaryKey(realm,u.getId());

        solde.setText(a1.getSolde().toString());

    }
}
