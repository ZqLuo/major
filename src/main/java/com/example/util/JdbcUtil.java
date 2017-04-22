package com.example.util;

import com.example.config.APIProperties;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zqLuo
 */
@Repository
public class JdbcUtil<T> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 数据库类型
     */
    public static final String DATABSE_TYPE_MYSQL ="mysql";
    public static final String DATABSE_TYPE_POSTGRE ="postgresql";
    public static final String DATABSE_TYPE_ORACLE ="oracle";

    /**
     * 分页SQL
     */
    public static final String MYSQL_SQL = "select * from ( {0}) sel_tab00 limit {1},{2}";         //mysql
    public static final String POSTGRE_SQL = "select * from ( {0}) sel_tab00 limit {1} offset {2}";//postgresql
    public static final String ORACLE_SQL = "select * from (select row_.*,rownum rownum_ from ({0}) row_ where rownum <= {1}) where rownum_>{2}"; //oracle

    /**
     * 按照数据库类型，封装SQL
     */
    public static String createPageSql(String sql, int page, int rows){
        APIProperties apiProperties = AppUtil.getBean(APIProperties.class);
        String dataBaseType = apiProperties.getDataBaseType(); //获取数据库类型
        int beginNum = (page - 1) * rows;
        String[] sqlParam = new String[3];
        sqlParam[0] = sql;
        sqlParam[1] = beginNum+"";
        sqlParam[2] = rows+"";
        if(dataBaseType.indexOf(DATABSE_TYPE_MYSQL)!=-1){
            sql = MessageFormat.format(MYSQL_SQL, sqlParam);
        }else if(dataBaseType.indexOf(DATABSE_TYPE_POSTGRE)!=-1){
            sql = MessageFormat.format(POSTGRE_SQL, sqlParam);
        }else if(dataBaseType.indexOf(DATABSE_TYPE_ORACLE)!=-1){
            int beginIndex = (page-1)*rows;
            int endIndex = beginIndex+rows;
            sqlParam[2] = beginIndex+"";
            sqlParam[1] = endIndex+"";
            sql = MessageFormat.format(ORACLE_SQL, sqlParam);
        }
        return sql;
    }

    public String createCountSql(String sql){
        return"select count(*) from (" + sql + ") as total";
    }

    public PageForSql queryForPage(String sql,int page,int size,Class<T> clazz,Object...params){
        PageForSql pageForSql = new PageForSql();
        pageForSql.setPage(page);
        pageForSql.setPageSize(size);
        pageForSql.setTotal(this.queryCount(createCountSql(sql),params));
        List<Map<String,Object>> result = jdbcTemplate.queryForList(createPageSql(sql,page,size),params);
        try {
            List<T> list = this.parseJdbcMapToObject(result,clazz);
            pageForSql.setResult(list);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return pageForSql;
    }

    public Integer queryCount(String sql,Object...params){
        return jdbcTemplate.queryForObject(sql,params,Integer.class);
    }

    public static <T> List<T> parseJdbcMapToObject(List<Map<String,Object>> jdbcMaps,Class<T> tClass) throws InstantiationException, IllegalAccessException, InvocationTargetException{
        List<T> results = new ArrayList<T>();
        if(jdbcMaps != null && jdbcMaps.size()>0){
            for(Map<String,Object> jdbcMap:jdbcMaps){
                Map<String,Object> map = new HashMap<String, Object>();
                T t = tClass.newInstance();
                Field[] fields = t.getClass().getDeclaredFields();
                for(Field field : fields){
                    Object value = jdbcMap.get(field.getName().toUpperCase());
                    if(StringUtil.isNotEmpty(value)){
                        map.put(field.getName(), value);
                    }
                }
                BeanUtils.populate(t, map);
                results.add(t);
            }
        }
        return results;
    }

    /**
     * 将jdbctemplate查询得到的map转换为对象（map中key需与对象的属性名相同）
     * 不区分大小写
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    public static Object parseJdbcMapToObject(Map<String,Object> jdbcMap,Class clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException{
        Map<String,Object> map = new HashMap<String, Object>();
        Object o = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            Object value = jdbcMap.get(field.getName().toUpperCase());
            if(StringUtil.isNotEmpty(value)){
                map.put(field.getName(), value);
            }
        }
        BeanUtils.populate(o, map);
        return o;
    }
}
