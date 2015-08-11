package com.shikha.rssfeeds.ui.object;

import android.widget.TextView;

/**
 * Created by shikha on 9/8/15.
 */
public class NewsViewHolder {

    public TextView title;
    public TextView link;
    public TextView time;

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getLink() {
        return link;
    }

    public void setLink(TextView link) {
        this.link = link;
    }

    public TextView getTime() {
        return time;
    }

    public void setTime(TextView time) {
        this.time = time;
    }
}
