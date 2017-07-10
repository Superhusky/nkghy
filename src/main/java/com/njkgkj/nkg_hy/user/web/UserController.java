package com.njkgkj.nkg_hy.user.web;

import com.njkgkj.nkg_hy.base.entity.PaginationEntity;
import com.njkgkj.nkg_hy.user.entity.UserEntity;
import com.njkgkj.nkg_hy.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhangkaihua on 2017/7/3.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @ResponseBody
    public List<UserEntity> findAllUsers(UserEntity userEntity, PaginationEntity page) {
        return this.userService.findAllUsers(userEntity);
    }

    public String login(){
        return "login";
    }
}
