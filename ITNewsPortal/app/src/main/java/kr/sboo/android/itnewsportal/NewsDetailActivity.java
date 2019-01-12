package kr.sboo.android.itnewsportal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.sboo.android.itnewsportal.data.NewsContract;
import kr.sboo.android.itnewsportal.model.News;

public class NewsDetailActivity extends TransitionActivityBase {
    public static final String NEWS_KEY = "NEWS";
    @BindView(R.id.webview) WebView mWebView;
    @BindView(R.id.go_back_button) FloatingActionButton mGoBackButton;
    @BindView(R.id.save_button) FloatingActionButton mSaveButton;
    @BindView(R.id.delete_button) FloatingActionButton mDeleteButton;
    @BindView(R.id.edit_button) FloatingActionButton mEditButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String newsJson = intent != null ? intent.getStringExtra(NEWS_KEY) : "";
        if(!TextUtils.isEmpty(newsJson)){
            Gson gson = new Gson();
            final News news = gson.fromJson(newsJson, News.class);
            mWebView.setWebViewClient(new WebViewClient());
            mWebView.loadUrl(news.getUri());
            mGoBackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            if(!TextUtils.isEmpty(news.getTitle()) && !TextUtils.isEmpty(news.getSubInfo()) && news.getId() > 0){
                mSaveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor cursor = getContentResolver().query(
                                NewsContract.NewsEntry.CONTENT_URI,
                                null,
                                "news_id=?",
                                new String[]{String.valueOf(news.getId())},
                                null);
                        if(cursor != null && cursor.getCount() > 0){
                            Toast.makeText(NewsDetailActivity.this, getResources().getString(R.string.news_url_already_exist_alert_text), Toast.LENGTH_LONG).show();
                            return;
                        }
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(NewsContract.NewsEntry.COLUMN_NEWS_ID, news.getId());
                        contentValues.put(NewsContract.NewsEntry.COLUMN_TITLE, news.getTitle());
                        contentValues.put(NewsContract.NewsEntry.COLUMN_SUB_INFO, news.getSubInfo());
                        contentValues.put(NewsContract.NewsEntry.COLUMN_URI, news.getUri());
                        Uri uri = getContentResolver().insert(NewsContract.NewsEntry.CONTENT_URI, contentValues);
                        if(uri != null){
                            Toast.makeText(NewsDetailActivity.this, getResources().getString(R.string.news_url_save_alert_text), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                mSaveButton.setVisibility(View.VISIBLE);
            }
            else{
                mDeleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = NewsContract.NewsEntry.CONTENT_URI.buildUpon().appendPath(String.valueOf(news.getId())).build();
                        int rowCnt = getContentResolver().delete(uri, null, null);
                        if(rowCnt > 0){
                            Toast.makeText(NewsDetailActivity.this, getResources().getString(R.string.news_delete_alert_text), Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                });
                mDeleteButton.setVisibility(View.VISIBLE);
            }
        }
        else{
            Toast.makeText(this, getResources().getText(R.string.web_view_url_empty_alert_text), Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
