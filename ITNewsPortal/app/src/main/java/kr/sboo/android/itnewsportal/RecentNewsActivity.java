package kr.sboo.android.itnewsportal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecentNewsActivity extends AppCompatActivity {

    @BindView(R.id.recent_news_container) private RecyclerView mRecentNewsContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_news);
        ButterKnife.bind(this);
    }
}
