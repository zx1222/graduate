package cn.stive.mall.rest;

import cn.stive.mall.bean.Article;
import cn.stive.mall.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dxt on 16/4/12.
 */
@Controller
public class
ArticleApi extends BaseHandler{
    @Autowired
    private ArticleService articleService;

    @RequestMapping("admin/article/add")
    @ResponseBody
    public Response article(Article article) throws Exception {

        articleService.addArticle(article);
        return this.success();

    }

    @RequestMapping("admin/category")
    @ResponseBody
    public Response getCategory(Article article) throws Exception {
        return this.success( articleService.getCategory());
    }

    @RequestMapping("admin/article/list")
    @ResponseBody
    public Response addArticle(@RequestParam(required = false,defaultValue = "1")int page,
                               @RequestParam(required = false,defaultValue = "20")int len,
                               @RequestParam(required = false,defaultValue = "0")long site_id,
                               long user_id) throws Exception {

        return this.success( articleService.getArticleList(site_id,user_id,page,len)
        );
    }



    @RequestMapping("/admin/article/del")
    @ResponseBody
    public Response delArticle(long id){
        articleService.delArticle(id);
        return this.success();
    }

    @RequestMapping("/admin/article")
    @ResponseBody
    public Response getArticle(long id){
        return this.success(        articleService.getArticle(id)
        );
    }

    @RequestMapping("/admin/article/edit")
    @ResponseBody
    public Response updateArticle(Article article) throws Exception {
        articleService.updateArticle(article);
        return this.success();
    }

    @RequestMapping("/mono/article/list")
    @ResponseBody
    public Response getMonoArticle(@RequestParam(required = false,defaultValue = "1")int page,
                                   @RequestParam(required = false,defaultValue = "20")int len) throws Exception {

        return this.success(articleService.getMonoArticleList(page,len));
    }

    @RequestMapping("/mono/article/detail")
    @ResponseBody
    public Response getMonoArticle(long id) throws Exception {

        return this.success(articleService.getArticleDetail(id));
    }
    @RequestMapping(value = "/mono/article/list/user",method = RequestMethod.GET)
    @ResponseBody
    public Response getUserArticle(long user_id,
                                   @RequestParam(required = false,defaultValue = "1")int page,
                                   @RequestParam(required = false,defaultValue = "20")int len){
        return this.success(articleService.getMonoArticleList(page,len,user_id));
    }



    @RequestMapping("mono/article/search")
    @ResponseBody
    public Response searchArticle(String s_str){


        return this.success(articleService.getSearchArticle(s_str));
    }

    @RequestMapping("mono/article/collect/list")
    @ResponseBody
    public Response focusArticle(long  user_id){
        return this.success(articleService.getFocusedArticle(user_id));
    }

}
