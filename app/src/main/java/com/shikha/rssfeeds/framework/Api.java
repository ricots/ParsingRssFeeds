package com.shikha.rssfeeds.framework;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import com.shikha.rssfeeds.framework.object.RssFeed;
import com.shikha.rssfeeds.utils.AppUtils;
import com.shikha.rssfeeds.utils.ConnectionDetector;

/**
 * Created by shikha on 9/8/15.
 */
public class Api {
    private Context mContext;
    public boolean progressOn = true;
    public String progressMessage = "Please wait...\n While news is loading.";

    public Api(Context mContext) {
        this.mContext = mContext;
    }


    public interface FetchListener {
        public void onFetched(RssFeed result);
    }

    public void getNews(int position, FetchListener listener) {
        String url = null;
        if (position == 0) {
            url = AppUtils.RSS_CNN_NEWS;
        } else {
            url = AppUtils.RSS_GOOGLE_NEW;
        }
        new AsyncHTTPPost().execute(url, listener);
    }

    class AsyncHTTPPost extends AsyncTask {

        private ProgressDialog pg;
        private String url;
        private FetchListener listener;

        @Override
        protected void onPreExecute() {

            if (new ConnectionDetector(mContext).isConnectedToInternet()) {
                if (progressOn) {
                    pg = ProgressDialog.show(mContext, "", progressMessage,
                            true, true);
                    pg.setCanceledOnTouchOutside(false);
                    pg.setOnDismissListener(new DialogInterface.OnDismissListener() {

                        @Override
                        public void onDismiss(DialogInterface arg0) {
                            AsyncHTTPPost.this.cancel(!isCancelled());
                        }
                    });
                }
            } else {
                AppUtils.displayToast(mContext, "Sorry, No network connectivity");
                AsyncHTTPPost.this.cancel(!isCancelled());

            }
        }

        @Override
        protected Object doInBackground(Object... arg0) {
            url = arg0[0].toString();
            listener = (FetchListener) arg0[1];
            if (url != null)
                return AppUtils.getFeed(url);
            else
                return null;
        }

        @Override
        protected void onPostExecute(Object args) {
            RssFeed feedResult=null;
            if (pg != null && pg.isShowing())
                pg.cancel();
            if (args != null) {
                try {
                  feedResult= (RssFeed) args;
                } catch (Exception e) {
                    AppUtils.displayLog("Exception Occured:" + e.getMessage());
                }
            } else {
               // AppUtils.displayToast(mContext, "Sorry something went wrong");
            }
            if (listener != null) {
                if (feedResult != null)
                    listener.onFetched(feedResult);
                else
                    listener.onFetched(null);
            } else {

                AppUtils.displayLog("Listener = null");
            }


        }

    }


}
