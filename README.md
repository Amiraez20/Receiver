# Lab : Comprendre et Utiliser les BroadcastReceivers sous Android

Ce projet est une démonstration pratique de l'utilisation des `BroadcastReceiver` dans une application Android.

## Objectifs du Projet

- **Comprendre la différence** entre un Receiver enregistré statiquement (dans le `AndroidManifest.xml`) et un Receiver enregistré dynamiquement (dans le code Java/Kotlin).
- **Intercepter des événements système** tels que le changement d'état du mode avion ou le redémarrage complet de l'appareil.
- **Créer et diffuser** des événements personnalisés (Custom Broadcasts) au sein de la même application.

## Composants Principaux

1. **`FlightModeListener` (Receiver Dynamique)** :
   - Détecte l'activation ou la désactivation du mode avion (`ACTION_AIRPLANE_MODE_CHANGED`).
   - Il est enregistré et désenregistré dynamiquement via les boutons de la `MainActivity` afin de préserver la batterie lorsque l'application n'est plus au premier plan.

2. **`DeviceStartupReceiver` (Receiver Statique)** :
   - Écoute l'événement système `BOOT_COMPLETED` (démarrage du téléphone).
   - Ce receiver est déclaré dans le `AndroidManifest.xml` car il doit pouvoir se déclencher même si l'application n'est pas en cours d'exécution.

3. **`PersonalEventReceiver` (Receiver Personnalisé)** :
   - Intercepte une action spécifique définie par l'application (`com.example.receiver.MY_PERSONAL_EVENT`).
   - Utilisé pour la communication interne de l'application.

## Fonctionnement

- **Interface Utilisateur** : L'écran principal (`MainActivity`) propose deux boutons.
  - Le premier bouton permet d'activer ou de désactiver l'écouteur du mode avion (`FlightModeListener`). Le statut actuel de l'écouteur est affiché à l'écran.
  - Le second bouton déclenche la diffusion d'un broadcast personnalisé, qui est ensuite capté par le `PersonalEventReceiver` et affiché sous forme de Toast.

- **Permissions** : L'application demande la permission `RECEIVE_BOOT_COMPLETED` pour pouvoir utiliser le `DeviceStartupReceiver`.

## Bonnes Pratiques Appliquées

- **Désenregistrement (Unregistering)** : Les receivers dynamiques sont correctement désenregistrés dans la méthode `onDestroy()` de l'Activité pour éviter les fuites de mémoire.
- **Sécurité (Exported)** : Les receivers déclarés dans le manifest utilisent l'attribut `android:exported="false"` (depuis Android 12) pour éviter que des applications tierces ne leur envoient des broadcasts non sollicités, garantissant ainsi une meilleure sécurité.
