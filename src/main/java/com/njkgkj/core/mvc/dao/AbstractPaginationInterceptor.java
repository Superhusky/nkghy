package com.njkgkj.core.mvc.dao;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by zhangkaihua on 2017/7/3.
 */
public abstract class AbstractPaginationInterceptor implements Interceptor {
    private Logger log = LoggerFactory.getLogger(AbstractPaginationInterceptor.class);

    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        ObjectFactory objectFactory = new DefaultObjectFactory();
        ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();
        ReflectorFactory objectReflactorFactory = new DefaultReflectorFactory();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, objectFactory, objectWrapperFactory, new DefaultReflectorFactory());
        RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
        if ((rowBounds == null) || (rowBounds == RowBounds.DEFAULT)) {
            return invocation.proceed();
        }
        String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        metaStatementHandler.setValue("delegate.boundSql.sql", getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit()));
        metaStatementHandler.setValue("delegate.rowBounds.offset", 0);
        metaStatementHandler.setValue("delegate.rowBounds.limit", Integer.MAX_VALUE);
        if (this.log.isDebugEnabled()) {
            BoundSql boundSql = statementHandler.getBoundSql();
            this.log.debug("创建分页SQL: " + boundSql.getSql());
        }
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        if ((target instanceof StatementHandler)) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    public void setProperties(Properties arg0) {
    }

    /**
     * 生成分页查询的语句
     *
     * @param sqlstr 原始SQL语句
     * @param offset 偏移量，通过页数 * 每页条数获得
     * @param limit  结果长度
     * @return
     */
    public abstract String getLimitString(String sqlstr, int offset, int limit);
}
