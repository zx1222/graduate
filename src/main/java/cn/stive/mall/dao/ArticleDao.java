package cn.stive.mall.dao;

import cn.stive.mall.bean.Article;
import cn.stive.mall.bean.Category;
import cn.stive.mall.bean.mono.ArticleDetail;
import cn.stive.mall.bean.mono.ArticlePage;
import cn.stive.mall.util.SqlUtil;
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

    public List<ArticlePage> getArticleDetailList(int page,int len){
        return getArticleDetailList(page,len,0);
    }
    public List<ArticlePage> getArticleDetailList(int page, int len ,long user_id){
        String sql = "select a.id,u.head_url userphoto ,u.nick_name name,a.id,a.cover_url article_cover,a.create_time time,c.category_name sort,a.title articletitle,a.description articlesubhead " +
                "from a_article a left join a_category c on c.id=a.category_id" +
                " left join u_user u on u.id = a.user_id where 1=1 ";


        List<Object> args = new ArrayList<Object>();

        if(user_id>0){
            sql += " user_id=? ";
            args.add(user_id);
        }
        if(page>0&&len>0){
            args.add((page-1)*len);
            args.add(page*len);
            sql += " limit ?,? ";
        }

        return jdbcTemplate.query(sql,new Object[]{(page-1)*len,len*page},new BeanPropertyRowMapper<ArticlePage>(ArticlePage.class));
    }

    public ArticleDetail getArticleDetail(long  id){
        String sql = "select u.nick_name author_name,a.cover_url article_cover,a.create_time article_time,a.title,a.description subhead,a.content main_content,a.author_name author,u.head_url" +
                "  from a_article a join u_user u on u.id=a.user_id where a.id=? ";

        return jdbcTemplate.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<ArticleDetail>(ArticleDetail.class));
    }

    public List<Category> getCategory(){
        String sql  ="select id ,category_name from a_category ";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
    }
}
