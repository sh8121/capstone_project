package kr.sboo.android.itnewsportal;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.util.List;

import kr.sboo.android.itnewsportal.api.ApiHelper;
import kr.sboo.android.itnewsportal.model.News;

public class RecentlyNewsService extends IntentService{
    public RecentlyNewsService() {
        super("RecentlyNewsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        List<News> newsList = ApiHelper.getNewsListFromApi();
        News news = null;
        if(newsList != null && newsList.size() > 0){
            news = newsList.get(0);
        }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecentlyNewsWidget.class));
        RecentlyNewsWidget.updateAppWidget(this, appWidgetManager, appWidgetIds, news);
    }
}
