package kr.sboo.android.itnewsportal.model;

import android.text.TextUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sboo on 2018-12-24.
 */

@Getter
@Setter
public class News {
    private long id;
    private String title;
    private String author;
    private String publishedDate;
    private String previewContent;
    private String uri;
    private Provider provider;

    public String getSubInfo(){
        if(TextUtils.isEmpty(author) || TextUtils.isEmpty(publishedDate) || provider == null){
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(provider.getName());
        buffer.append(" | ");
        buffer.append(author);
        buffer.append("   ");
        buffer.append(publishedDate);
        return buffer.toString();
    }
}
