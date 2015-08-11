package com.shikha.rssfeeds.framework;

import com.shikha.rssfeeds.framework.object.RssFeed;
import com.shikha.rssfeeds.framework.object.RssItem;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by shikha on 9/8/15.
 */
public class RSSHandler extends DefaultHandler
{

    RssFeed mRssFeed;
    RssItem mRssItem;
    final int RSS_TITLE = 1;
    final int RSS_LINK = 2;
    final int RSS_PUBDATE = 3;

    int depth = 0;
    int currentstate = 0;

    /*
     * this function returns rssfeed object when all of the parsing is complete
     */
   public RssFeed getFeed()
    {
        return mRssFeed;
    }


    public void startDocument() throws SAXException
    {
        mRssFeed = new RssFeed();
        mRssItem = new RssItem();

    }
    public void endDocument() throws SAXException
    {
    }
    public void startElement(String namespaceURI, String localName,String qName,
                             Attributes atts) throws SAXException
    {
        depth++;
        if (localName.equals("channel"))
        {
            currentstate = 0;
            return;
        }
        if (localName.equals("image"))
        {
            mRssFeed.setTitle(mRssItem.getTitle());
            mRssFeed.setPubdate(mRssItem.getPubDate());
        }
        if (localName.equals("item"))
        {

            mRssItem = new RssItem();
            return;
        }
        if (localName.equals("title"))
        {
            currentstate = RSS_TITLE;
            return;
        }
        if (localName.equals("link"))
        {
            currentstate = RSS_LINK;
            return;
        }
        if (localName.equals("pubDate"))
        {
            currentstate = RSS_PUBDATE;
            return;
        }

        currentstate = 0;
    }

    public void endElement(String namespaceURI, String localName, String qName)
            throws SAXException
    {
        depth--;
        if (localName.equals("item"))
        {
            mRssFeed.addItem(mRssItem);
            return;
        }
    }

    public void characters(char ch[], int start, int length)
    {
        String theString = new String(ch,start,length);
        switch (currentstate)
        {
            case RSS_TITLE:
                mRssItem.setTitle(theString);
                currentstate = 0;
                break;
            case RSS_LINK:
                mRssItem.setLink(theString);
                currentstate = 0;
                break;
            case RSS_PUBDATE:
                mRssItem.setPubDate(theString);
                currentstate = 0;
                break;
            default:
                return;
        }

    }
}
