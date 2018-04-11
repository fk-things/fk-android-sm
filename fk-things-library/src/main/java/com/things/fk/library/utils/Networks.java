package com.things.fk.library.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.common.base.Preconditions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;


/**
 * 网络工具
 * 1. 网络连接状态
 * 2. Mac地址
 *
 * @author tic
 *         created on 18/4/1.
 */

public class Networks {

    private static final String MARSHMALLOW_MAC_ADDRESS = "02:00:00:00:00:00";
    private static final String FILE_ADDRESS_MAC = "/sys/class/net/wlan0/address";

    public static boolean ok(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Preconditions.checkNotNull(connectivityManager);
        if (Utilities.ATLEAST_LOLLIPOP) {
            return okLollipop(connectivityManager);
        } else {
            return ok(connectivityManager);
        }
    }

    public static String macAddress(Context context) {
        Context application = context.getApplicationContext();
        WifiManager wifiMan = (WifiManager) application.getSystemService(Context.WIFI_SERVICE);
        Preconditions.checkNotNull(wifiMan);
        WifiInfo wifiInf = wifiMan.getConnectionInfo();

        if (Utilities.ATLEAST_LOLLIPOP_MR1) {
            String result = null;
            try {
                result = getAdressMacByInterface();
                if (result != null) {
                    return result;
                } else {
                    result = getAddressMacByFile(wifiMan);
                    return result;
                }
            } catch (IOException e) {
                Log.e("MobileAccess", "Erreur lecture propriete Adresse MAC");
            } catch (Exception e) {
                Log.e("MobileAcces", "Erreur lecture propriete Adresse MAC ");
            }
        } else {
            if (Utilities.isNotNull(wifiInf) && Utilities.isNotNull(wifiInf.getMacAddress())) {
                return wifiInf.getMacAddress();
            } else {
                return "";
            }
        }
        return MARSHMALLOW_MAC_ADDRESS;
    }

    private static String getAdressMacByInterface() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (nif.getName().equalsIgnoreCase("wlan0")) {
                    byte[] macBytes = nif.getHardwareAddress();
                    if (macBytes == null) {
                        return "";
                    }

                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes) {
                        res1.append(String.format("%02X:", b));
                    }

                    if (res1.length() > 0) {
                        res1.deleteCharAt(res1.length() - 1);
                    }
                    return res1.toString();
                }
            }

        } catch (Exception e) {
            Log.e("MobileAcces", "Erreur lecture propriete Adresse MAC ");
        }
        return null;
    }

    /**
     * get mac address by file
     *
     * @param wifiMan
     * @return
     * @throws Exception
     */
    private static String getAddressMacByFile(WifiManager wifiMan) throws Exception {
        String ret;
        int wifiState = wifiMan.getWifiState();

        wifiMan.setWifiEnabled(true);
        File fl = new File(FILE_ADDRESS_MAC);
        FileInputStream fin = new FileInputStream(fl);
        ret = crunchifyGetStringFromStream(fin);
        fin.close();

        boolean enabled = WifiManager.WIFI_STATE_ENABLED == wifiState;
        wifiMan.setWifiEnabled(enabled);
        return ret;
    }

    /**
     * @param crunchifyStream
     * @return
     * @throws IOException
     */
    private static String crunchifyGetStringFromStream(InputStream crunchifyStream) throws IOException {
        if (crunchifyStream != null) {
            Writer crunchifyWriter = new StringWriter();

            char[] crunchifyBuffer = new char[2048];
            try {
                Reader crunchifyReader = new BufferedReader(new InputStreamReader(crunchifyStream, "UTF-8"));
                int counter;
                while ((counter = crunchifyReader.read(crunchifyBuffer)) != -1) {
                    crunchifyWriter.write(crunchifyBuffer, 0, counter);
                }
            } finally {
                crunchifyStream.close();
            }
            return crunchifyWriter.toString();
        } else {
            return "No Contents";
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
