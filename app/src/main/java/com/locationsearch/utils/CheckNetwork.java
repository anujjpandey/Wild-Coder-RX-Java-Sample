package com.locationsearch.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Wild Coder on 12-10-2013.
 */
public class CheckNetwork {

    /**
     * @param c
     * @return
     */
    public boolean checkInternetConnection(Context c) {
        if (c == null)
            return false;
        ConnectivityManager cm = (ConnectivityManager) c
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        return cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected();
    }
}
