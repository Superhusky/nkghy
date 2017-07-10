package com.njkgkj.nkg_hy.base.dao;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;

/**
 * Created by zhangkaihua on 2017/7/4.
 */
public class BaseDao extends SqlSessionDaoSupport {
    @Autowired
    @Qualifier("sqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public SqlSession getMyBatisSession() {
        return getSqlSession();
    }

    public <T> int delete_myBatis(String sqlId, T entity) {
        return getSqlSession().delete(sqlId, entity);
    }

    public <T> List<T> find_myBatis(String sqlId, Object paramObject) {
        return find_myBatis(sqlId, paramObject, -1, -1);
    }

    public <T> List<T> find_myBatis(String sqlId, Object paramObject, int offset, int size) {
        return find_myBatis(sqlId, paramObject, offset, size, true);
    }

    public <T> List<T> find_myBatis(String sqlId, Object paramObject, int offset, int size, boolean isColNameToUpperCase) {
        List rtnList = null;
        if ((offset >= 0) && (size > 0)) {
            RowBounds rowBounds = new RowBounds(offset, size);
            rtnList = getSqlSession().selectList(sqlId, paramObject, rowBounds);
        } else {
            rtnList = getSqlSession().selectList(sqlId, paramObject);
        }
        if (isColNameToUpperCase) {
            if (isToUpperCase(rtnList)) {
                rtnList = convertListKeyToUpperCase(rtnList);
            }
        } else if (isToLowerCase(rtnList)) {
            rtnList = convertListKeyToLowerCase(rtnList);
        }
        return rtnList;
    }

    private boolean isToUpperCase(Object resultObj) {
        boolean rtnValue = false;
        if (resultObj != null) {
            if (List.class.isAssignableFrom(resultObj.getClass())) {
                List rtnList = (List) resultObj;
                if ((rtnList != null) && (rtnList.size() > 0)) {
                    Object oneRecord = rtnList.get(0);
                    if ((oneRecord != null) && (Map.class.isAssignableFrom(oneRecord.getClass()))) {
                        Map oneMap = (Map) oneRecord;
                        rtnValue = isMapKeyToUpperCase(oneMap);
                    }
                }
            } else if (Map.class.isAssignableFrom(resultObj.getClass())) {
                rtnValue = isMapKeyToUpperCase((Map) resultObj);
            }
        }
        return rtnValue;
    }

    private boolean isToLowerCase(Object resultObj) {
        boolean rtnValue = false;
        if (resultObj != null) {
            if (List.class.isAssignableFrom(resultObj.getClass())) {
                List rtnList = (List) resultObj;
                if ((rtnList != null) && (rtnList.size() > 0)) {
                    Object oneRecord = rtnList.get(0);
                    if ((oneRecord != null) && (Map.class.isAssignableFrom(oneRecord.getClass()))) {
                        Map oneMap = (Map) oneRecord;
                        rtnValue = isMapKeyToLowerCase(oneMap);
                    }
                }
            } else if (Map.class.isAssignableFrom(resultObj.getClass())) {
                rtnValue = isMapKeyToUpperCase((Map) resultObj);
            }
        }
        return rtnValue;
    }

    private boolean isMapKeyToUpperCase(Map<String, Object> oneMap) {
        boolean rtnValue = false;
        if (oneMap != null) {
            Iterator<String> oneRecordKeys = oneMap.keySet().iterator();
            if (oneRecordKeys.hasNext()) {
                String oneKey = (String) oneRecordKeys.next();
                if (!oneKey.equals(oneKey.toUpperCase())) {
                    rtnValue = true;
                }
            }
        }
        return rtnValue;
    }

    private boolean isMapKeyToLowerCase(Map<String, Object> oneMap) {
        boolean rtnValue = false;
        if (oneMap != null) {
            Iterator<String> oneRecordKeys = oneMap.keySet().iterator();
            if (oneRecordKeys.hasNext()) {
                String oneKey = (String) oneRecordKeys.next();
                if (!oneKey.equals(oneKey.toLowerCase())) {
                    rtnValue = true;
                }
            }
        }
        return rtnValue;
    }

    private List<Map<String, Object>> convertListKeyToUpperCase(List<?> list) {
        List<Map<String, Object>> rtnValue = null;
        if ((list != null) && (list.size() > 0)) {
            rtnValue = new ArrayList();
            for (int index = 0; index < list.size(); index++) {
                Map oneRecord = (Map) list.get(index);
                if (oneRecord != null) {
                    rtnValue.add(convertMapKeyToUpperCase(oneRecord));
                } else {
                    rtnValue.add(null);
                }
            }
        }
        return rtnValue;
    }

    private Map<String, Object> convertMapKeyToUpperCase(Map<String, Object> oneRecord) {
        Map<String, Object> rtnValue = new HashMap();
        Iterator<String> keys = oneRecord.keySet().iterator();
        while (keys.hasNext()) {
            String keyStr = (String) keys.next();
            Object value = oneRecord.get(keyStr);
            rtnValue.put(keyStr.toUpperCase(), value);
        }
        return rtnValue;
    }

    private List<Map<String, Object>> convertListKeyToLowerCase(List<?> list) {
        List<Map<String, Object>> rtnValue = null;
        if ((list != null) && (list.size() > 0)) {
            rtnValue = new ArrayList();
            for (int index = 0; index < list.size(); index++) {
                Map oneRecord = (Map) list.get(index);
                if (oneRecord != null) {
                    rtnValue.add(convertMapKeyToLowerCase(oneRecord));
                } else {
                    rtnValue.add(null);
                }
            }
        }
        return rtnValue;
    }

    private Map<String, Object> convertMapKeyToLowerCase(Map<String, Object> oneRecord) {
        Map<String, Object> rtnValue = new HashMap();
        Iterator<String> keys = oneRecord.keySet().iterator();
        while (keys.hasNext()) {
            String keyStr = (String) keys.next();
            Object value = oneRecord.get(keyStr);
            rtnValue.put(keyStr.toLowerCase(), value);
        }
        return rtnValue;
    }
}
