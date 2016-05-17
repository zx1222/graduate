package cn.stive.mall.bean.mono;

import cn.stive.mall.bean.Site;
import cn.stive.mall.bean.User;
import cn.stive.mall.bean.UserInfo;

import java.util.Date;

/**
 * Created by dxt on 16/4/14.
 */
public class ArticlePage {
    private Long id;
    private String article_cover;
    private Date time;
    private String sort;
    private String articletitle;
    private String articlesubhead;
    private String site_icon ;
    private String site_name ;
    private Long site_id;

    private SiteAbstract site_info;
    private ArticleData article_data;
    private SociallyCount sociallyCount;


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

    public ArticlePage() {
        site_info = new SiteAbstract();
        article_data = new ArticleData();
    }

    public void setSite_id(Long site_id) {
        this.site_id = site_id;
        site_info.setSite_Id(site_id);

    }


    public SociallyCount getSociallyCount() {
        return sociallyCount;
    }

    public void setSite_icon(String site_icon) {

        site_info.setIcon_url(site_icon);
    }


    public void setName(String name) {

        site_info.setSite_name(name);
    }


    public void setId(Long id) {
        article_data.setId(id);
    }


    public void setArticle_cover(String article_cover) {
        article_data.setArticle_cover(article_cover);
    }


    public void setTime(Date time) {
        article_data.setTime(time);
    }


    public void setSort(String sort) {
        article_data.setSort(sort);
    }


    public void setArticletitle(String articletitle) {
        article_data.setArticletitle(articletitle);
    }

    public void setArticlesubhead(String articlesubhead) {
        article_data.setArticlesubhead(articlesubhead);
    }

    public SiteAbstract getSite_info(){
        return  this.site_info;
    }


    public ArticleData getArticle_data(){
        return this.article_data;
    }
}
