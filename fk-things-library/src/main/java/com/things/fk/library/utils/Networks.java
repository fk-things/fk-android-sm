package com.things.fk.library.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.common.base.Preconditions;


/**
 * @author ${tic}
 *         created on 18/4/1.
 */

public class Networks {

    public static boolean ok(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Preconditions.checkNotNull(connectivityManager);
        if (Utilities.ATLEAST_LOLLIPOP) {
            return okLollipop(connectivityManager);
        } else {
            return ok(connectivityManager);
        }
    }

    private static boolean ok(ConnectivityManager connectivityManager) {
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        Boolean isWifiConn = networkInfo.isConnected();
        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        Boolean isMobileConn = networkInfo.isConnected();
        return isWifiConn || isMobileConn;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static boolean okLollipop(ConnectivityManager connectivityManager) {
        Network[] networks = connectivityManager.getAllNetworks();
        StringBuilder builder = new StringBuilder();
        boolean connected = false;
        for (Network network : networks) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
            builder.append(networkInfo.getTypeName()).append(" connect is ").append(networkInfo.isConnected());
            if (networkInfo.isConnected()) {
                connected = true;
            }
        }
        Log.d(Networks.class.getSimpleName(), builder.toString());
        return connected;
    }
}
