package com.mokshithvoodarla.tinovationsecurityapp;

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

import java.util.ArrayList;
import java.util.List;

public class TwoFragment extends Fragment implements OnChartGestureListener{
    PieChart mChart;
    LineChart nLineChart;

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
        mChart = (PieChart) getView().findViewById(R.id.pieChart1);
        mChart.setOnChartGestureListener(this);
        mChart.setTouchEnabled(true);
        //mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        mChart.setCenterTextTypeface(Typeface.SERIF);

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        mChart.setHighlightPerTapEnabled(true);
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(18.5f, "9 PM"));
        entries.add(new PieEntry(26.7f, "6 PM"));
        entries.add(new PieEntry(24.0f, "12 PM"));
        entries.add(new PieEntry(30.8f, "10 AM"));

        PieDataSet set = new PieDataSet(entries, "People");
        int[] c = {Color.RED, Color.BLUE, Color.parseColor("#FFC107"), Color.parseColor("#009688")};
        set.setColors(c);
        set.setValueTextColor(Color.WHITE);
        PieData data = new PieData(set);
        mChart.setData(data);
        mChart.invalidate();

        super.onActivityCreated(savedInstanceState);
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