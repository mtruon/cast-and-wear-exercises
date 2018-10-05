package com.cis4500.mtruon02.cis4500wearosdemo.provider;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.wearable.complications.ComplicationData;
import android.support.wearable.complications.ComplicationManager;
import android.support.wearable.complications.ComplicationProviderService;
import android.support.wearable.complications.ComplicationText;
import android.util.Log;

import com.cis4500.mtruon02.cis4500wearosdemo.MainActivity;

import java.util.Locale;

public class DemoComplicationProviderService extends ComplicationProviderService {

    private static final String TAG = "DemoComplicationProviderService";
    private SharedPreferences preferences;


    public void onComplicationActivated(int complicationId, int dataType, ComplicationManager complicationManager) {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    public void onComplicationUpdate(int complicationId, int dataType, ComplicationManager complicationManager) {
        ComplicationData complicationData = null;

        /* Retrieving data (counted taps) from MainActivity. This could be replaced with any method to retrieve
         * data. A relevant scenario would be pulling data from a formal database. */
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String complicationValue = preferences.getString("count-cps", "0");

        // Transform (build) the data to a supported type
        /* TASK A-3 */

        /* Within the current instance of the demo, the complication data should never be null.
         * However, it is good practice to ensure the validity of the complication data. Unknown data types
         * could be received by the complication if set up improperly. This is to safeguard the information and
         * , whilst providing an opportunity to send different data types to the complication. */
        if (complicationData != null) {
            complicationManager.updateComplicationData(complicationId, complicationData);
        } else {
            //If no data has been passed then we should notify the complication manager that no update is required
            complicationManager.noUpdateRequired(complicationId);
        }


    }


    @Override
    public void onComplicationDeactivated(int complicationId) {
    }

}
