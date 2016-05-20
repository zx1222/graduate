package cn.stive.mall.dao;

import cn.stive.mall.bean.mono.Comment;
import cn.stive.mall.bean.mono.CommentData;
import cn.stive.mall.bean.mono.Forward;
import cn.stive.mall.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by dxt on 16/4/19.
 */
@Repository
public class CommentDao {
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Autowired
    SqlUtil sqlUtil;

    public void insertComment(Comment comment) throws Exception {
        sqlUtil.insert(comment,"a_comment");

        String sql = "update a_article set comment_count= comment_count+1 where id = ?";
        jdbcTemplate.update(sql,new Object[]{comment.getArticle_id()});
    }

    public void insertForward(Map<String,Object> sql_map) throws Exception {
        sqlUtil.insert(sql_map,"a_forward");
    }

    public void insertUp(Map<String,Object> sql_map ) {
        int result =  sqlUtil.insert(sql_map,"a_article_up");

        String sql = "update a_article set up_count= up_count+1 where id = ?";
        jdbcTemplate.update(sql,new Object[]{sql_map.get("article_id")});
    }

    public void insertCommentUp(Map<String,Object> sql_map ) {
        sqlUtil.insert(sql_map,"a_comment_up");
    }

    public List<Comment> getComment(){
        String sql = "select * from a_comment where status = 0";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Comment>(Comment.class));
    }

    public List<CommentData> getCommentData(long article_id,int page,int len){
        StringBuilder builder  = new StringBuilder( "select u.id user_id,u.nick_name user_name,u.head_url,c.content,count(cu.id) up_count ,c.create_time ");
        builder.append(" from a_comment c  join u_user u on u.id = c.visitor_id left join a_comment_up cu on cu.comment_id = c.id  ");
        builder.append(" where c.status = 0 and article_id = ? ");
        builder.append(" group by c.id order by c.create_time desc ");
        builder.append(" limit ? ,? ");

        return jdbcTemplate.query(builder.toString(),new Object[]{article_id,(page-1)*len,len},new BeanPropertyRowMapper<CommentData>(CommentData.class));

    }

    public int getUpCount(long article_id){
        String sql = "select count(*) from a_article_up  where status = 0";
        return jdbcTemplate.queryForInt(sql);
    }

    public int getCommentCount(long article_id){
        String sql = "select count(*) from a_comment where status = 0";
        return jdbcTemplate.queryForInt(sql);
    }

    public int  addFocus(long site_id, long visitor_id){
        String sql = "insert into s_focus (site_id,visitor_id) values(?,?)  on DUPLICATE KEY UPDATE status = status^1";
        int result =  jdbcTemplate.update(sql,new Object[]{site_id,visitor_id});

        if(result==1){
            sql = "update a_site set focus_count = focus_count+1 where id = ?";
            jdbcTemplate.update(sql,new Object[]{site_id});
        }
        return result;
    }

    public int addCollection(long article_id,long visitor_id){
        String sql = "insert into a_collect (article_id,visitor_id) values(?,?)  on DUPLICATE KEY UPDATE status = status^1";
        int result =  jdbcTemplate.update(sql,new Object[]{article_id,visitor_id});

        if(result==1){
            sql = "update a_article set collect_count= collect_count+1 where id = ?";
            jdbcTemplate.update(sql,new Object[]{article_id});
        }

        return result;
    }
}
