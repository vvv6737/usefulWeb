package com.usefulNVersatileWeb.usefulWeb.user.controller;

import com.usefulNVersatileWeb.usefulWeb.user.service.UserService;
import com.usefulNVersatileWeb.usefulWeb.user.vo.UserVo;
import com.usefulNVersatileWeb.usefulWeb.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
public class RestUserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/isIdCheck", name = "회원가입 ID체크")
    public Boolean isIdCheck(UserVo userVo, HttpSession session) throws Exception {
        if(userService.isUserIdCheck(userVo).size() == 0) {
            session.setAttribute("isIdCheck", true);
            return true;
        } else {
            return false;
        }
    }

    @PostMapping(value =  "/addUser", name = "회원가입")
    public Boolean addUser(HttpSession session, UserVo userVO, RedirectAttributes attributes) throws Exception{
        try {
            //아이디 체크를 하였는지.
            Boolean chk = (Boolean) session.getAttribute("isIdCheck");
            if(chk) {
                String password = userVO.getUserPassword();
                String shaPass = null;
                shaPass = StringUtil.sha256(password);
                userVO.setUserPassword(shaPass);
                userService.AddUser(userVO);
                return true;
            } else {
                return false;
            }
        } catch (NoSuchAlgorithmException e) {
            attributes.addFlashAttribute("msg", "회원가입 에러");
            return false;
        }
    }

}