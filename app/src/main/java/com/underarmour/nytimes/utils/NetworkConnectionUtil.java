package com.underarmour.nytimes.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.underarmour.nytimes.NYTimesApp;

public class NetworkConnectionUtil {

    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) NYTimesApp.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
