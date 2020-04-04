package edu.uic.cs478.a2;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {

    private static final String SIGNATURE =
            "edu.uic.cs478.sp2020.project3";

    BroadcastReceiver mReceiver = new MyReceiver() ;
    IntentFilter mFilter = new IntentFilter(SIGNATURE) ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("type", "inside oncreate()");

        registerReceiver(mReceiver, mFilter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionsmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.attractions:
                Intent showAttractions = new Intent(this, ShowAttractions.class);
                showAttractions.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(showAttractions);
                return true;

            case R.id.restaurants:
                Intent showRestaurants = new Intent(this, ShowRestaurants.class);
                showRestaurants.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(showRestaurants);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }


}
