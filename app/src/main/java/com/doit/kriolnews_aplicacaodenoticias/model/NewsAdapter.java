package com.doit.kriolnews_aplicacaodenoticias.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.doit.kriolnews_aplicacaodenoticias.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    ArrayList<NewsItem> newsItems;
    RecyclerView recyclerView;
    Context context;
    public NewsAdapter(Context context, ArrayList<NewsItem> newsItems){
        this.newsItems=newsItems;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.news_item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        recyclerView= view.findViewById(R.id.recyclerview);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //YoYo.with(Techniques.FadeIn).playOn(holder.cardView);
        NewsItem current=newsItems.get(position);
        holder.Title.setText(current.getTitle());
        holder.Description.setText(current.getDescription());
        holder.Date.setText(current.getPubDate());
        holder.category.setText(current.getCategory());
        if(current.getThumbnailUrl()!=null) {
            String url = current.getThumbnailUrl().substring(7);
            Picasso.with(context).load("https://"+url).into(holder.Thumbnail);
        }else{
            String url = "https://sanitationsolutions.net/wp-content/uploads/2015/05/empty-image.png";
            Picasso.with(context).load(url).into(holder.Thumbnail);
        }

        final NewsItem news = newsItems.get(position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.getLink()));
                context.startActivity(intent);
            }
        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //Toast.makeText(context,"Work", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Title,Description,Date,category;
        ImageView Thumbnail;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            Title= (TextView) itemView.findViewById(R.id.title_text);
            Description= (TextView) itemView.findViewById(R.id.description_text);
            Date= (TextView) itemView.findViewById(R.id.date_text);
            Thumbnail= (ImageView) itemView.findViewById(R.id.thumb_img);
            cardView= (CardView) itemView.findViewById(R.id.cardview);
            category = (TextView) itemView.findViewById(R.id.category_news);
        }
    }
}
