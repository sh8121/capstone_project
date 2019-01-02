package kr.sboo.android.itnewsportal.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NewsDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "news.db";
    private static final int DB_VERSION = 1;
    private static final String SQL_CREATE = "CREATE TABLE " + NewsContract.NewsEntry.TABLE_NAME +
            "(" + NewsContract.NewsEntry._ID +  " INTEGER PRIMARY KEY AUTOINCREMENT," +
            NewsContract.NewsEntry.COLUMN_NEWS_ID + " INTEGER, " +
            NewsContract.NewsEntry.COLUMN_TITLE +  " TEXT, " +
            NewsContract.NewsEntry.COLUMN_SUB_INFO + " TEXT, " +
            NewsContract.NewsEntry.COLUMN_URI + " TEXT)";

    public NewsDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
