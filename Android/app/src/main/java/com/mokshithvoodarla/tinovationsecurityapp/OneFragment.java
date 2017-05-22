package com.mokshithvoodarla.tinovationsecurityapp;

/**
 * Created by Mokshith on 11/24/16.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class OneFragment extends Fragment{

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
        RecyclerView recList = (RecyclerView) getView().findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        ProfileAdapter adapter = new ProfileAdapter(createList(21));
        recList.setAdapter(adapter);
    }
    private List<ProfileInfo> createList(int size) {

        List<ProfileInfo> result = new ArrayList<>();
        for (int i=1; i <= size; i++) {
            ProfileInfo ci = new ProfileInfo();
            ci.name="akimeme"+i;
            ci.description="akiisdank"+i;
            ci.action1="eataki"+i;
            ci.action2="eataki"+i;
            result.add(ci);

        }

        return result;
    }
}