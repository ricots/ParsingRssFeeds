package com.shikha.rssfeeds.ui.adapter;

import android.content.Context;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shikha.rssfeeds.R;
import com.shikha.rssfeeds.framework.object.RssItem;
import com.shikha.rssfeeds.ui.object.NewsViewHolder;

import java.util.ArrayList;

/**
 * Created by shikha on 9/8/15.
 */
public class NewsAdapter extends BaseAdapter {
    private Context context;
    ArrayList<RssItem> objects;
    private LayoutInflater inflater;

    public NewsAdapter(Context context,
                       ArrayList<RssItem> objects) {
        this.context = context;
        this.objects = objects;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public RssItem getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    NewsViewHolder viewHolder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder = new NewsViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(
                    R.layout.add_on_news_row, null);
        }
        viewHolder.setLink((TextView) convertView.findViewById(R.id.txtLink));
        viewHolder.setTime((TextView) convertView.findViewById(R.id.txtDate));
        viewHolder.setTitle((TextView) convertView.findViewById(R.id.txtTitle));


        RssItem item = getItem(position);

        viewHolder.getTitle().setText(item.getTitle());
        viewHolder.getTime().setText(item.getPubDate());
        viewHolder.getLink().setText(item.getLink());
        Linkify.addLinks(viewHolder.getLink(), Linkify.WEB_URLS);

        return convertView;
    }
}
