package kr.sboo.android.itnewsportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * Created by lgpc on 2018-12-23.
 */

public class NewsAdapter extends RecyclerView.Adapter {
    private Context mContext;

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

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class NewsHolder extends RecyclerView.ViewHolder{
        private TextView mTitle;
        private TextView mWriter;
        private TextView mDate;

        public NewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
