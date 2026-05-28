package com.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class FlightModeListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Cette méthode s'exécute à chaque détection du changement d'état du mode avion
        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {
            
            // On vérifie si le mode avion vient d'être allumé ou éteint
            boolean isFlightModeActive = intent.getBooleanExtra("state", false);
            
            String feedbackMsg = isFlightModeActive 
                ? "Le mode avion est désormais ACTIF ✈️" 
                : "Le mode avion est INACTIF 📡";
            
            // Affichage rapide de l'état via un Toast
            Toast.makeText(context, feedbackMsg, Toast.LENGTH_LONG).show();
        }
    }
}
