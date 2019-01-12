package kr.sboo.android.itnewsportal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends TransitionActivityBase {
    private FirebaseAnalytics mFirebaseAnalytics;
    @BindView(R.id.go_to_today_news_button) Button mGoToTodayNewsButton;
    @BindView(R.id.go_to_my_news_button) Button mGoToMyNewsButton;
    @BindView(R.id.adView) AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(this, "ca-app-pub-1831729606651519~5963495121");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mGoToTodayNewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "go_to_today_news_button");
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "go_to_today_news_button");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "button");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                Intent intent = new Intent(MainActivity.this, TodayNewsActivity.class);
                startActivity(intent);
            }
        });

        mGoToMyNewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "go_to_my_news_button");
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "go_to_my_news_button");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "button");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                Intent intent = new Intent(MainActivity.this, MyNewsActivity.class);
                startActivity(intent);
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}
