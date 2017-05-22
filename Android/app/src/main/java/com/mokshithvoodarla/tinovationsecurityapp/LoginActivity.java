package com.mokshithvoodarla.tinovationsecurityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
    }
    public void launchSignUpActivity(View v){
        finish();
        Intent i = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(i);
    }
    public void signIn(View v){
        EditText email = (EditText) findViewById(R.id.input_email);
        EditText pass = (EditText) findViewById(R.id.input_password);
        if(email.getText().toString().equals("hi@g.c")&&pass.getText().toString().equals("hi")){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }else {
            Toast.makeText(getApplicationContext(),"Email: hi@g.c, Password: hi",Toast.LENGTH_LONG).show();
        }
    }
}
