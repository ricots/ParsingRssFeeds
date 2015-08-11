package com.shikha.rssfeeds.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by shikha on 9/8/15.
 */
public class ConnectionDetector {
 
    private Context _context;
 
    public ConnectionDetector(final Context context){
        this._context = context;
      
    }
 
    /**
     * Checking for all possible internet providers
     * **/
    public boolean isConnectedToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
          if (connectivity != null)
          {
              NetworkInfo[] info = connectivity.getAllNetworkInfo();
              if (info != null)
                  for (int i = 0; i < info.length; i++)
                      if (info[i].getState() == NetworkInfo.State.CONNECTED)
                      {
                          return true;
                      }
 
          }
          return false;
    }
}
