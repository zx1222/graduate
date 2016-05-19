package cn.stive.mall.rest;

import cn.stive.mall.bean.Article;
import cn.stive.mall.bean.Site;
import cn.stive.mall.bean.mono.ArticleData;
import cn.stive.mall.bean.mono.SiteInfo;
import cn.stive.mall.service.ArticleService;
import cn.stive.mall.service.CommentService;
import cn.stive.mall.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dxt on 16/4/18.
 */
@Controller
public class SiteApi extends BaseHandler{
    @Autowired
    private SiteService siteService;
    @Autowired
    private CommentService commentService;

    @Autowired
    ArticleService articleService;

//    @RequestMapping
//    @ResponseBody
//    public Response methodName(){
//        return this.success();
//    }

    @RequestMapping("/admin/site/list")
    @ResponseBody
    public Response getSiteList(long user_id){
        return this.success(siteService.getSite(user_id));
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
        siteService.addArticle(article_id,site_id);
        return this.success();
    }

    @RequestMapping("/admin/site/del/article")
    @ResponseBody
    public Response removeArticle(long article_id,long site_id){
        siteService.removeArticle(article_id,site_id);
        return this.success();
    }

    @RequestMapping("/mono/site/detail")
    @ResponseBody
    public Response monoSiteDetail(long site_id,@RequestParam(required = false,defaultValue = "1")int page,
                                   @RequestParam(required = false,defaultValue = "20")int len){
        Map<String,Object> result = new HashMap<String, Object>();

        SiteInfo site_info = siteService.getSiteInfo(site_id);
        result.put("site_info",site_info);

        List<ArticleData> article_list=  articleService.getArticleDataList(site_id,page,len);

        result.put("article_info",article_list);

        return this.success(result);
    }


    @RequestMapping("mono/site/focus")
    @ResponseBody
    public Response focusSite(long site_id,long visitor_id){
        int result = commentService.focusSite(site_id,visitor_id);

        if(result ==1) {
            return this.success("focus");
        }else{
            return this.success("unfocus");
        }
    }


    @RequestMapping("mono/site/search")
    @ResponseBody
    public Response searchSite(String s_str){


        return this.success(siteService.getSearchSite(s_str));
    }




}
