package cn.stive.mall.dao;

import cn.stive.mall.bean.mono.Comment;
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
    }

    public void insertForward(Map<String,Object> sql_map) throws Exception {
        sqlUtil.insert(sql_map,"a_forward");
    }

    public void insertUp(Map<String,Object> sql_map ) {
        sqlUtil.insert(sql_map,"a_article_up");
    }

    public List<Comment> getComment(){
        String sql = "select * from a_comment where status = 0";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Comment>(Comment.class));
    }

}
