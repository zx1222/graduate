package cn.stive.mall.rest;

import cn.stive.mall.bean.Article;
import cn.stive.mall.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dxt on 16/4/12.
 */
@Controller
public class ArticleApi extends BaseHandler{
    @Autowired
    private ArticleService articleService;

    @RequestMapping("mono/article/add")
    @ResponseBody
    public Response addArticle(Article article) throws Exception {
        articleService.addArticle(article);
        return this.success();
    }

    @RequestMapping("mono/category")
    @ResponseBody
    public Response getCategory(Article article) throws Exception {
        return this.success( articleService.getCategory());
    }

    @RequestMapping("mono/article/list")
    @ResponseBody
    public Response addArticle(@RequestParam(required = false,defaultValue = "1")int page,
                               @RequestParam(required = false,defaultValue = "20")int len) throws Exception {

        return this.success( articleService.getArticleList(page,len)
        );
    }

    @RequestMapping("/mono/article/del")
    @ResponseBody
    public Response delArticle(long id){
        articleService.delArticle(id);
        return this.success();
    }

    @RequestMapping("/mono/article")
    @ResponseBody
    public Response getArticle(long id){
        return this.success(        articleService.getArticle(id)
        );
    }

    @RequestMapping("/mono/article/edit")
    @ResponseBody
    public Response updateArticle(Article article) throws Exception {
        articleService.updateArticle(article);
        return this.success();
    }


}
