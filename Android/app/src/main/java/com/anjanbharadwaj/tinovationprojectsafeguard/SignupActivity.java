package com.anjanbharadwaj.tinovationprojectsafeguard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    Button signup;
    EditText name;
    EditText password;
    String user;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        signup = (Button)findViewById(R.id.btn_signup);
        name = (EditText)findViewById(R.id.input_email);
        password = (EditText)findViewById(R.id.input_password);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = name.getText().toString();
                String userpass = password.getText().toString();

                user = user.replace('.', '|');
                System.out.println(user);
                Map<String, Object> highermap = new HashMap<String, Object>();
                Map<String, Object> lowermap = new HashMap<String, Object>();

                highermap.put(user, "");
                lowermap.put("password", userpass);
                lowermap.put("Stream", "");

                root.updateChildren(highermap);
                root.child(user).updateChildren(lowermap);

                Map<String, Object> streammap = new HashMap<String, Object>();
                Map<String, Object> streammap2 = new HashMap<String, Object>();

                streammap.put("pm1640", "");
                streammap2.put("event", "User " + user.replace("|", ".") + " successfully created");
                root.child(user).child("Stream").updateChildren(streammap);
                root.child(user).child("Stream").child("pm1640").updateChildren(streammap2);

                StorageReference ref = FirebaseStorage.getInstance().getReferenceFromUrl("gs://safeguard-82cc4.appspot.com/")
                        .child("logo.jpg");

                final long ONE_MEGABYTE = 1024 * 1024;
                ref.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        int index = user.indexOf("@");
                        String user2 = user.substring(0, index);
                        StorageReference ref1 = FirebaseStorage.getInstance().getReferenceFromUrl("gs://safeguard-82cc4.appspot.com/")
                                .child(user2).child("pm1640.jpg");
                        UploadTask uploadTask = ref1.putBytes(bytes);
                        uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle unsuccessful uploads
                            }
                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                                //Uri downloadUrl = taskSnapshot.getDownloadUrl();

                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                i.putExtra("Username", user);
                                startActivity(i);
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });


            }
        });
    }
    public void launchLoginActivity(View v){
        finish();
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }
}
