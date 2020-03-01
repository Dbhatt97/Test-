package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.poovam.pinedittextfield.LinePinField;


public class OTP_activity extends AppCompatActivity {

    public static String OTP = "0000";
    TextView mssg, resend;
    LinePinField ED;
    Button ver;
public static String resend_number;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_activity);

         final String ss = this.getResources().getString(R.string.ver);
        resend = (TextView)findViewById(R.id.resend);
        mssg = (TextView) findViewById(R.id.txtmssg);
        ver = (Button) findViewById(R.id.verify);
        ED = (LinePinField) findViewById(R.id.otp);


        mssg.setText("We have sent you an SMS on " + MainActivity.phoneNo + " with 4 digit verification code.");



        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Verifyotp chk = new Verifyotp();
                chk.execute();
                OTP = ED.getText().toString();
           if (Verifyotp.string2 == "false" || OTP.length() != 4) {
               Toast.makeText(getApplicationContext(),
                       "Wrong OTP Try again", Toast.LENGTH_LONG).show();
           } else {


               Intent in = new Intent(OTP_activity.this, Home_Acivity.class);
               startActivity(in);
               Toast.makeText(getApplicationContext(),
                       "User Registered", Toast.LENGTH_LONG).show();

                  }

             }

        });

resend.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        sendSMSMessage();
        Resend_otp res = new Resend_otp();
        res.execute();

    }
});
    }


    protected void sendSMSMessage() {
            resend_number  = MainActivity.phoneNo;

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }

        } else {
            SendTextMsg();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    //   smsManager.sendTextMessage(phoneNo, null, "Hello", null, null);

                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }

    private void SendTextMsg() {
        SmsManager smsManager = SmsManager.getDefault();
        //  smsManager.sendTextMessage(phoneNo, null, "Hello", null, null);

        Toast.makeText(getApplicationContext(), "SMS sent.",
                Toast.LENGTH_LONG).show();
    }


}
