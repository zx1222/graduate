package cn.stive.mall.dao;

import cn.stive.mall.bean.Site;
import cn.stive.mall.bean.mono.SiteInfo;
import cn.stive.mall.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by dxt on 16/4/18.
 */
@Repository
public class SiteDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    SqlUtil sqlUtil;


    public Site getSiteDetail(long id ){
        String sql = "select * from a_site where id = ? and status = 0";

        return jdbcTemplate.queryForObject(sql,new Object[]{id}, new BeanPropertyRowMapper<Site>(Site.class));
    }
    public List<Site> getSites() {
        String sql = "select * from a_site where status = 0";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Site>(Site.class));
    }

    public void insertSites(Site site) throws Exception {
        Map<String, Object> sql_map = SqlUtil.getSqlMap(site, null);
        List<Object> args = new ArrayList<Object>();
        String sql = SqlUtil.getInsertSql("a_site", sql_map, args);

        jdbcTemplate.update(sql, args.toArray());

    }

    public void updateSite(Site site) throws Exception {
        Set<String> filter= new HashSet<String>();
        filter.add("id");
        sqlUtil.update(site,filter,"a_site", site.getId());
    }

    public void insertSiteMp(long article_id,long site_id ){
        String sql = "insert into a_site_mp ( article_id,site_id )  values(?,?) on DUPLICATE key update status=0";
        jdbcTemplate.update(sql,new Object[]{article_id,site_id});
    }
    public int  selecSiteMp(long article_id,long site_id){
        String sql = "select count(*) from a_site_mp where site_id=? and article_id = ? and status = 0";
        return jdbcTemplate.queryForInt(sql,new Object[]{article_id,site_id});
    }

    public void updateSiteMp(long article_id,long site_id ){
        String sql = "update a_site_mp set status = 1 where article_id=? and site_id=?";
        jdbcTemplate.update(sql,new Object[]{article_id,site_id});
    }

    public SiteInfo getSiteInfo(long site_id){
        String sql = "select s.id ,site_name,icon site_photo,cover_url site_cover,description site_descript," +
                " u.id user_id ,head_url user_photo,nick_name user_name from a_site s left join u_user u on u.id = s.user_id " +
                " where s.id = ? and s.status = 0";
       return jdbcTemplate.queryForObject(sql,new Object[]{site_id},new BeanPropertyRowMapper<SiteInfo>(SiteInfo.class));
    }


}

