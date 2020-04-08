package edu.uic.cs478.a1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button attButton;
    private Button restButton;

    private BroadcastReceiver mReceiver ;
    private IntentFilter mFilter ;

    private static final String SIGNATURE =
            "edu.uic.cs478.sp2020.project3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, "edu.uic.cs478.sp2020.project3") !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{"edu.uic.cs478.sp2020.project3"}, 0);
        }

        if (ContextCompat.checkSelfPermission(this, "edu.uic.cs478.sp2020.project3") ==
                PackageManager.PERMISSION_GRANTED) {
            attButton = (Button) findViewById(R.id.attractions);
            restButton = (Button) findViewById(R.id.restaurants);

            attButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Toast.makeText(v.getContext(), "Chicago, here I come!!!",
                            Toast.LENGTH_LONG).show() ;
                    Intent attIntent = new Intent(SIGNATURE);
                    attIntent.putExtra("type", "1");
                    sendBroadcast(attIntent);
                    Intent launchIntent = getPackageManager()
                            .getLaunchIntentForPackage("edu.uic.cs478.a2");

                    if (launchIntent != null) {
                        //attIntent.setClassName("edu.uic.cs478.a2", "edu.uic.cs478.a2.MainActivity");
                        launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(launchIntent);
                    } else {
                        Toast.makeText(MainActivity.this, "There is no package available in android", Toast.LENGTH_LONG).show();
                    }
                }
            });


            restButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Where the food at???",
                            Toast.LENGTH_LONG).show() ;
                    Intent restIntent = new Intent(SIGNATURE);
                    restIntent.putExtra("type", "2");
                    sendBroadcast(restIntent);
                    Intent launchIntent = getPackageManager()
                            .getLaunchIntentForPackage("edu.uic.cs478.a2");

                    if (launchIntent != null) {
                        launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(launchIntent);
                    } else {
                        Toast.makeText(MainActivity.this, "There is no package available in android", Toast.LENGTH_LONG).show();
                    }
                }
            });


            mReceiver = new MyReceiver() ;
            mFilter = new IntentFilter(SIGNATURE) ;



            registerReceiver(mReceiver,mFilter) ;
        }

    }


    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
