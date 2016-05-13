package cn.stive.mall.rest;

import cn.stive.mall.bean.Site;
import cn.stive.mall.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dxt on 16/4/18.
 */
@Controller
public class SiteApi extends BaseHandler{
    @Autowired
    private SiteService siteService;

//    @RequestMapping
//    @ResponseBody
//    public Response methodName(){
//        return this.success();
//    }

    @RequestMapping("/admin/site/list")
    @ResponseBody
    public Response getSiteList(){
        return this.success(siteService.getSite());
    }

    @RequestMapping("/admin/site/detail")
    @ResponseBody
    public Response getSiteDetail(long id){
        return this.success(siteService.getSiteDetail(id));
    }


    @RequestMapping("/admin/site/del")
    @ResponseBody
    public Response delSite(Site site) throws Exception {
        siteService.updateSite(site);
        return this.success();
    }

    @RequestMapping("/admin/site/add")
    @ResponseBody
    public Response methodName(Site site) throws Exception {

        if(site.getId()==null) {
            siteService.insertSite(site);
        }else{
            siteService.updateSite(site);
        }
        return this.success();
    }

    @RequestMapping("/admin/site/add/article")
    @ResponseBody
    public Response addArticle(long article_id,long site_id){

        return this.success();
    }

    @RequestMapping("/admin/site/del/article")
    @ResponseBody
    public Response removeArticle(long article_id,long site_id){
        siteService.removeArticle(article_id,site_id);
        return this.success();
    }
}
