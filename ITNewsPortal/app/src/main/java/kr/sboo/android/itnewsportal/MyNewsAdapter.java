package kr.sboo.android.itnewsportal;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import kr.sboo.android.itnewsportal.data.NewsContract;

/**
 * Created by lgpc on 2018-12-23.
 */

public class MyNewsAdapter extends NewsAdapterBase{
    private Cursor mCursor;

    public MyNewsAdapter(Context context, OnNewsClickListener listener) {
        super(context, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        mCursor.moveToPosition(position);
        final int titleIndex = mCursor.getColumnIndex(NewsContract.NewsEntry.COLUMN_TITLE);
        final int subInfoIndex = mCursor.getColumnIndex(NewsContract.NewsEntry.COLUMN_SUB_INFO);
        final String title = mCursor.getString(titleIndex);
        final String subInfo = mCursor.getString(subInfoIndex);
        holder.bind(title, subInfo);
    }

    @Override
    public int getItemCount() {
        return mCursor != null ? mCursor.getCount() : 0;
    }

    public String getNewsUri(int position){
        mCursor.moveToPosition(position);
        final int uriIndex = mCursor.getColumnIndex(NewsContract.NewsEntry.COLUMN_URI);
        final String uri = mCursor.getString(uriIndex);
        return uri;
    }

    public void setCursor(Cursor cursor){
        if(mCursor != null && mCursor != cursor){
            mCursor.close();
        }
        mCursor = cursor;
        notifyDataSetChanged();
    }
}

