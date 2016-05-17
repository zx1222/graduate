package cn.stive.mall.dao;

import cn.stive.mall.bean.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dxt on 16/4/13.
 */
@Repository
public class CategoryDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Category> getCategory(){
        String sql = "select c.`id` ,category_name,c.create_time ,count(*) article_num from a_category c " +
                "left join a_article a on a.category_id = c.id  where c.status = 0 and a.status =0 group by c.id";

        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
    }
}
