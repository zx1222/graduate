package cn.stive.mall.dao;

import cn.stive.mall.bean.Article;
import cn.stive.mall.bean.Category;
import cn.stive.mall.util.SqlUtil;
import com.mchange.v2.sql.SqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by dxt on 16/4/12.
 */
@Repository
public class ArticleDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insertArticle(Article article) throws Exception {
        Map<String,Object> sql_map =  SqlUtil.getSqlMap(article,null);
        List<Object> args = new ArrayList<Object>();
        String sql = SqlUtil.getInsertSql("a_article",sql_map,args);

        jdbcTemplate.update(sql,args.toArray());


    }

    public List<Category> getCategory(){
        String sql  ="select id ,category_name from a_category ";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
    }

    public List<Article> getArticleList(int page,int len){
        String sql = "select * from a_article  where status = 0 limit ?,? ";
        return jdbcTemplate.query(sql,new Object[]{(page-1)*len,len},new BeanPropertyRowMapper<Article>(Article.class));
    }

    public Article getArticle(long id){
        String sql = "select * from a_article where id = ?" ;
        return jdbcTemplate.queryForObject(sql,new Object[]{id},new BeanPropertyRowMapper<Article>(Article.class));
    }

    public void delArticle(long id){
        String sql = "update a_article set status=1 where id = ? ";
        jdbcTemplate.update(sql,new Object[]{id});
    }

    public void updateArticle(Article article) throws Exception {
        Set<String> filter = new HashSet<String>();
        filter.add("id");
        Map<String,Object>sql_map = SqlUtil.getSqlMap(article,filter);
        Map<String,Object>query_map = new HashMap<String, Object>();
        query_map.put("id",article.getId());

        List<Object> args= new ArrayList<Object>();


        String set_sql = SqlUtil.getSetSQL(sql_map,args);
        String query_sql = SqlUtil.getQuerySQL(query_map,args,"a_article");
        String total_sql = "update a_article " +set_sql +" "+query_sql;

        jdbcTemplate.update(total_sql,args.toArray());
    }
}
