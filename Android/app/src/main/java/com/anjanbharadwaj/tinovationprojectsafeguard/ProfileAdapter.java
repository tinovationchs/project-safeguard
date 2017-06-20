package com.anjanbharadwaj.tinovationprojectsafeguard;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private List<ProfileInfo> contactList;
    private String username;
    private String picturename;
    public ProfileAdapter(List<ProfileInfo> contactList) {
        this.contactList = contactList;


    }

    // Create a storage reference from our app
    //private StorageReference storageRef = storage.getReference();

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(final ProfileViewHolder contactViewHolder, int i) {
        ProfileInfo ci = contactList.get(i);
        contactViewHolder.vName.setText(ci.name);
        contactViewHolder.description.setText(ci.description);
        contactViewHolder.imageView.setImageBitmap(ci.bitmap);


//        contactViewHolder.imageView.setImageResource(R.drawable.aki);

        contactViewHolder.vAction1.setText(ci.action1);
        contactViewHolder.vAction1.setClickable(true);
        //contactViewHolder.vAction2.setText(ci.action2);
        //contactViewHolder.vAction2.setClickable(false);
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
        //protected Button vAction2;

        public ProfileViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.name);
            description =  (TextView) v.findViewById(R.id.description);
            imageView = (ImageView)  v.findViewById(R.id.imageView);
            vAction1 = (Button)  v.findViewById(R.id.btnOne);
            vAction1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*
                    Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                    smsIntent.setType("vnd.android-dir/mms-sms");
                    //smsIntent.putExtra("address", "12125551212");
                    //smsIntent.putExtra("sms_body","Hey! I see you outside my house! Come on in!");
                    view.getContext().startActivity(smsIntent);
                    */
                    boolean hasfb = false;
                    try {
                        view.getContext().getPackageManager().getApplicationInfo("com.facebook.orca", 0);
                        hasfb = true;
                    }
                    catch (PackageManager.NameNotFoundException e) {
                        hasfb = false;
                    }

                    if (!hasfb) {
                        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                        smsIntent.setType("vnd.android-dir/mms-sms");
                        //smsIntent.putExtra("address", "12125551212");
                        //smsIntent.putExtra("sms_body","Hey! I see you outside my house! Come on in!");
                        view.getContext().startActivity(smsIntent);
                    } else {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT, "Hey! I see you outside my house! Come on in!");
                        //intent.putExtra(Intent.EXTRA_USER, "Aki Praveen");

                        intent.setType("text/plain");
                        intent.setPackage("com.facebook.orca");

                        try
                        {
                            view.getContext().startActivity(intent);
                        }
                        catch (ActivityNotFoundException ex)
                        {
                            Log.v("yo", ex.getMessage());
                        }
                    }

                }
            });




            //vAction2 = (Button) v.findViewById(R.id.btnTwo);
        }

    }

}