package com.bakeli.mobilebanking.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bakeli.mobilebanking.R;
import com.bakeli.mobilebanking.database.DatabaseManager;

import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {
    Realm realm;
    Intent LoginIntent1;
    Intent LoginIntent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        realm.init(getApplicationContext());

        final EditText etLogin = (EditText) findViewById(R.id.editText);
        final EditText etPassword = (EditText) findViewById(R.id.editText3);
        final TextView tvSignIn = (TextView) findViewById(R.id.tvSignIn);
        Button bConnect = (Button) findViewById(R.id.button);

        bConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = etLogin.getText().toString();
                String password = etPassword.getText().toString();
                DatabaseManager db = new DatabaseManager();
                boolean res = db.login(login, password);
                if(res==true)
                {
                    LoginIntent1 = new Intent(getApplicationContext(), HomeActivity.class);
                    LoginActivity.this.startActivity(LoginIntent1);
                }else{
                    /*LoginIntent2 = new Intent(getApplicationContext(), LoginActivity.class);
                    LoginActivity.this.startActivity(LoginIntent2);*/
                    Toast.makeText(LoginActivity.this, "Veuillez vous connecter!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent LoginIntent = new Intent(getApplicationContext(), InsertUser.class);
                LoginActivity.this.startActivity(LoginIntent);

            }
        });
    }
}
