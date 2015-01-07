package com.nmanoogian.odin;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.transform.Result;

/**
 * ValhallaAsyncTask
 * An async task that manages API calls
 * Created by nicmanoogian on 1/6/15.
 */
public class ValhallaAsyncTask extends AsyncTask<Void, Void, String>
{
    private String command;

    public ValhallaAsyncTask(String command)
    {
        this.command = command;
    }

    @Override
    protected String doInBackground(Void... params)
    {
        ArrayList<BasicNameValuePair> valuePairs = new ArrayList<>(2);
        valuePairs.add(new BasicNameValuePair(ValhallaAPIManager.API_KEY_NAME, ValhallaAPIManager.apiKey));
        valuePairs.add(new BasicNameValuePair("command", this.command));
        HttpResponse response = this.postData(ValhallaAPIManager.API_URL, valuePairs);

        // If there was no response, return null
        if (response == null) {
            return null;
        }
        Scanner respScanner = null;
        try {
            respScanner = new Scanner(response.getEntity().getContent());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // Scan all lines and return the result
        String outString = "";
        while (respScanner.hasNextLine()) {
            outString += respScanner.nextLine();
        }
        return outString;
    }

    public HttpResponse postData(String url, List<BasicNameValuePair> valuePairs)
    {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);

        try {
            httppost.setEntity(new UrlEncodedFormEntity(valuePairs));

            // Execute HTTP Post Request
            return httpclient.execute(httppost);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}