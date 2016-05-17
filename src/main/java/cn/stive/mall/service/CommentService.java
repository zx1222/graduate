package cn.stive.mall.service;

import cn.stive.mall.bean.mono.Comment;
import cn.stive.mall.bean.mono.CommentData;
import cn.stive.mall.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dxt on 16/4/19.
 */
@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    public void addComment(Comment comment) throws Exception {
        commentDao.insertComment(comment);
    }

    public void forward(long article_id,long visitor_id) throws Exception {
        Map<String,Object> sql_map = new HashMap<String, Object>();

        sql_map.put("article_id",article_id);
        sql_map.put("visitor_id",visitor_id);

        commentDao.insertForward(sql_map);
    }

    public void upComment(long comment_id,long visitor_id) throws Exception {
        Map<String,Object> sql_map = new HashMap<String, Object>();

        sql_map.put("article_id",comment_id);
        sql_map.put("visitor_id",visitor_id);

        commentDao.insertCommentUp(sql_map);
    }

    public void upArticle(long article_id,long visitor_id) throws Exception {
        Map<String,Object> sql_map = new HashMap<String, Object>();

        sql_map.put("article_id",article_id);
        sql_map.put("visitor_id",visitor_id);

        commentDao.insertUp(sql_map);
    }

    public List<Comment> getCommentList(int page,int len){
        return commentDao.getComment();
    }


    public List<CommentData> getCommentData(long article_id,int page,int len ){
        return commentDao.getCommentData(article_id,page,len);
    }

    public int getUpCount(long article_id){
        return commentDao.getCommentCount(article_id);
    }

    public int getCommentCount(long article_id){
        return commentDao.getUpCount(article_id);
    }

    public int  focusSite(long site_id,long visitor_id){
       return  commentDao.addFocus(site_id,visitor_id);
    }

    public int collectArticle(long article_id,long visitor_id){
        return commentDao.addCollection(article_id,visitor_id);
    }
}
