package com.nmanoogian.odin;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class OdinMainActivity extends ActionBarActivity {
    private static final int RESULT_SETTINGS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ValhallaAPIManager.setApiKey(preferences.getString("prefAuthKey", ""));

        setContentView(R.layout.activity_odin_main);
        final Button garageButton = (Button) findViewById(R.id.garage_button);
        garageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValhallaAPIManager.apiKeyIsSet()) {
                    ValhallaAPIManager.toggleGarage();
                    Toast.makeText(getApplicationContext(), "Toggled garage door.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "API Key is not set.", Toast.LENGTH_LONG).show();
                }
            }
        });

        final Button lightButton = (Button) findViewById(R.id.toggle_light_button);
        lightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValhallaAPIManager.apiKeyIsSet()) {
                    ValhallaAPIManager.toggleLight();
                    Toast.makeText(getApplicationContext(), "Toggled room light.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "API Key is not set.", Toast.LENGTH_LONG).show();
                }
            }
        });

        final Button offFanButton = (Button) findViewById(R.id.off_fan_button);
        offFanButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValhallaAPIManager.apiKeyIsSet()) {
                    ValhallaAPIManager.setOffFan();
                    Toast.makeText(getApplicationContext(), "Set fan off.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "API Key is not set.", Toast.LENGTH_LONG).show();
                }
            }
        });

        final Button lowFanButton = (Button) findViewById(R.id.low_fan_button);
        lowFanButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValhallaAPIManager.apiKeyIsSet()) {
                    ValhallaAPIManager.setLowFan();
                    Toast.makeText(getApplicationContext(), "Set fan low.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "API Key is not set.", Toast.LENGTH_LONG).show();
                }

            }
        });

        final Button highFanButton = (Button) findViewById(R.id.high_fan_button);
        highFanButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValhallaAPIManager.apiKeyIsSet()) {
                    ValhallaAPIManager.setHighFan();
                    Toast.makeText(getApplicationContext(), "Set fan high.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "API Key is not set.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_odin_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, OdinSettingsActivity.class);
            startActivityForResult(i, RESULT_SETTINGS);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
