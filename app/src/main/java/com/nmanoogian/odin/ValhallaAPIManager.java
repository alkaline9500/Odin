package com.nmanoogian.odin;


import android.widget.Toast;

/**
 * ValhallaAPIManager
 * Manages API calls
 * Created by nicmanoogian on 1/6/15.
 */
public class ValhallaAPIManager
{
    final static String API_URL = "https://bluefile.org/share/valhalla.php";
    final static String API_KEY_NAME = "valhalla_key";

    public static void toggleGarage()
    {
        new ValhallaAsyncTask("garage").execute();
    }

    public static void setOffFan()
    {
        new ValhallaAsyncTask("fanoff").execute();
    }

    public static void setLowFan()
    {
        new ValhallaAsyncTask("fanlow").execute();
    }

    public static void setHighFan()
    {
        new ValhallaAsyncTask("fanhigh").execute();
    }

    public static void toggleLight()
    {
        new ValhallaAsyncTask("toggle").execute();
    }
}