package com.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DeviceStartupReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Intercepte l'événement de démarrage complet de l'appareil
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Toast.makeText(context, "L'appareil vient de démarrer ! - Écouteur statique déclenché", Toast.LENGTH_LONG).show();
        }
    }
}
