package cn.stive.mall.dao;

import cn.stive.mall.bean.User;
import cn.stive.mall.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by dxt on 16/4/8.
 */
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SqlUtil sqlUtil;


    public long createUser(User user) throws Exception {
        Set<String> filter = new HashSet<String>();
        filter.add("id");
        final List<Object> args = new ArrayList<Object>();

        final Map<String,Object> sql_map = SqlUtil.getSqlMap(user,filter);
        final String sql = SqlUtil.getInsertSql("u_user",sql_map,args);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                for(int i=0;i<args.size();i++){
                    ps.setObject(i+1,args.get(i));
                }
                return ps;
            }
        },keyHolder);
        return keyHolder.getKey().longValue();
    }

    public void updateUser(User user) throws Exception {
        sqlUtil.update(user,null,"u_user",user.getId());
    }
    public void updateUserActive(long user_id){
        String sql = "update u_user set is_active = 1 where id = ?";
        jdbcTemplate.update(sql,new Object[]{user_id});
    }

    public String getUserName(long user_id){
        String sql = "select nick_name from u_user where id = ? ";
        return jdbcTemplate.queryForObject(sql,new Object[]{user_id},String.class);
    }

    public User getUserByPassword(String email, String password){
        String sql = "select id,nick_name,user_name,email,head_url,descript from u_user where email=? and password=?";
        return jdbcTemplate.queryForObject(sql,new Object[]{email,password},new BeanPropertyRowMapper<User>(User.class));
    }


    public String getPassword(long u_id){
        String sql = "select password from u_user where u_id = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{u_id},String.class);
    }

    public void addFocus(long user_id, long visitor_id){
        String sql = "insert into u_focus (user_id,visitor_id) values(?,?)";
        jdbcTemplate.update(sql,new Object[]{user_id,visitor_id});
    }
}


