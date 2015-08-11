package com.shikha.rssfeeds.framework.object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shikha on 9/8/15.
 */
public class RssFeed {
    private String title;
    private String pubdate;
    private int itemcount;
    private List<RssItem> itemlist;

    public RssFeed()
    {

        itemlist=new ArrayList<RssItem>();
    }
    public int addItem(RssItem item)
    {
        itemlist.add(item);
        itemcount++;
        return itemcount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public int getItemcount() {
        return itemcount;
    }

    public void setItemcount(int itemcount) {
        this.itemcount = itemcount;
    }

    public List<RssItem> getItemlist() {
        return itemlist;
    }

    public void setItemlist(List<RssItem> itemlist) {
        this.itemlist = itemlist;
    }


}
