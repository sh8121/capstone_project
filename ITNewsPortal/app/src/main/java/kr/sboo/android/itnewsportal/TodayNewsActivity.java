package kr.sboo.android.itnewsportal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.sboo.android.itnewsportal.model.News;

public class TodayNewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>, NewsAdapter.OnNewsClickListener {

    private static final int NEWS_LOADER_ID = 1000;
    @BindView(R.id.today_news_container) RecyclerView mTodayNewsContainer;
    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_news);
        ButterKnife.bind(this);
        mAdapter = new NewsAdapter(this, this);
        mTodayNewsContainer.setAdapter(mAdapter);
        mTodayNewsContainer.setLayoutManager(new LinearLayoutManager(this));
        mTodayNewsContainer.setItemAnimator(new DefaultItemAnimator());
        mTodayNewsContainer.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        getSupportLoaderManager().initLoader(NEWS_LOADER_ID, null, this);
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {
        return new NewsLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> data) {
        mAdapter.setNewsList(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        mAdapter.setNewsList(null);
    }

    @Override
    public void onClick(News news) {
        Gson gson = new Gson();
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra(NewsDetailActivity.NEWS_KEY, gson.toJson(news));
        startActivity(intent);
    }
}
