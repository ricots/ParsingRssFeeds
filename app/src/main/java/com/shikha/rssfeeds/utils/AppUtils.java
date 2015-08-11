package com.shikha.rssfeeds.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.shikha.rssfeeds.framework.RSSHandler;
import com.shikha.rssfeeds.framework.object.RssFeed;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by shikha on 9/8/15.
 */
public class AppUtils {

    public static final String RSS_CNN_NEWS = "http://rss.cnn.com/rss/cnn_latest.rss";
    public static final String RSS_GOOGLE_NEW = "https://news.google.com/?output=rss";
    public static final String TAG = "DIGIPLUSE";

    /**
     * Function to display toast message to user
     *
     * @param context:context of the activity
     * @param msg:message     to display to the user
     */
    public static void displayToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

    }

    /**
     * Function to display log message
     *
     * @param msg:message to print as log
     */
    public static void displayLog(String msg) {
        Log.e(TAG, msg);

    }

    /**
     * Function to get rssfeed object from provied url
     */
    public static RssFeed getFeed(String urlFeed) {
        try {

            URL url = new URL(urlFeed);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlreader = parser.getXMLReader();
            RSSHandler rssHandler = new RSSHandler();
            xmlreader.setContentHandler(rssHandler);
            InputSource is = new InputSource(url.openStream());
            xmlreader.parse(is);
            return rssHandler.getFeed();
        } catch (Exception ee) {
            return null;
        }
    }

}
