package com.usefulNVersatileWeb.usefulWeb.user.controller;

import com.usefulNVersatileWeb.usefulWeb.user.service.UserService;
import com.usefulNVersatileWeb.usefulWeb.util.SessionUtil;
import com.usefulNVersatileWeb.usefulWeb.util.StringUtil;
import com.usefulNVersatileWeb.usefulWeb.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/user")
public class ViewUserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/login", name = "로그인")
    public String login() {
        return "/view/user/Login";
    }

    @GetMapping(value = "/logout", name = "로그아웃")
    public String logout(HttpSession session, String url) {
        session.removeAttribute("USER");
        url = url == null ? "/main/mainView" : url;
        return "redirect:" + url;
    }

    @GetMapping(value = "/signUp", name = "회원가입 화면")
    public String signUp(HttpSession session) {
        session.removeAttribute("isIdCheck");
        return "/view/user/SignUp";
    }

}
