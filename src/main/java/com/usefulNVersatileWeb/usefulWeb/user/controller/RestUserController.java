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
        return userService.isUserIdCheck(userVo, session);
    }

    @PostMapping(value =  "/addUser", name = "회원가입")
    public Boolean addUser(HttpSession session, UserVo userVO, RedirectAttributes attributes) throws Exception{
        boolean res = false;
        try {
            res = userService.AddUser(userVO, session);
        } catch (NoSuchAlgorithmException e) {
            attributes.addFlashAttribute("msg", "회원가입 에러");
            return res;
        }
        return res;
    }
}