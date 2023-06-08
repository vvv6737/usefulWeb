package com.usefulNVersatileWeb.usefulWeb.user.service;

import com.usefulNVersatileWeb.usefulWeb.user.mapper.UserMapper;
import com.usefulNVersatileWeb.usefulWeb.user.vo.UserVo;
import com.usefulNVersatileWeb.usefulWeb.util.ResponseUtil;
import com.usefulNVersatileWeb.usefulWeb.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public Boolean AddUser(UserVo userVo, HttpSession session) throws Exception {
        //아이디 체크를 하였는지.
        Boolean chk = (Boolean) session.getAttribute("isIdCheck");
        if(chk) {
            String password = userVo.getUserPassword();
            String shaPass = null;
            shaPass = StringUtil.sha256(password);
            userVo.setUserPassword(shaPass);
            userMapper.addUser(userVo);
            return true;
        } else {
            return false;
        }
    }

    public UserVo loginForm(UserVo userVO) throws NoSuchAlgorithmException {
        String password = userVO.getUserPassword();
        String shaPass = StringUtil.sha256(password);
        userVO.setUserPassword(shaPass);
        return userMapper.loginForm(userVO);
    }

    public Boolean isUserIdCheck(UserVo userVO, HttpSession session) throws Exception {
        if (userMapper.isUserIdCheck(userVO).size() == 0) {
            session.setAttribute("isIdCheck", true);
            return true;
        } else {
            return false;
        }
    }

}
