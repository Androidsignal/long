package com.example.akshar.largejson;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncLoader extends AsyncTask<Void, Void, String> {

    Onacysnkloader onacysnkloader;
    ListView listView;
    String urldata;
    ProgressDialog progressDialog;
    Context context;

//  public AsyncLoader(Context context,Onacysnkloader onacysnkloader, String urldata ) {
//    this.context = context;
//    this.onacysnkloader = onacysnkloader;
//    this.urldata = urldata;
//
//  }

    public AsyncLoader(Context context, String urldata, Onacysnkloader onacysnkloader) {
        this.context = context;
        this.urldata = urldata;
        this.onacysnkloader = onacysnkloader;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("wait");
        progressDialog.setMessage("plz wait");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        String result = "";

        URL url = null;
        try {
            url = new URL(urldata);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            if (httpURLConnection.getResponseCode() == 200) {
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }



    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        onacysnkloader.Onresult(s);
        progressDialog.dismiss();
    }
}