package cn.stive.mall.rest;

import cn.stive.mall.bean.mono.Comment;
import cn.stive.mall.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/mono/article/comment/list")
    @ResponseBody
    public Response commentList(
            @RequestParam(required = false,defaultValue = "1") int page,
            @RequestParam(required = false,defaultValue = "5") int len

    ){
        return this.success(commentService.getCommentList(page,len));
    }



}
