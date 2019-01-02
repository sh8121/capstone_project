package kr.sboo.android.itnewsportal.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class NewsContract {
    public static final String AUTHORITY = "kr.sboo.android.itnewsportal.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_NEWS = "news";
    public static final long INVALID_NEWS_ID = -1;
    public static final class NewsEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_NEWS).build();
        public static final String TABLE_NAME = "news";
        public static final String COLUMN_NEWS_ID = "news_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_SUB_INFO = "sub_info";
        public static final String COLUMN_URI = "uri";
    }
}
