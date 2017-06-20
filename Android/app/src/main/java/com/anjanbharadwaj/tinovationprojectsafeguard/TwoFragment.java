package com.anjanbharadwaj.tinovationprojectsafeguard;

/**
 * Created by Mokshith on 11/24/16.
 */

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TwoFragment extends Fragment implements OnChartGestureListener{
    PieChart mChart;
    LineChart nLineChart;
    String username;
    List<String> people;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        people = new ArrayList<>();
        username = getActivity().getIntent().getExtras().get("Username").toString();

        final DatabaseReference root = FirebaseDatabase.getInstance().getReference().child(username).child("Stream");
        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator i = dataSnapshot.getChildren().iterator();

                while(i.hasNext()){
                    String k = ((DataSnapshot)i.next()).getKey().toString();

                    String k1 = dataSnapshot.child(k).child("event").getValue().toString();
                    people.add(k1);
                    System.out.println(k1 + "LOLOLOL");

                    /*
                    root.child(k).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Iterator i = dataSnapshot.getChildren().iterator();
                            String k1 = ((DataSnapshot)i.next()).getValue().toString();
                            System.out.println("LOL");
                            people.add(k1);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    */

                }
                if(!i.hasNext()){
                    makechart();

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onActivityCreated(savedInstanceState);
    }
    public void makechart() {
        System.out.println("MAKECHART METHOD");
        int total = people.size();
        Collections.sort(people);
        //Collections.frequency(List, String);
        ArrayList<String> uniquepeople = new ArrayList<>();
        ArrayList<Integer> peoplenum = new ArrayList<>();
        int frequency = 1;
        for(int i = 1; i<people.size(); i++){
            if(i == people.size()-1){
                if(people.get(i).equals(people.get(i-1))){
                    frequency++;

                    uniquepeople.add(people.get(i));
                    peoplenum.add(frequency);
                    break;
                }else{
                    uniquepeople.add(people.get(i-1));
                    peoplenum.add(frequency);

                    uniquepeople.add(people.get(i));
                    peoplenum.add(1);

                    break;
                }
            }
            if(people.get(i).equals(people.get(i-1))){
                frequency++;
            }else{
                uniquepeople.add(people.get(i-1));
                peoplenum.add(frequency);
                frequency = 1;
            }
        }



        mChart = (PieChart) getView().findViewById(R.id.pieChart1);
        mChart.setOnChartGestureListener(this);
        mChart.setTouchEnabled(true);

        mChart.setUsePercentValues(false);
        //mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        mChart.setCenterTextTypeface(Typeface.SERIF);

        mChart.setDrawHoleEnabled(false);
        //mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        //mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        mChart.setHighlightPerTapEnabled(true);
        List<PieEntry> entries = new ArrayList<>();
        for(int i = 0; i<uniquepeople.size(); i++){
            float x = (float) (peoplenum.get(i)) /total * 100;
            System.out.println("PERCENTAGE: " + x + uniquepeople.get(i).replace("arrived", ""));
            entries.add(new PieEntry(x, uniquepeople.get(i).replace("arrived", "")));
        }


        PieDataSet set = new PieDataSet(entries, "People");
        int[] c = {Color.RED, Color.BLUE, Color.parseColor("#FFC107"), Color.parseColor("#009688")};
        set.setColors(c);
        set.setValueTextColor(Color.WHITE);
        PieData data = new PieData(set);
        mChart.setData(data);
        mChart.invalidate();



    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }
}