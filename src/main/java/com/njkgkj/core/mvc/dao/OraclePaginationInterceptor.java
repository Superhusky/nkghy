package com.njkgkj.core.mvc.dao;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;

import java.sql.Connection;

/**
 * Created by zhangkaihua on 2017/7/3.
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class OraclePaginationInterceptor extends AbstractPaginationInterceptor {

    public String getLimitString(String sqlstr, int offset, int limit) {
        sqlstr = sqlstr.trim();
        StringBuffer pagingSelect = new StringBuffer(sqlstr.length() + 100);
        pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
        pagingSelect.append(sqlstr);
        pagingSelect.append(" ) row_ ) where rownum_ > ").append(offset).append(" and rownum_ <= ").append(offset + limit);
        return pagingSelect.toString();
    }
}
