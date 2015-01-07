package com.nmanoogian.odin;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

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
public class ValhallaAsyncTask extends AsyncTask<Void, Void, ValhallaResponse>
{
    private String command;
    private ValhallaResponse response;
    private ValhallaAsyncDelegate delegate;

    public ValhallaAsyncTask(String command, ValhallaAsyncDelegate delegate)
    {
        this.command = command;
        this.delegate = delegate;
        this.response = null;
    }

    @Override
    protected ValhallaResponse doInBackground(Void... params)
    {
        ArrayList<BasicNameValuePair> valuePairs = new ArrayList<>(2);
        valuePairs.add(new BasicNameValuePair(ValhallaAPIManager.API_KEY_NAME, ValhallaAPIManager.apiKey));
        if (this.command != null)
        {
            valuePairs.add(new BasicNameValuePair("command", this.command));
        }
        HttpResponse response = this.postData(ValhallaAPIManager.API_URL, valuePairs);

        Scanner respScanner = null;
        try {
            respScanner = new Scanner(response.getEntity().getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Scan all lines and return the result
        String outString = "";
        while (respScanner.hasNextLine()) {
            outString += respScanner.nextLine();
        }

        // Generate and return a response object
        try {
            return new ValhallaResponse(new JSONObject(outString));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // If anything failed, return null
        return null;
    }



    @Override
    protected void onPostExecute(ValhallaResponse valhallaResponse) {
        super.onPostExecute(valhallaResponse);
        this.delegate.didFinishTask(valhallaResponse);
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