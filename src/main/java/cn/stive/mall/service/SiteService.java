package cn.stive.mall.service;

import cn.stive.mall.bean.Site;
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

    public List<Site> getSite(){
        return siteDao.getSites();

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
        siteDao.insertSiteMp(article_id,site_id);
    }

    public void removeArticle(long article_id,long site_id){
        siteDao.updateSiteMp(article_id,site_id);
    }

    
}
