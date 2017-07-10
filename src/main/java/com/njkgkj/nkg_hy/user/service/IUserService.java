package com.njkgkj.nkg_hy.user.service;

import com.njkgkj.nkg_hy.user.entity.UserEntity;
import java.util.List;

/**
 * Created by zhangkaihua on 2017/7/4.
 */
public abstract interface IUserService {
    List<UserEntity> findAllUsers(UserEntity userEntity);
}
