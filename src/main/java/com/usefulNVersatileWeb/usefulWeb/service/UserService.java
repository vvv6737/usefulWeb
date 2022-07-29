package com.usefulNVersatileWeb.usefulWeb.service;

import com.usefulNVersatileWeb.usefulWeb.mapper.UserMapper;
import com.usefulNVersatileWeb.usefulWeb.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public List<UserVo> userInfo() throws Exception {
        return userMapper.userInfo();
    }
}
