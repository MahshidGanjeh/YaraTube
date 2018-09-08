package com.yaratech.yaratube.login.mobilelogin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

    final SmsManager sms = SmsManager.getDefault();

    // TODO: This method is called when the BroadcastReceiver is receiving
    @Override
    public void onReceive(Context context, Intent intent) {
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);

                    String message = currentMessage.getDisplayMessageBody();
                    String senderNumber = currentMessage.getDisplayOriginatingAddress();

                    //replace all the digits in the sms  with ""
                    //so what is left is just the verification code
                    String verificationCode = message.replaceAll("\\D+", "");

                    //to send the code to other fragment or activities
                    Intent intent1 = new Intent
                            ("android.intent.action.SmsReceiver");

                    if (senderNumber.equals("+98200049103")) {
                        intent1.putExtra("verificationCode", verificationCode);
                    }

                    context.sendBroadcast(intent1);
                }
            }

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);
        }
    }
}

