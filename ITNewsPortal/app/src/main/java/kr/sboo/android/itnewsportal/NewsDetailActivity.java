package kr.sboo.android.itnewsportal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.sboo.android.itnewsportal.model.News;

public class NewsDetailActivity extends AppCompatActivity {
    public static final String NEWS_KEY = "NEWS";
    @BindView(R.id.webview) WebView mWebView;
    @BindView(R.id.go_back_button) FloatingActionButton mGoBackButton;
    @BindView(R.id.save_button) FloatingActionButton mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String newsJson = intent != null ? intent.getStringExtra(NEWS_KEY) : "";
        if(!TextUtils.isEmpty(newsJson)){
            Gson gson = new Gson();
            News news = gson.fromJson(newsJson, News.class);
            mWebView.setWebViewClient(new WebViewClient());
            mWebView.loadUrl(news.getUri());
            mGoBackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            mSaveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        else{
            Toast.makeText(this, getResources().getText(R.string.web_view_url_empty_alert_text), Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
