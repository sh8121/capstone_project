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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.sboo.android.itnewsportal.data.NewsContract;
import kr.sboo.android.itnewsportal.model.News;

public class MyNewsActivity extends AppCompatActivity implements MyNewsAdapter.OnNewsClickListener, LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemSelectedListener {

    private static final int MY_NEWS_LOADER_ID = 1001;

    @BindView(R.id.my_news_order_selector) Spinner mMyNewsOrderSelector;
    @BindView(R.id.my_news_container) RecyclerView mMyNewsContainer;
    private MyNewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_news);
        ButterKnife.bind(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.news_order_std_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mMyNewsOrderSelector.setAdapter(adapter);
        mMyNewsOrderSelector.setOnItemSelectedListener(this);
        mAdapter = new MyNewsAdapter(this, this);
        mMyNewsContainer.setLayoutManager(new LinearLayoutManager(this));
        mMyNewsContainer.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mMyNewsContainer.setAdapter(mAdapter);
        getSupportLoaderManager().initLoader(MY_NEWS_LOADER_ID, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
         int position = mMyNewsOrderSelector.getSelectedItemPosition();
         String sortOrder = null;
         switch (position){
             case 0:
                 sortOrder = "_id DESC";
                 break;
             case 1:
                 sortOrder = "_id ASC";
                 break;
             case 2:
                 sortOrder = "title DESC";
                 break;
             case 3:
                 sortOrder = "title ASC";
                 break;
             default:
                 throw new UnsupportedOperationException("UnKnown Sort Option");
         }
        return new CursorLoader(this, NewsContract.NewsEntry.CONTENT_URI, null, null, null, sortOrder);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        getSupportLoaderManager().restartLoader(MY_NEWS_LOADER_ID, null, this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
