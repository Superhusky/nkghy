package com.njkgkj.nkg_hy.user.service.impl;

import com.njkgkj.nkg_hy.user.dao.IUserDao;
import com.njkgkj.nkg_hy.user.entity.UserEntity;
import com.njkgkj.nkg_hy.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangkaihua on 2017/7/4.
 */
@Service("userService")
public class UserServiceImpl implements IUserService{
    @Autowired
    @Qualifier("userDao")
    private IUserDao userDao;

    public List<UserEntity> findAllUsers(UserEntity userEntity) {
        return this.userDao.findAllUsers(userEntity);
    }
}
