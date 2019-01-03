package kr.sboo.android.itnewsportal;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.sboo.android.itnewsportal.data.NewsContract;
import kr.sboo.android.itnewsportal.model.News;

public class MyNewsActivity extends AppCompatActivity implements MyNewsAdapter.OnNewsClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int MY_NEWS_LOADER_ID = 1001;

    @BindView(R.id.my_news_container) RecyclerView mMyNewsContainer;
    private MyNewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_news);
        ButterKnife.bind(this);
        mAdapter = new MyNewsAdapter(this, this);
        mMyNewsContainer.setLayoutManager(new LinearLayoutManager(this));
        mMyNewsContainer.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mMyNewsContainer.setAdapter(mAdapter);
        getSupportLoaderManager().initLoader(MY_NEWS_LOADER_ID, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this, NewsContract.NewsEntry.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        mAdapter.setCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mAdapter.setCursor(null);
    }

    @Override
    public void onClick(int position) {
        String newsUri = mAdapter.getNewsUri(position);
        long id = mAdapter.getId(position);
        News news = new News();
        news.setId(id);
        news.setUri(newsUri);
        Gson gson = new Gson();
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra(NewsDetailActivity.NEWS_KEY, gson.toJson(news));
        startActivity(intent);
    }
}
