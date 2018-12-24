package kr.sboo.android.itnewsportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.sboo.android.itnewsportal.model.News;

/**
 * Created by lgpc on 2018-12-23.
 */

public class NewsAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<News> mNewsList;

    public NewsAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.news_cell, parent, false);
        return new NewsHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((NewsHolder)holder).bind(mNewsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewsList != null ? mNewsList.size() : 0;
    }

    public void setNewsList(List<News> newsList){
        mNewsList = newsList;
    }

    class NewsHolder extends RecyclerView.ViewHolder{
         @BindView(R.id.title) TextView mTitle;
         @BindView(R.id.writer) TextView mWriter;
         @BindView(R.id.date) TextView mDate;

        public NewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);        }

        public void bind(News news){
            mTitle.setText(news.getTitle());
            mWriter.setText(news.getAuthor());
            mDate.setText(news.getPublishedDate());
        }
    }
}
