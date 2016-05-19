package cn.stive.mall.bean.mono;

/**
 * Created by dxt on 16/5/19.
 */
public class SearchArticle {
    private long id;
    private String title;
    private String subhead;
    private String cover_url;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getSubhead() {
        return subhead;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }
}
