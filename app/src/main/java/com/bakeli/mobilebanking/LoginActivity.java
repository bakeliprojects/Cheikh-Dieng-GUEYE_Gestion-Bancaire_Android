package com.bakeli.mobilebanking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etLogin = (EditText) findViewById(R.id.editText);
        final EditText etPassword = (EditText) findViewById(R.id.editText3);
        final TextView tvSignIn = (TextView) findViewById(R.id.tvSignIn);
        Button bConnect = (Button) findViewById(R.id.button);

        bConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent LoginIntent = new Intent(getApplicationContext(), HomeActivity.class);
                    LoginActivity.this.startActivity(LoginIntent);

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
