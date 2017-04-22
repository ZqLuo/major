package com.example.util;

import com.example.config.APIProperties;
import com.sun.java.swing.ui.CommonUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by zqLuo
 */
@Repository
public class JdbcUtil {

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

    public PageForSql queryForPage(String sql,int page,int size,Class clazz,Object...params){
        PageForSql pageForSql = new PageForSql();
        String countSql = "slect count(*) from (" + sql + ")";
        pageForSql.setPage(page);
        pageForSql.setPageSize(page);
        pageForSql.setTotal(this.queryCount(sql,params));
        List<Map<String,Object>> result = jdbcTemplate.queryForList(sql,params);
        try {
            List objects = CommontUtil.parseJdbcMapToObject(result,clazz);
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
}
