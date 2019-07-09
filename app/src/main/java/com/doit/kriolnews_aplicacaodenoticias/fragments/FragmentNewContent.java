package com.doit.kriolnews_aplicacaodenoticias.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.doit.kriolnews_aplicacaodenoticias.R;
import com.doit.kriolnews_aplicacaodenoticias.model.NewsItem;
import com.doit.kriolnews_aplicacaodenoticias.model.ReadRss;
import com.google.firebase.auth.FirebaseAuth;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FragmentNewContent extends Fragment {

    RecyclerView recyclerView;

    private ListView listView;
    private FirebaseAuth mAuth;

    private ArrayList<String> titles;
    private ArrayList<String> links;
    private List<NewsItem> newsItemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_news_content,container,false);

        titles = new ArrayList<>();
        links = new ArrayList<>();
        newsItemList = new ArrayList<>();

        listView = view.findViewById(R.id.lv_rss);
        mAuth = FirebaseAuth.getInstance();

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Uri uri = Uri.parse(links.get(i));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        */

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        //Call Read rss asyntask to fetch rss
        ReadRss readRss = new ReadRss(getActivity(), recyclerView);
        readRss.execute();

        return view;
    }
}
