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

public class VirementActivity extends AppCompatActivity {
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virement);
        realm = Realm.getDefaultInstance();

        final EditText mnt = (EditText) findViewById(R.id.etMntVirement);
        final EditText benef = (EditText) findViewById(R.id.etBeneficiaire);
        Button bValiderVirement = (Button) findViewById(R.id.bVirement);
        Button bAnnuler = (Button) findViewById(R.id.bVirementCancel);

        bValiderVirement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseManager db = new DatabaseManager();

                SharedPreferences settings = getSharedPreferences("credentials", Context.MODE_PRIVATE);
                String loginS = settings.getString("login", "");
                String passwordS = settings.getString("password", "");

                User u = db.getByUserByCredentials(realm, loginS,passwordS);


                //Compte du beneficiaire
                Account a1 = db.getByPrimaryKey(realm,benef.getText().toString());
                realm.beginTransaction();
                Account a2 = db.getByPrimaryKey(realm, u.getIdCompte());
                if(a2!=null)
                {
                    if(a1 != null)
                    {
                        if(Integer.parseInt(a2.getSolde()) >= Integer.parseInt(mnt.getText().toString())) {
                            //Credit sur le compte beneficiaire
                            int sommeAjout = Integer.parseInt(a1.getSolde()) + Integer.parseInt(mnt.getText().toString());
                            a1.setSolde(String.valueOf(sommeAjout));

                            //Compte donneur


                            //Debit sur le compte donneur
                            int sommeRetrait = Integer.parseInt(a2.getSolde()) - Integer.parseInt(mnt.getText().toString());
                            a2.setSolde(String.valueOf(sommeRetrait));
                            Toast.makeText(VirementActivity.this, "Operation effectuée avec succès!!!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ConsultationActivity.class);
                            VirementActivity.this.startActivity(intent);
                        }else{
                            Toast.makeText(VirementActivity.this, "Operation Impossible\n Solde Insuffisant!!!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(VirementActivity.this, "Ce compte n'existe pas!!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        VirementActivity.this.startActivity(intent);
                    }
                }else{
                    Toast.makeText(VirementActivity.this, "Cette utilisateur n'existe pas!!!", Toast.LENGTH_SHORT).show();
                }

                realm.commitTransaction();






                /*Toast.makeText(VirementActivity.this,"Beneficiaire :"+ a1.getId()+" "+a1.getSolde(), Toast.LENGTH_LONG).show();
                Toast.makeText(VirementActivity.this,"Donneur :"+ a2.getId()+" "+a2.getSolde(), Toast.LENGTH_LONG).show();*/

            }
        });

        bAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                VirementActivity.this.startActivity(intent);

            }
        });
    }
}
