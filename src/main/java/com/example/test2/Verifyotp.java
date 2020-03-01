package com.example.test2;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class Verifyotp extends AsyncTask<String, String, String> {


public static String string,string2;
    @Override
    protected String doInBackground(String... strings) {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("mobile_no", MainActivity.phoneNo)
                .addFormDataPart("otp",OTP_activity.OTP)
                .build();
        Request request = new Request.Builder()
                .url("http://3.6.234.107/api/verifyotp")
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .build();

        try {
         Response response = client.newCall(request).execute();
            string = response.body().string();
            JSONObject jsonObject = new JSONObject(string);
            string2  = jsonObject.getString("result");

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }


}
