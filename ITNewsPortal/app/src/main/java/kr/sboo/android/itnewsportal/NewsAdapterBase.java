package kr.sboo.android.itnewsportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lgpc on 2018-12-23.
 */

public abstract class NewsAdapterBase extends RecyclerView.Adapter<NewsAdapterBase.NewsHolder> {
    private Context mContext;
    private OnNewsClickListener mListener;

    public NewsAdapterBase(Context context, OnNewsClickListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newsView = LayoutInflater.from(mContext).inflate(R.layout.news_cell, parent, false);
        return new NewsHolder(newsView);
    }

    interface OnNewsClickListener{
        void onClick(int position);
    }

    class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
         @BindView(R.id.title) TextView mTitle;
         @BindView(R.id.sub_info) TextView mSubInfo;

        public NewsHolder(View newsView) {
            super(newsView);
            ButterKnife.bind(this, newsView);
            newsView.setOnClickListener(this);
        }

        public void bind(String title, String subInfo){
            mTitle.setText(title);
            mSubInfo.setText(subInfo);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(getAdapterPosition());
        }
    }
}
