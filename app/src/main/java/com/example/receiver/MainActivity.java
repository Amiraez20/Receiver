package com.example.receiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private FlightModeListener flightModeListener;
    private boolean isFlightListenerActive = false;
    private Button btnToggleFlightMode, btnTriggerCustom;
    private TextView tvStatusDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        // Gestion des insets pour l'affichage edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        flightModeListener = new FlightModeListener();
        tvStatusDisplay = findViewById(R.id.tvStatusDisplay);
        btnToggleFlightMode = findViewById(R.id.btnToggleFlightMode);
        btnTriggerCustom = findViewById(R.id.btnTriggerCustom);

        btnToggleFlightMode.setOnClickListener(v -> manageFlightModeListener());
        btnTriggerCustom.setOnClickListener(v -> triggerCustomBroadcast());
    }

    private void manageFlightModeListener() {
        if (!isFlightListenerActive) {
            // Configuration du filtre pour notre écouteur dynamique
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
            
            // On enregistre dynamiquement le receiver
            registerReceiver(flightModeListener, intentFilter);
            isFlightListenerActive = true;
            tvStatusDisplay.setText("Statut de l'écouteur : ACTIF (Dynamique)");
            btnToggleFlightMode.setText("Arrêter l'écoute du mode avion");
        } else {
            // On le désinscrit quand on n'en a plus besoin
            unregisterReceiver(flightModeListener);
            isFlightListenerActive = false;
            tvStatusDisplay.setText("Statut de l'écouteur : DÉSACTIVÉ");
            btnToggleFlightMode.setText("Démarrer l'écoute du mode avion");
        }
    }

    private void triggerCustomBroadcast() {
        Intent intent = new Intent("com.example.receiver.MY_PERSONAL_EVENT");
        intent.putExtra("payload_msg", "Message reçu depuis notre propre broadcast !");
        sendBroadcast(intent); // Diffusion intra-application
        
        Toast.makeText(this, "Le broadcast personnalisé a bien été diffusé !", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        // Prévention des fuites mémoires (memory leaks)
        if (isFlightListenerActive) {
            unregisterReceiver(flightModeListener);
        }
        super.onDestroy();
    }
}