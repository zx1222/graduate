package cn.stive.mall.bean.mono;

import java.util.Date;

/**
 * Created by dxt on 16/4/18.
 */
public class ArticleData {
    private long id;
    private String article_cover;
    private Date time;
    private String sort;
    private String articletitle;
    private String articlesubhead;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArticle_cover() {
        return article_cover;
    }

    public void setArticle_cover(String article_cover) {
        this.article_cover = article_cover;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getArticletitle() {
        return articletitle;
    }

    public void setArticletitle(String articletitle) {
        this.articletitle = articletitle;
    }

    public String getArticlesubhead() {
        return articlesubhead;
    }

    public void setArticlesubhead(String articlesubhead) {
        this.articlesubhead = articlesubhead;
    }
}
