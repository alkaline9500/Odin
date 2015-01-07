package com.nmanoogian.odin;

/**
 * ValhallaAPIManager
 * Manages API calls
 * Created by nicmanoogian on 1/6/15.
 */
public class ValhallaAPIManager
{
    final static String API_URL = "https://bluefile.org/share/valhalla.php";
    final static String API_KEY_NAME = "valhalla_key";
    static String apiKey = "";

    public static void setApiKey(String apiKey)
    {
        ValhallaAPIManager.apiKey = apiKey;
    }

    public static boolean apiKeyIsSet()
    {
        return !apiKey.equals("");
    }

    public static void toggleGarage(ValhallaAsyncDelegate delegate)
    {
        new ValhallaAsyncTask("garage", delegate).execute();
    }

    public static void setOffFan(ValhallaAsyncDelegate delegate)
    {
        new ValhallaAsyncTask("fanoff", delegate).execute();
    }

    public static void setLowFan(ValhallaAsyncDelegate delegate)
    {
        new ValhallaAsyncTask("fanlow", delegate).execute();
    }

    public static void setHighFan(ValhallaAsyncDelegate delegate)
    {
        new ValhallaAsyncTask("fanhigh", delegate).execute();
    }

    public static void toggleLight(ValhallaAsyncDelegate delegate)
    {
        new ValhallaAsyncTask("toggle", delegate).execute();
    }

    public static void refresh(ValhallaAsyncDelegate delegate)
    {
        new ValhallaAsyncTask(null, delegate).execute();
    }
}