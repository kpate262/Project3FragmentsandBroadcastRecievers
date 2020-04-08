package edu.uic.cs478.a2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class ShowRestaurants extends MainActivity implements Restaurants_Fragment.ListSelectionListener{
    public static String[] restNameArray;
    public static String[] restUrlArray;

    private android.support.v4.app.FragmentManager mFragmentManager;
    private Restaurants_Fragment rest_Fragment = null;
    private final Restaurants_WebView_Fragment webView_fragment = new Restaurants_WebView_Fragment();
    private FrameLayout restaurants, restWebLayout;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "ShowAttractions";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.framelayout);
        Log.i("Restaurants", " Clicked Restaurants");

        restNameArray = getResources().getStringArray(R.array.listOfRestaurants);
        restUrlArray = getResources().getStringArray(R.array.restUrls);

        restaurants = (FrameLayout) findViewById(R.id.names);
        restWebLayout = (FrameLayout) findViewById(R.id.webView);

        mFragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = mFragmentManager
                .beginTransaction();

        rest_Fragment = new Restaurants_Fragment();

        fragmentTransaction.replace(R.id.names, rest_Fragment);

        fragmentTransaction.commit();

        mFragmentManager.addOnBackStackChangedListener(
                new android.support.v4.app.FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        Configuration config = getResources().getConfiguration();

                        if(config.toString().contains("port")){
                            portLayout();
                        }else{
                            landLayout();
                        }
                    }
                });
    }

    private int portLayout(){
        if (!webView_fragment.isAdded()) {
            restaurants.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));

            restWebLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
            return 0;
        } else {
            restaurants.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 0f));

            restWebLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 1f));
            return 1;
        }
    }

    private int landLayout() {
        if (!webView_fragment.isAdded()) {
            restaurants.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));

            restWebLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
            return 0;
        } else {
            restaurants.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 1f));

            restWebLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 2f));
            return 1;
        }
    }

    @Override
    public void onListSelection(int index) {
        if (!webView_fragment.isAdded()) {
            android.support.v4.app.FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction();

            fragmentTransaction.add(R.id.webView, webView_fragment);

            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.commit();

            mFragmentManager.executePendingTransactions();
        }

        if (webView_fragment.getShownIndex() != index) {
            webView_fragment.showQuoteAtIndex(index);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration config){
        super.onConfigurationChanged(config);
        if(config.toString().contains("port")){
            Log.i("selected", "port");
            portLayout();
        }else{
            Log.i("selected", "land");
            landLayout();
        }
    }
}
