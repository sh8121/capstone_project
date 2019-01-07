package kr.sboo.android.itnewsportal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import kr.sboo.android.itnewsportal.api.ApiHelper;
import kr.sboo.android.itnewsportal.model.News;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by sboo on 2018-12-24.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private List<News> mNewsList;

    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if(mNewsList != null)
            deliverResult(mNewsList);
        else
            forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        return ApiHelper.getNewsListFromApi();
    }

    @Override
    public void deliverResult(@Nullable List<News> newsList) {
        mNewsList = newsList;
        super.deliverResult(newsList);
    }
}
