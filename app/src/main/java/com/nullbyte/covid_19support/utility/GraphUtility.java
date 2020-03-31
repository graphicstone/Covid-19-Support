package com.nullbyte.covid_19support.utility;

import android.graphics.Color;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraphUtility {
    public static final void piechart(PieChart piechart, ArrayList<PieEntry> arrayList) {
        piechart.setUsePercentValues(true);
        piechart.getDescription().setEnabled(false);
        piechart.setExtraOffsets(2, 5, 2, 2);
        piechart.setDragDecelerationFrictionCoef(0.95f);
        piechart.setDrawHoleEnabled(true);
        piechart.setHoleColor(Color.WHITE);
        piechart.setTransparentCircleRadius(61f);
        PieDataSet pieDataSet = new PieDataSet(arrayList, " ");
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        int[] colors = {Color.rgb(13, 166, 10), Color.rgb(255, 140, 0)};
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        for (int c : colors) {
            arrayList1.add(c);
        }
        pieDataSet.setColors(arrayList1);
        pieDataSet.setColors(ColorTemplate.createColors(colors));
        PieData pieData = new PieData((pieDataSet));
        pieData.setValueTextSize(18f);
        pieData.setValueTextColor(Color.WHITE);
        piechart.setData(pieData);
        piechart.setCenterTextSize(30f);
        piechart.setDrawEntryLabels(false);
        piechart.animateY(1000, Easing.EaseInOutCubic);

    }

    public static final void barchart(BarChart barChart, ArrayList<BarEntry> arrayList, final ArrayList<String> xAxisValues) {
        barChart.setDrawBarShadow(false);
        barChart.setFitBars(true);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(25);
        barChart.setPinchZoom(true);

        barChart.setDrawGridBackground(true);
        BarDataSet barDataSet = new BarDataSet(arrayList, "Values");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.9f);
        barData.setValueTextSize(0f);

        barChart.setBackgroundColor(Color.TRANSPARENT); //set whatever color you prefer
        barChart.setDrawGridBackground(false);

        Legend l = barChart.getLegend();
        l.setTextSize(0f);
        l.setFormSize(0f);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setTextSize(13f);
        xAxis.setPosition(XAxis.XAxisPosition.TOP_INSIDE);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        xAxis.setDrawGridLines(false);

        barChart.setData(barData);

    }
}

