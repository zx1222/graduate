package cn.stive.mall.service;

import cn.stive.mall.bean.Article;
import cn.stive.mall.bean.Category;
import cn.stive.mall.bean.mono.ArticleData;
import cn.stive.mall.bean.mono.ArticleDetail;
import cn.stive.mall.bean.mono.ArticlePage;
import cn.stive.mall.bean.mono.SearchArticle;
import cn.stive.mall.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by dxt on 16/4/12.
 */
@Service
public class ArticleService {
    @Autowired
    ArticleDao articleDao;

    public void addArticle(Article article) throws Exception {
        if(article.getId()==null) {
            articleDao.insertArticle(article);
        }else{
            articleDao.updateArticle(article);
        }
    }
    public List<Category> getCategory(){
        return articleDao.getCategory();
    }


    public List<Article> getArticleList(long site_id,long user_id,int page,int len){
        return articleDao.getArticleList(site_id,user_id,page,len);
    }

    public List<ArticleData> getArticleDataList(long site_id,int page,int len){
        return articleDao.getArticleDataList(site_id,page,len);
    }

    public Article getArticle(long id ){
        return articleDao.getArticle(id);
    }

    public void delArticle(long id){
        articleDao.delArticle(id);

    }

    public void updateArticle(Article article) throws Exception {
        articleDao.updateArticle(article);
    }

    public List<ArticlePage> getMonoArticleList(int page, int len){
        return articleDao.getArticleDetailList(page,len);
    }

    public ArticleDetail getArticleDetail(long id){
        return articleDao.getArticleDetail(id);
    }

    public List<ArticlePage> getMonoArticleList(int page,int len,long user_id){
        return articleDao.getArticleDetailList(page,len,user_id);
    }


    public List<SearchArticle> getSearchArticle(String s_str){
        return articleDao.getSearchArticle(s_str);
    }
    public List<SearchArticle> getFocusedArticle(long  user_id){
        return articleDao.getFocusedArticle(user_id);
    }
}
