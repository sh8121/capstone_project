package kr.sboo.android.itnewsportal;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import kr.sboo.android.itnewsportal.model.News;

/**
 * Implementation of App Widget functionality.
 */
public class RecentlyNewsWidget extends AppWidgetProvider {
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int[] appWidgetIds, News news) {

        for(int appWidgetId : appWidgetIds){
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recently_news_widget);
            views.setTextViewText(R.id.app_widget_title, news.getTitle());
            views.setTextViewText(R.id.app_widget_sub_info, news.getSubInfo());
            Intent refreshIntent = new Intent(context, RecentlyNewsService.class);
            PendingIntent refreshPendingIntent = PendingIntent.getService(context, 0, refreshIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.app_widget_refresh, refreshPendingIntent);
            Intent mainActivityIntent = new Intent(context, MainActivity.class);
            mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Intent todayNewsActivityIntent = new Intent(context, TodayNewsActivity.class);
            PendingIntent openAppPendingIntent = PendingIntent.getActivities(context, 0, new Intent[]{mainActivityIntent, todayNewsActivityIntent}, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.app_widget_info_container, openAppPendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        Intent intent = new Intent(context, RecentlyNewsService.class);
        context.startService(intent);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

