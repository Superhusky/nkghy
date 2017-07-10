package com.njkgkj.nkg_hy.user.dao.impl;

import com.njkgkj.nkg_hy.base.dao.BaseDao;
import com.njkgkj.nkg_hy.user.dao.IUserDao;
import com.njkgkj.nkg_hy.user.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangkaihua on 2017/7/4.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDao implements IUserDao {
    public List<UserEntity> findAllUsers(UserEntity userEntity) {
        return find_myBatis("com.njkgkj.nkg_hy.user.dao.IUserDao.findAllUsers",userEntity);
    }
}