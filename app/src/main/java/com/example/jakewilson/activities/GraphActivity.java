package com.example.jakewilson.activities;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import com.example.jakewilson.fragments.WaterChartFragment;
import com.example.jakewilson.graphs.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class GraphActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        if (savedInstanceState == null) {
            WaterChartFragment chartFragment =
                    (WaterChartFragment) getFragmentManager().findFragmentById(R.id.chart);

            WaterChartFragment chart2Fragment =
                    (WaterChartFragment) getFragmentManager().findFragmentById(R.id.chart2);
            chartFragment.run();
            chart2Fragment.run();
        }
        setUpTintBar();

    }

    public void setUpTintBar(){
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        if(Build.VERSION.SDK_INT >= 19){
            SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();
            findViewById(android.R.id.content).setPadding(0, config.getPixelInsetTop(true), config.getPixelInsetRight(), config.getPixelInsetBottom());
        }
        tintManager.setTintColor(getResources().getColor(R.color.rachioBlue));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.graph, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
