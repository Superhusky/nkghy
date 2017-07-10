package com.njkgkj.nkg_hy.user.dao;

import com.njkgkj.nkg_hy.user.entity.UserEntity;
import java.util.List;

/**
 * Created by zhangkaihua on 2017/7/4.
 */
public interface IUserDao {
     List<UserEntity> findAllUsers(UserEntity userEntity);
}
