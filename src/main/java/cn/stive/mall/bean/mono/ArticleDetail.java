package cn.stive.mall.bean.mono;

import java.util.Date;

/**
 * Created by dxt on 16/4/18.
 */
public class ArticleDetail {
    private String author_name;
    private String article_cover;
    private Date article_time;
    private String title;
    private String subhead;
    private String main_content;
    private String author;
    private String head_url;

    public String getHead_url() {
        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
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
