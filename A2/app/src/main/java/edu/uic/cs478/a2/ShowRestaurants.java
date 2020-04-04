package edu.uic.cs478.a2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ShowRestaurants extends MainActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurants_activity);

        Log.i("Restaurants", " Clicked Restaurants");
    }
}
