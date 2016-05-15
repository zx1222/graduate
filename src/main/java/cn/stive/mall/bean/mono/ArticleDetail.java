package cn.stive.mall.bean.mono;

import java.util.Date;

/**
 * Created by dxt on 16/4/18.
 */
public class ArticleDetail {
    private String article_cover;
    private Date article_time;
    private String title;
    private String subhead;
    private String main_content;
    private String author;

    private long site_id;
    private String site_icon;
    private String site_name;

    public long getSite_id() {
        return site_id;
    }

    public void setSite_id(long site_id) {
        this.site_id = site_id;
    }

    public String getSite_icon() {
        return site_icon;
    }

    public void setSite_icon(String site_icon) {
        this.site_icon = site_icon;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }



    public String getArticle_cover() {
        return article_cover;
    }

    public void setArticle_cover(String article_cover) {
        this.article_cover = article_cover;
    }

    public Date getArticle_time() {
        return article_time;
    }

    public void setArticle_time(Date article_time) {
        this.article_time = article_time;
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

    public String getMain_content() {
        return main_content;
    }

    public void setMain_content(String main_content) {
        this.main_content = main_content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
