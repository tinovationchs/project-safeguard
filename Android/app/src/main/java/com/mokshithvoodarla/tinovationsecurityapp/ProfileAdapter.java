package com.mokshithvoodarla.tinovationsecurityapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private List<ProfileInfo> contactList;

    public ProfileAdapter(List<ProfileInfo> contactList) {
        this.contactList = contactList;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder contactViewHolder, int i) {
        ProfileInfo ci = contactList.get(i);
        contactViewHolder.vName.setText(ci.name);
        contactViewHolder.description.setText(ci.description);
        contactViewHolder.imageView.setImageResource(R.drawable.aki);
        contactViewHolder.vAction1.setText(ci.action1);
        contactViewHolder.vAction2.setText(ci.action2);
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new ProfileViewHolder(itemView);
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected TextView description;
        protected ImageView imageView;
        protected Button vAction1;
        protected Button vAction2;

        public ProfileViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.name);
            description =  (TextView) v.findViewById(R.id.description);
            imageView = (ImageView)  v.findViewById(R.id.imageView);
            vAction1 = (Button)  v.findViewById(R.id.btnOne);
            vAction2 = (Button) v.findViewById(R.id.btnTwo);
        }
    }
}