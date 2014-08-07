package com.example.jakewilson.fragments;

import android.os.Bundle;

import com.example.jakewilson.adapters.CustomDataAdapter;
import com.example.jakewilson.graphs.R;
import com.shinobicontrols.charts.ChartFragment;
import com.shinobicontrols.charts.ColumnSeries;
import com.shinobicontrols.charts.DataAdapter;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.DateTimeAxis;
import com.shinobicontrols.charts.NumberAxis;
import com.shinobicontrols.charts.Range;
import com.shinobicontrols.charts.SeriesStyle;
import com.shinobicontrols.charts.ShinobiChart;

import java.util.Random;

/**
 * Created by jakewilson on 8/6/14.
 */
public class WaterChartFragment extends ChartFragment implements Runnable {

    private static final int MAX_DATAPOINTS = 30;
    private static final long DELAY = 200;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShinobiChart shinobiChart = getShinobiChart();
        shinobiChart.setLicenseKey("xObzis96bIVP+/uMjAxNDA5MDZqYWNvYi5ncmFudC53aWxzb24uODAxQGdtYWlsLmNvbQ==w6hharquFsdR5x5svBiUDNxVoQOe+Tinj69c/NJUHdUNiERA3oXtTEQHheQcHsnYHT9NZYJBLpgnRAaWsrzurQWkjgx9Brch1/aD77ynB5EMaax2SRdZNQoM6xo3whQ669afaZzi9mj21tdGjL2NT41jO8i4=BQxSUisl3BaWf/7myRmmlIjRnMU2cA7q+/03ZX9wdj30RzapYANf51ee3Pi8m2rVW6aD7t6Hi4Qy5vv9xpaQYXF5T7XzsafhzS3hbBokp36BoJZg8IrceBj742nQajYyV7trx5GIw9jy/V6r0bvctKYwTim7Kzq+YPWGMtqtQoU=PFJTQUtleVZhbHVlPjxNb2R1bHVzPnh6YlRrc2dYWWJvQUh5VGR6dkNzQXUrUVAxQnM5b2VrZUxxZVdacnRFbUx3OHZlWStBK3pteXg4NGpJbFkzT2hGdlNYbHZDSjlKVGZQTTF4S2ZweWZBVXBGeXgxRnVBMThOcDNETUxXR1JJbTJ6WXA3a1YyMEdYZGU3RnJyTHZjdGhIbW1BZ21PTTdwMFBsNWlSKzNVMDg5M1N4b2hCZlJ5RHdEeE9vdDNlMD08L01vZHVsdXM+PEV4cG9uZW50PkFRQUI8L0V4cG9uZW50PjwvUlNBS2V5VmFsdWU+");
        switch (this.getId()) {
            case R.id.chart:
                shinobiChart.setTitle("Daily Minutes");
                break;
            case R.id.chart2:
                shinobiChart.setTitle("Daily Water Use");
                break;
        }



        NumberAxis xAxis = new NumberAxis();


        xAxis.enableGesturePanning(true);
        shinobiChart.setXAxis(xAxis);
        //xAxis.getStyle().setInterSeriesPadding(0.5f);

        NumberAxis yAxis = new NumberAxis();

        shinobiChart.setYAxis(yAxis);

        DateTimeAxis dt = new DateTimeAxis();


        @SuppressWarnings("rawtypes")
        DataAdapter dataAdapter = new CustomDataAdapter<Double, Double>();

        // Create a ColumnSeries and give it the data adapter
        ColumnSeries series = new ColumnSeries();
        series.getStyle().setLineColor(getResources().getColor(R.color.rachioBlue));
        series.getStyle().setAreaColor(getResources().getColor(R.color.rachioBlue));
        series.getStyle().setFillStyle(SeriesStyle.FillStyle.FLAT);

        series.setDataAdapter(dataAdapter);
        shinobiChart.addSeries(series);


    }

    @Override
    public void onPause() {
        getView().removeCallbacks(this);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        run();
    }

    @Override
    public void run() {
        // Retrieve the data adapter
        @SuppressWarnings("rawtypes")
        DataAdapter dataAdapter = getShinobiChart().getSeries().get(0).getDataAdapter();
        int count = dataAdapter.size();

        Random prng1 = new Random();
        Random prng2 = new Random();
        double d = 0;
        switch (this.getId()) {
            case R.id.chart:
                d = prng1.nextDouble() * 50;
                break;
            case R.id.chart2:
                d = prng2.nextDouble() * 50;
                break;
        }

        // Add a new point to the data adapter
        dataAdapter.add(new DataPoint<Double, Double>((double) count, d));

        // For this demo we're adding 50 data points at 200ms intervals. We get
        // the fragments View so we can call postDelayed on it in order
        // to schedule the run() method to be called again in 200ms.

        if (count < WaterChartFragment.MAX_DATAPOINTS) {

            getView().postDelayed(this,
                    WaterChartFragment.DELAY);
            count++;
        }


    }
}
