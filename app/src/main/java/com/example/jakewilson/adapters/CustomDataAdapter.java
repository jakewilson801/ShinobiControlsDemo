package com.example.jakewilson.adapters;

import com.shinobicontrols.charts.Data;
import com.shinobicontrols.charts.DataAdapter;

/**
 * Created by jakewilson on 8/6/14.
 */
public class CustomDataAdapter<Tx, Ty> extends DataAdapter<Tx, Ty> {

    // We'll keep track of how many chart updates we have pending
    private int pendingUpdates = 0;

    @Override
    public boolean add(Data<Tx, Ty> dataPoint) {
        // Call the parent implementation
        boolean added = super.add(dataPoint);

        // Update the number of pending updates
        if (added) {
            pendingUpdates++;
        }

        if (pendingUpdates >= 10) {
            // If we have 10 updates pending, we fire the update handler.
            // Note that until this method is called, the chart will NOT be
            // updated!
            fireUpdateHandler();
            pendingUpdates = 0;
        }

        return added;
    }
}
