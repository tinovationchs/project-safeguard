package com.anjanbharadwaj.tinovationprojectsafeguard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText name;
    EditText pass;
    String nametomatch;
    String passwordtomatch;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        login = (Button)findViewById(R.id.btn_login);
        name = (EditText)findViewById(R.id.input_email);
        pass = (EditText)findViewById(R.id.input_password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Log in initiated");
                root.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        nametomatch = name.getText().toString();
                        passwordtomatch = pass.getText().toString();

                        nametomatch = "thisisanjan@gmail.com";
                        passwordtomatch = "hellohello";

                        ArrayList<String> allusers = new ArrayList<String>();
                        Iterator i = dataSnapshot.getChildren().iterator();
                        while(i.hasNext()){
                            allusers.add(((DataSnapshot)i.next()).getKey());
                        }
                        nametomatch = nametomatch.replace('.','|');
                        if(allusers.contains(nametomatch)){


                            Iterator i1 = dataSnapshot.child(nametomatch).getChildren().iterator();
                            while(i1.hasNext()) {
                                DataSnapshot s = ((DataSnapshot) i1.next());

                                String key = s.getKey().toString();
                                System.out.println(key);
                                if (key.equals("password")) {
                                    if (passwordtomatch.equals(s.getValue().toString())) {
                                        System.out.println(passwordtomatch);
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        intent.putExtra("Username", nametomatch);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "One of the required fields is incorrect. Please try again.", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }

                        }else{
                            Toast.makeText(getApplicationContext(), "One of the required fields is incorrect. Please try again.", Toast.LENGTH_SHORT).show();

                        }



                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
    public void launchSignUpActivity(View v){
        finish();
        Intent i = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(i);
    }

}
