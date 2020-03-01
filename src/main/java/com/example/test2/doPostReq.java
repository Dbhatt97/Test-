package com.example.test2;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class doPostReq extends AsyncTask<String,String,String> {

    public static String string;
    @Override
    protected String doInBackground(String... strings) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("mobile_no",MainActivity.phoneNo)
                .build();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url("http://3.6.234.107/api/login")
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}