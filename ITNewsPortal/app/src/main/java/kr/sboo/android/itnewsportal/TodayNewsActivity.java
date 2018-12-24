package kr.sboo.android.itnewsportal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.sboo.android.itnewsportal.model.News;

public class TodayNewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    @BindView(R.id.today_news_container) RecyclerView mTodayNewsContainer;
    NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_news);
        ButterKnife.bind(this);
        mAdapter = new NewsAdapter(this);
        mTodayNewsContainer.setAdapter(mAdapter);
        mTodayNewsContainer.setLayoutManager(new LinearLayoutManager(this));
        getSupportLoaderManager().initLoader(0, null, this);
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {
        return new NewsLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> data) {
        mAdapter.setNewsList(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        mAdapter.setNewsList(null);
        mAdapter.notifyDataSetChanged();
    }
}
