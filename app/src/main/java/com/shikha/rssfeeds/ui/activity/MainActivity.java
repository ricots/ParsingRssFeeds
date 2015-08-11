package com.shikha.rssfeeds.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.shikha.rssfeeds.R;
import com.shikha.rssfeeds.framework.Api;
import com.shikha.rssfeeds.framework.object.RssFeed;
import com.shikha.rssfeeds.framework.object.RssItem;
import com.shikha.rssfeeds.ui.adapter.NewsAdapter;
import com.shikha.rssfeeds.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Context mContext;
    private Api mApi;
    private Spinner mNewSpinner;
    private Toolbar mToolbar;
    private ListView mList;
    private NewsAdapter mAdapter;
    private ArrayList<RssItem> rssItemList;
    private int listCount=0;
    private ArrayList<RssItem> rssFeedResult;
    private Button mLoad;
    private LinearLayout mLoadContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponent();
    }

    private void initializeComponent() {
        setupToolbar();
        mContext = this;
        mApi = new Api(mContext);
        mLoadContainer= (LinearLayout) findViewById(R.id.conatinerLoad);
        mLoad= (Button) findViewById(R.id.buttonLoad);
        mLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLoad();
            }
        });
        mNewSpinner = (Spinner) findViewById(R.id.newsfilter);
        mNewSpinner.setOnItemSelectedListener(this);
        rssItemList = new ArrayList<RssItem>();
        mList = (ListView) findViewById(R.id.newsList);
        mAdapter = new NewsAdapter(mContext, rssItemList);
        mList.setAdapter(mAdapter);



    }

    private void performLoad() {
        if(rssFeedResult!=null && rssFeedResult.size()>0) {
            int size = rssFeedResult.size();

            if (size > listCount) {
                ArrayList<RssItem> tempList = displayResultSet();
                notifyAdapter(tempList);
            } else {
                mLoadContainer.setVisibility(View.GONE);
                AppUtils.displayToast(mContext, "No data to load!!!");
            }
        }else
        {
            mLoadContainer.setVisibility(View.GONE);
            AppUtils.displayToast(mContext, "No data to load!!!");
        }
    }


    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        performNewsUpload(position);

    }

    private void performNewsUpload(int position) {
        listCount=0;
        mApi.getNews(position, new Api.FetchListener() {
            @Override
            public void onFetched(RssFeed resultFeed) {
                if (resultFeed != null) {
                    mLoadContainer.setVisibility(View.VISIBLE);
                    Gson gson = new Gson();
                    rssFeedResult = (ArrayList<RssItem>) resultFeed.getItemlist();
                    ArrayList<RssItem>tempList= displayResultSet();
                    notifyAdapter(tempList);


                }
            }
        });
    }

    private ArrayList<RssItem> displayResultSet() {
        listCount=listCount+5;
        List<RssItem> tempList=new ArrayList<>();
        if(rssFeedResult.size()>listCount) {
            for(int i=0;i<listCount;i++)
            {
                RssItem item=rssFeedResult.get(i);
                tempList.add(item);
            }

            return (ArrayList<RssItem>) tempList;
        }
        else
        {
            for(int i=0;i<rssFeedResult.size();i++)
            {
                RssItem item=rssFeedResult.get(i);
                tempList.add(item);
            }
            return (ArrayList<RssItem>) tempList;
        }
    }

    private void notifyAdapter(ArrayList<RssItem> tempList) {
        rssItemList.clear();
        rssItemList.addAll(tempList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
