package edu.uic.cs478.a2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


public class ShowAttractions extends MainActivity implements Attractions_Fragment.ListSelectionListener {
    public static String[] attNameArray;
    public static String[] attUrlArray;

    private android.support.v4.app.FragmentManager mFragmentManager;
    private Attractions_Fragment attr_Fragment = null;
    private final Attractions_WebView_Fragment webView_fragment = new Attractions_WebView_Fragment();
    private FrameLayout mTitleFrameLayout, mQuotesFrameLayout;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "ShowAttractions";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.framelayout);
        Log.i("Attractions", " Clicked Attractions");

        attNameArray = getResources().getStringArray(R.array.listOfAttractions);
        attUrlArray = getResources().getStringArray(R.array.attUrls);

        mTitleFrameLayout = (FrameLayout) findViewById(R.id.names);
        mQuotesFrameLayout = (FrameLayout) findViewById(R.id.webView);

        mFragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = mFragmentManager
                                                    .beginTransaction();

        attr_Fragment = new Attractions_Fragment();

        fragmentTransaction.replace(R.id.names, attr_Fragment);

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
            mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));
            mQuotesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
            return 0;
        } else {
            mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 0f));

            mQuotesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 1f));
            return 1;
        }
    }

    private int landLayout() {
        if (!webView_fragment.isAdded()) {
            mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));

            mQuotesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
            return 0;
        } else {
            mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 1f));

            mQuotesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 2f));
            return 1;
        }
    }

    @Override
    public void onListSelection(int index) {
        Log.i("selected", index + "");
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
        super.onResume();
        //Configuration config = getResources().getConfiguration();

        if(config.toString().contains("port")){
            Log.i("selected", "port");
            portLayout();
        }else{
            Log.i("selected", "land");
            landLayout();
        }
    }
}
