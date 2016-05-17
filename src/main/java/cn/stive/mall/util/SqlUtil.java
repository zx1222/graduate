package cn.stive.mall.util;


import cn.stive.mall.bean.Article;
import cn.stive.mall.bean.mono.Forward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * Created by dxt on 16/4/8.
 */
@Repository
public class SqlUtil {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static Map getSqlMap(Object obj,Set<String> filter) throws Exception{
        Map<String,Object> map = new HashMap<String, Object>();

        List<PropertyDescriptor> descriptors = IntrospectorUtil.getInstance().getPropertyDescriptors(obj.getClass());

        for(PropertyDescriptor pd : descriptors){
            if(filter==null||!inFilter(filter,pd.getName())) {

                Object o = pd.getReadMethod().invoke(obj, null);

                if(o!=null) {
                    map.put(pd.getName(), o);
                }
            }
        }
        return map;
    }

    public static String getSetSQL(Map<String,Object> set_map,List<Object> args){
        StringBuilder setSql  = new StringBuilder("set ");
        Set<String> updateKeySet =  set_map.keySet();
        Iterator<String> iterator = updateKeySet.iterator();

        while(iterator.hasNext()){
            String next = iterator.next();
            Object o = set_map.get(next);
            if(o!=null){
                setSql.append("`"+next+"`"+" =? , ");
                args.add(o);
            }
        }
        if(setSql.indexOf(",")>0) {
            setSql.deleteCharAt(setSql.lastIndexOf(","));
        }


        return setSql.toString();
    }

    public static String getQuerySQL(Map<String,Object> query_map,List<Object> args,String table){

        StringBuilder querySQL = new StringBuilder(" where ");
        if(!"".equals(table))
            table += '.';

        Set<String> keyWords = query_map.keySet();
        Iterator<String> i_keywords = keyWords.iterator();
        if(!i_keywords.hasNext()){
            return "";
        }
        while(i_keywords.hasNext()){
            String key = i_keywords.next();
            Object o = query_map.get(key);
            if(o!=null){
                if(o instanceof String){

                    querySQL.append(table+"`"+key+"`" + " like ?  ");
                    args.add("%"+o+"%");
                }else {
                    querySQL.append(table+"`" + key + "`" + "=?  ");
                    args.add(o);
                }
                if(i_keywords.hasNext()){
                    querySQL.append(" and ");
                }
            }
        }


        return querySQL.toString();
    }
    private static  boolean inFilter(Set<String> filter,String pdName){
        return filter.contains(pdName);
    }

    public static String getInsertSql(String table,Map<String,Object> add_map,List<Object> args){
        StringBuilder filedSql = new StringBuilder("insert into "+table+" ( ");
        StringBuilder valueSql = new StringBuilder("(");

        Set<String> keys = add_map.keySet();

        Iterator<String> i_keys = keys.iterator();

        while(i_keys.hasNext()){
            String key = i_keys.next();
            Object o = add_map.get(key);
            if(o!=null){
                filedSql.append("`"+key+"`");
                valueSql.append("?");
                args.add(o);
            }
            if(i_keys.hasNext()){
                filedSql.append(",");
                valueSql.append(",");
            }
        }
        filedSql.append(")");
        valueSql.append(")");

        String totalSql = filedSql.toString()+" values "+valueSql.toString();

        return totalSql;



    }


    public void insert(Object obj,String table) throws Exception {
        Map<String,Object> sql_map =  SqlUtil.getSqlMap(obj,null);
       insert(sql_map,table);

    }

    public int  insert(Map<String,Object> sql_map,String table){
        List<Object> args = new ArrayList<Object>();
        String sql = SqlUtil.getInsertSql(table,sql_map,args);

        return jdbcTemplate.update(sql,args.toArray());

    }

    public void update(Object obj,Set<String> filter,String table,long id) throws Exception {
        Map<String,Object>sql_map = getSqlMap(obj,filter);
        Map<String,Object>query_map = new HashMap<String, Object>();
        query_map.put("id",id);

        List<Object> args= new ArrayList<Object>();


        String set_sql = SqlUtil.getSetSQL(sql_map,args);
        String query_sql = SqlUtil.getQuerySQL(query_map,args,table);
        String total_sql = "update  "+table+" "+set_sql +" "+query_sql;

        jdbcTemplate.update(total_sql,args.toArray());
    }

}
