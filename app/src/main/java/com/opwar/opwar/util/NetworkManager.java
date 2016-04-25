package com.opwar.opwar.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

/**
 * Created by URZU on 25/04/2016.
 */
public class NetworkManager {
    /**
     * Comprueba que existe conexión a internet
     *
     * @throws IOException
     */
    public static void checkNetworkConnection(Context context) throws IOException {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null | (networkInfo != null && !networkInfo.isConnected())) {
            throw new IOException();
        }
    }
}
