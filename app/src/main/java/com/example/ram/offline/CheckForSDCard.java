package com.example.ram.offline;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;

import java.util.Objects;

public class CheckForSDCard {
    private Context context;

CheckForSDCard(){}
public CheckForSDCard(Context context){this.context=context;}
    boolean isSDCardPresent() {
        return Environment.getExternalStorageState().equals(

                Environment.MEDIA_MOUNTED);
    }
    public boolean isConnectingToInternet() {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm!=null)
        {
            if(Build.VERSION.SDK_INT<23)
            {
                NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                if (networkInfo != null) {
                    return (networkInfo.isConnected() && (networkInfo.getType()==ConnectivityManager.TYPE_WIFI|| networkInfo.getType()==ConnectivityManager.TYPE_MOBILE));
                }
            }
            else

            {
                final Network network=cm.getActiveNetwork();
                if(network!=null)
                {
                    final NetworkCapabilities nc=cm.getNetworkCapabilities(network);
                    return (Objects.requireNonNull(nc).hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                }
            }
        }
        return false;
    }

}
