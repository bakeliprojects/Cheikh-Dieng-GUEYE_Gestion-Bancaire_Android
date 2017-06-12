package com.bakeli.mobilebanking.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bakeli.mobilebanking.R;

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

        bValiderVirement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                

            }
        });
    }
}
