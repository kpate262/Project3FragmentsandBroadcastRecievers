package edu.uic.cs478.a2;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;



public class ShowAttractions extends MainActivity implements Attractions_Fragment.ListSelectionListener {
    public static String[] attNameArray;
    public static String[] uriArray;

    private Attractions_Fragment attr_Fragment = null;
    private Attractions_WebView_Fragment webView_fragment = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attractions_activity);
        Log.i("Attractions", " Clicked Attractions");

        attNameArray = getResources().getStringArray(R.array.listOfAttractions);
        uriArray = getResources().getStringArray(R.array.listOfRestaurants);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                                                    .beginTransaction();

        attr_Fragment = new Attractions_Fragment();
        webView_fragment = new Attractions_WebView_Fragment();

        fragmentTransaction.replace(R.id.att_names, attr_Fragment);
        //fragmentTransaction.replace(R.id.web_view, webView_fragment);

        fragmentTransaction.commit();


    }

    //@Override
    public void onListSelection(int index) {

    }
}
