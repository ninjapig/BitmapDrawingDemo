package com.example.maptest;

import android.annotation.TargetApi;
import android.net.http.AndroidHttpClient;
import android.os.Build;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import java.util.Scanner;



import java.io.IOException;

public class GetPage implements Runnable{
    private final static String TAG = "GET_PAGE";

    @TargetApi(Build.VERSION_CODES.FROYO)
    @Override
    public void run() {
        AndroidHttpClient client = AndroidHttpClient.newInstance(null);
        HttpParams myParams = client.getParams();
        HttpConnectionParams.setConnectionTimeout(myParams, 10000);
        HttpConnectionParams.setSoTimeout(myParams, 10000);
        String url = "http://www.json-generator.com/j/ccTKZaFvqW?indent=4";

        try {
            HttpResponse response = client.execute(new HttpGet(url));
            HttpEntity entity = response.getEntity();
            Log.d(TAG, new Scanner(entity.getContent()).useDelimiter("\\A").next());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
