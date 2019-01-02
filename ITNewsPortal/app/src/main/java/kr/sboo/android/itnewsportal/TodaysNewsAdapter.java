package kr.sboo.android.itnewsportal;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import kr.sboo.android.itnewsportal.model.News;

public class TodaysNewsAdapter extends NewsAdapterBase {

    private List<News> mNewsList;

    public TodaysNewsAdapter(Context context, OnNewsClickListener listener) {
        super(context, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        News news = mNewsList.get(position);
        holder.bind(news.getTitle(), news.getSubInfo());
    }

    @Override
    public int getItemCount() {
        return mNewsList != null ? mNewsList.size() : 0;
    }

    public News getNews(int position){
        return mNewsList.get(position);
    }

    public void setNewsList(List<News> newsList){
        mNewsList = newsList;
        notifyDataSetChanged();
    }
}
