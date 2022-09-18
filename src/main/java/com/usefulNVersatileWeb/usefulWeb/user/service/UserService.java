package com.usefulNVersatileWeb.usefulWeb.user.service;

import com.usefulNVersatileWeb.usefulWeb.user.mapper.UserMapper;
import com.usefulNVersatileWeb.usefulWeb.user.vo.UserVo;
import com.usefulNVersatileWeb.usefulWeb.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

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

    public List<UserVo> isUserIdCheck(UserVo userVO) throws Exception {
        return userMapper.isUserIdCheck(userVO);
    }

}
