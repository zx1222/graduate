package cn.stive.mall.rest;

import cn.stive.mall.bean.mono.Comment;
import cn.stive.mall.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dxt on 16/4/19.
 */
@Controller
public class CommentApi extends BaseHandler {

    @Autowired
    private CommentService commentService;



    @RequestMapping("/mono/article/comment")
    @ResponseBody
    public Response comment(Comment comment) throws Exception {
        commentService.addComment(comment);
        return this.success();
    }

    @RequestMapping("/mono/article/up")
    @ResponseBody
    public Response up(long article_id,long visitor_id){
        return this.success();
    }

    @RequestMapping("/mono/article/forward")
    @ResponseBody
    public Response forward(long article_id,long visitor_id){
        return this.success();
    }

    public CommentApi() {
    }

    @RequestMapping("/mono/article/comment/list")
    @ResponseBody
    public Response commentList(
            long article_id,
            @RequestParam(required = false,defaultValue = "1") int page,
            @RequestParam(required = false,defaultValue = "20") int len

    ){
        Map<String,Object> result = new HashMap<String, Object>();

        result.put("comment_count",commentService.getCommentCount(article_id));
        result.put("up_count",commentService.getUpCount(article_id));
        result.put("comment_list", commentService.getCommentData(article_id,page,len));
        return this.success(result);

    }





}
