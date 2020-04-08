package edu.uic.cs478.a2;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Type", intent.getStringExtra("type"));

        Log.i("Companion receiver", "Companion Receiver called into action!");
        Toast.makeText(context, "Programmatic receiver in actio33n! ",
                Toast.LENGTH_LONG);

        if(intent.getStringExtra("type").compareTo("1") == 0) {
            Activity att = (Activity) context;
            Intent showAttractions = new Intent(context, ShowAttractions.class);
            showAttractions.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            att.startActivity(showAttractions);
        }
        else if (intent.getStringExtra("type").compareTo("2") == 0){
            Activity rest = (Activity) context;
            Intent showRestaurants = new Intent(context, ShowRestaurants.class);
            showRestaurants.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            rest.startActivity(showRestaurants);
        }
    }
}
