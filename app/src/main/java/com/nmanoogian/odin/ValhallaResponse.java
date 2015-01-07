package com.nmanoogian.odin;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * ValhallaResponse
 * Represents the current state of the Valhalla system
 * Created by nicmanoogian on 1/7/15.
 */
public class ValhallaResponse
{
    private boolean success;
    private boolean lightOn;
    private int lightLevel;
    private double temp;
    private int fanStatus;
    private boolean autoMode;

    public ValhallaResponse(JSONObject jsonObject) throws JSONException
    {
        this.success = jsonObject.getBoolean("success");
        JSONObject dataObject = jsonObject.getJSONObject("data");
        this.lightOn = dataObject.getBoolean("light");
        this.lightLevel = dataObject.getInt("lightAcc");
        this.temp = dataObject.getDouble("temp");
        this.fanStatus = dataObject.getInt("fan");
        this.autoMode = dataObject.getBoolean("auto");
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isLightOn() {
        return lightOn;
    }

    public int getLightLevel() {
        return lightLevel;
    }

    public double getTemp() {
        return temp;
    }

    public int getFanStatus() {
        return fanStatus;
    }

    public boolean isAutoMode() {
        return autoMode;
    }
}
