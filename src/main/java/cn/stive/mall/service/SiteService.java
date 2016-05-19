package cn.stive.mall.service;

import cn.stive.mall.bean.Site;
import cn.stive.mall.bean.mono.SearchSite;
import cn.stive.mall.bean.mono.SiteInfo;
import cn.stive.mall.dao.SiteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dxt on 16/4/18.
 */
@Service
public class SiteService {

    @Autowired
    private SiteDao siteDao;



    public List<Site> getSite(long user_id){
        return siteDao.getSites(user_id);

    }

    public void updateSite(Site site) throws Exception {
        siteDao.updateSite(site);
    }

    public void insertSite(Site site) throws Exception {
        siteDao.insertSites(site);
    }

    public Site getSiteDetail(long id ){
        return siteDao.getSiteDetail(id);
    }

    public void addArticle(long article_id,long site_id){
        if(checkSameArticle(article_id,site_id)){
            throw new RuntimeException("该文章已经添加!");
        }
        siteDao.insertSiteMp(article_id,site_id);
    }

    private boolean checkSameArticle(long article_id,long site_id){
        return siteDao.selecSiteMp(site_id,article_id)>0;
    }

    public void removeArticle(long article_id,long site_id){
        siteDao.updateSiteMp(article_id,site_id);
    }

    public SiteInfo getSiteInfo(long site_id){
        return siteDao.getSiteInfo(site_id);
    }

    public List<SearchSite> getSearchSite(String s_str){
        return siteDao.getSearchSite(s_str);
    }

    
}
