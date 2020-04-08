package edu.uic.cs478.a1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Type", intent.getStringExtra("type"));
        Toast.makeText(context, "Programmatic receiver in action! ",
                Toast.LENGTH_LONG) ;
    }
}
