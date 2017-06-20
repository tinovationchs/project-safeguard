package com.anjanbharadwaj.tinovationprojectsafeguard;

/**
 * Created by Anjan on 11/24/16.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OneFragment extends Fragment{
    List<ProfileInfo> result;
    String username;
    String time;
    String info1 = "";
    String personName = "";
    RecyclerView recList;

    DatabaseReference root;
    ArrayList<String> info;
    ProfileInfo ci;
    ArrayList<String> data = new ArrayList<>();
    Bitmap bitmap;
    ProfileAdapter adapter;
    String sub;
    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        username = getActivity().getIntent().getExtras().get("Username").toString();
        root = FirebaseDatabase.getInstance().getReference().child(username).child("Stream");
        recList = (RecyclerView) getView().findViewById(R.id.cardList);
        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipe_container);
        refreshLayout.setEnabled(true);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshLayout.setRefreshing(false);
            }
        });
        refreshLayout.setEnabled(true);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("An event was added!");
                data = new ArrayList<String>();
                Iterator i = dataSnapshot.getChildren().iterator();
                while(i.hasNext()){
                    data.add(((DataSnapshot)i.next()).getKey());
                }
                if(data.size()!=0) {
                    int index = username.indexOf("@");
                    sub = username.substring(0, index);
                    Log.v("sree", "username == > " + sub);
                    createList(data);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void createList(final ArrayList<String> times) {
        /*
        result = new ArrayList<>();
        for (int j=0; j < times.size(); j++) {
            time = times.get(j);
            if(doIt(time) == 0){ j--;}
            System.out.println(System.currentTimeMillis() + " time: " + time + ",doIt");
        }
        */
        result = new ArrayList<>();
        doIt(times, times.get(0), 0);
    }
    public void doIt(final ArrayList<String> times, final String time1, final int num){
        root.child(time1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(System.currentTimeMillis() + " time: " + time1 + ", onDataChange beginning");
                info1 = dataSnapshot.child("event").getValue().toString();
                //personName = dataSnapshot.child("person").getValue().toString();
                ci = new ProfileInfo();
                ci.name=time1;
                time1.replace('/','|');
                time1.replace(' ', '-');
                ci.description = info1;
                if(info1.contains("Stranger")) {
                    ci.action1 = "Call 911";
                } else{
                    ci.action1 = "Say Hello!";
                }


                int index = username.indexOf("@");
                String sub = username.substring(0, index);
                StorageReference storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://safeguard-82cc4.appspot.com/")
                        .child(sub + "/" + time1 + ".jpg");
                System.out.println(System.currentTimeMillis() + " time: " + time1 + ", before storage");

                final long ONE_MEGABYTE = 1024 * 1024;
                storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        // Data for "images/island.jpg" is returns, use this as needed
                        bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        ci.bitmap = bitmap;
                        result.add(ci);
                        //adapter.notifyDataSetChanged();
                        int num1 = num +1;
                        //Log.v("ANJANPRINT", "num1: " + num1 + ", times.size()-1: " + (times.size()-1));
                        if(num1 < times.size()) {
                            System.out.println("Each Time");
                            doIt(times, times.get(num1), num1);
                        } else {
                            System.out.println("Final");
                            adapter = new ProfileAdapter(result);
                            recList.setAdapter(adapter);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.v("FAIL", "Welp");

                    }
                }).addOnCompleteListener(new OnCompleteListener<byte[]>() {
                    @Override
                    public void onComplete(@NonNull Task<byte[]> task) {

                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}