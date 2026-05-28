package com.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PersonalEventReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // On vérifie que l'action correspond bien à notre événement personnalisé
        if ("com.example.receiver.MY_PERSONAL_EVENT".equals(intent.getAction())) {
            String passedMessage = intent.getStringExtra("payload_msg");
            Toast.makeText(context, "Événement personnalisé capté : " + passedMessage, Toast.LENGTH_LONG).show();
        }
    }
}
