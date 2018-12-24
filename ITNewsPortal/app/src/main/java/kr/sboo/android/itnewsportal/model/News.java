package kr.sboo.android.itnewsportal.model;

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
}
