package com.cis4500.mtruon02.cis4500wearosdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.complications.ProviderUpdateRequester;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cis4500.mtruon02.cis4500wearosdemo.provider.DemoComplicationProviderService;

public class MainActivity extends WearableActivity {

    private TextView counterString;
    private Button buttonShort;
    //private Button buttonLong;

    private static int count = 0;
    private String counterLabel = "Counted Taps: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counterString = (TextView) findViewById(R.id.counter);
        counterString.setText(counterLabel + count);

        /* TASK A-2: Setup the Wear OS Application */
        buttonShort = (Button) findViewById(R.id.button_short);
        buttonShort.setOnClickListener(v -> {
            count += 1;

            /* SharedPreferences is an arbitrary method to share data between services and applications
             * across the system. We insert the counted taps into a key-pair value to be collected by
             * the complication data provider. */
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("count-cps", String.valueOf(count));
            editor.commit();
            counterString.setText(counterLabel + count);

            /* TASK A-2 */

        });


        /* Task B - Extension and Application */
//        buttonLong.setOnClickListener(v -> {
//        });


        // Enables Always-on
        setAmbientEnabled();
    }

}
