package com.nmanoogian.odin;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
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


public class OdinMainActivity extends ActionBarActivity implements ValhallaAsyncDelegate {
    private static final int RESULT_SETTINGS = 1;
    private Button garageButton;
    private Button offFanButton;
    private Button highFanButton;
    private Button lowFanButton;
    private Button lightButton;
    private Button refreshButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ValhallaAPIManager.setApiKey(preferences.getString("prefAuthKey", ""));

        setContentView(R.layout.activity_odin_main);
        this.garageButton = (Button) findViewById(R.id.garage_button);
        this.garageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ValhallaAPIManager.toggleGarage(OdinMainActivity.this);
                OdinMainActivity.this.garageButton.setEnabled(false);
            }
        });

        this.lightButton = (Button) findViewById(R.id.toggle_light_button);
        this.lightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ValhallaAPIManager.toggleLight(OdinMainActivity.this);
                OdinMainActivity.this.lightButton.setEnabled(false);
            }
        });

        this.offFanButton = (Button) findViewById(R.id.off_fan_button);
        this.offFanButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ValhallaAPIManager.setOffFan(OdinMainActivity.this);
                OdinMainActivity.this.offFanButton.setEnabled(false);
            }
        });

        this.lowFanButton = (Button) findViewById(R.id.low_fan_button);
        this.lowFanButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ValhallaAPIManager.setLowFan(OdinMainActivity.this);
                OdinMainActivity.this.lowFanButton.setEnabled(false);

            }
        });

        this.highFanButton = (Button) findViewById(R.id.high_fan_button);
        this.highFanButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ValhallaAPIManager.setHighFan(OdinMainActivity.this);
                OdinMainActivity.this.highFanButton.setEnabled(false);
            }
        });

        this.refreshButton = (Button) findViewById(R.id.refresh_button);
        this.refreshButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ValhallaAPIManager.refresh(OdinMainActivity.this);
                OdinMainActivity.this.refreshButton.setEnabled(false);
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

    @Override
    public void didFinishTask(ValhallaResponse response)
    {

        // Reset buttons
        this.garageButton.setEnabled(true);
        this.offFanButton.setEnabled(true);
        this.lowFanButton.setEnabled(true);
        this.highFanButton.setEnabled(true);
        this.refreshButton.setEnabled(true);
        this.lightButton.setEnabled(true);
        
        // Null response means some kind of failure
        if (response == null )
        {
            Toast.makeText(getApplicationContext(), "There was a problem.", Toast.LENGTH_LONG).show();
            return;
        }

        // Success

        // We got a response back
        if (response.getResponse() != null)
        {
            Toast.makeText(getApplicationContext(), response.getResponse(), Toast.LENGTH_LONG).show();
        }

        // Request reached server, but then there was a problem
        if (!response.isSuccess()) return;

        // Just data
        if (response.isLightOn())
        {
            this.lightButton.setBackgroundColor(Color.YELLOW);
        }
        else
        {
            this.lightButton.setBackgroundColor(Color.LTGRAY);
        }

        switch (response.getFanStatus())
        {
            case 0:
                this.offFanButton.setTypeface(null, Typeface.BOLD);
                this.lowFanButton.setTypeface(null, Typeface.NORMAL);
                this.highFanButton.setTypeface(null, Typeface.NORMAL);
                break;
            case 1:
                this.offFanButton.setTypeface(null, Typeface.NORMAL);
                this.lowFanButton.setTypeface(null, Typeface.BOLD);
                this.highFanButton.setTypeface(null, Typeface.NORMAL);
                break;
            case 3:
                this.offFanButton.setTypeface(null, Typeface.NORMAL);
                this.lowFanButton.setTypeface(null, Typeface.NORMAL);
                this.highFanButton.setTypeface(null, Typeface.BOLD);
                break;
            default:
                this.offFanButton.setTypeface(null, Typeface.NORMAL);
                this.lowFanButton.setTypeface(null, Typeface.NORMAL);
                this.highFanButton.setTypeface(null, Typeface.NORMAL);
                break;

        }

    }
}
