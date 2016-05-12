package cn.stive.mall.bean.mono;

import cn.stive.mall.bean.User;
import cn.stive.mall.bean.UserInfo;

import java.util.Date;

/**
 * Created by dxt on 16/4/14.
 */
public class ArticlePage {
    private long id;
    private String article_cover;
    private Date time;
    private String sort;
    private String articletitle;
    private String articlesubhead;
    private String userphoto ;
    private String name ;

    private UserInfo user_info;
    private ArticleData article_data;
    private SociallyCount sociallyCount;

    public ArticlePage() {
        user_info = new UserInfo();
        article_data = new ArticleData();
    }

    public void setUserphoto(String userphoto) {

        user_info.setUserphoto(userphoto);
    }


    public void setName(String name) {

        user_info.setName(name);
    }


    public void setId(long id) {
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

    public UserInfo getUser_info(){
        return  this.user_info;
    }

    public ArticleData getArticle_data(){
        return this.article_data;
    }
}
