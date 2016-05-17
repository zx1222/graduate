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

    private int comment_count;
    private int up_count;
    private int forward_count;
    private int collect_count;




    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getUp_count() {
        return up_count;
    }

    public void setUp_count(int up_count) {
        this.up_count = up_count;
    }

    public int getForward_count() {
        return forward_count;
    }

    public void setForward_count(int forward_count) {
        this.forward_count = forward_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

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
