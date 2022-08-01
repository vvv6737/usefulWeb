package com.usefulNVersatileWeb.usefulWeb.service;

import com.usefulNVersatileWeb.usefulWeb.mapper.UserMapper;
import com.usefulNVersatileWeb.usefulWeb.util.StringUtil;
import com.usefulNVersatileWeb.usefulWeb.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public List<UserVo> userInfo() throws Exception {
        return userMapper.userInfo();
    }

    public UserVo AddUser(UserVo userVo) throws Exception {
        userMapper.addUser(userVo);
        return userVo;
    }

    public UserVo loginForm(UserVo userVO) throws NoSuchAlgorithmException {
        String password = userVO.getUserPassword();
        String shaPass = StringUtil.sha256(password);
        userVO.setUserPassword(shaPass);
        return userMapper.loginForm(userVO);
    }
}
