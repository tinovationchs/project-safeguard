package com.mokshithvoodarla.tinovationsecurityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
    }
    public void launchLoginActivity(View v){
        finish();
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }
}
