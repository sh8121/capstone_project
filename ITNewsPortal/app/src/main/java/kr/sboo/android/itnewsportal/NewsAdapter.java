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

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    private Context mContext;
    private List<News> mNewsList;

    public NewsAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newsView = LayoutInflater.from(mContext).inflate(R.layout.news_cell, parent, false);
        return new NewsHolder(newsView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        holder.bind(mNewsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewsList != null ? mNewsList.size() : 0;
    }

    public void setNewsList(List<News> newsList){
        mNewsList = newsList;
        notifyDataSetChanged();
    }

    class NewsHolder extends RecyclerView.ViewHolder{
         @BindView(R.id.title) TextView mTitle;
         @BindView(R.id.sub_info) TextView mSubInfo;

        public NewsHolder(View newsView) {
            super(newsView);
            ButterKnife.bind(this, newsView);        }

        public void bind(News news){
            mTitle.setText(news.getTitle());
            mSubInfo.setText(news.getSubInfo());
        }
    }
}
