package com.usefulNVersatileWeb.usefulWeb.user.controller;

import com.usefulNVersatileWeb.usefulWeb.user.service.UserService;
import com.usefulNVersatileWeb.usefulWeb.user.vo.UserVo;
import com.usefulNVersatileWeb.usefulWeb.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Controller
public class ViewUserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/user/login", name = "로그인")
    public String login() {
        return "/view/user/Login";
    }

    @GetMapping(value = "/user/loginForm", name = "로그인폼")
    public String loginForm(UserVo userVo, HttpServletRequest request, RedirectAttributes attributes) {
        String url = request.getParameter("url") == "" ? "" : "?url=" + request.getParameter("url");
        try {
            if(userVo.getUserId() == ""){
                attributes.addFlashAttribute("nullId", "아이디를 입력해주세요.");
                return "redirect:/user/login" + url;
            }
            if(userVo.getUserPassword() == "") {
                attributes.addFlashAttribute("nullPwd", "비밀번호를 입력해주세요.");
                return "redirect:/user/login" + url;
            }
            UserVo user = userService.loginForm(userVo);
            if (SessionUtil.setUser(user, request)){
                if(url != "") {
                    return "redirect:" + request.getParameter("url");
                }
                return "redirect:/main/mainView";
            } else {
                attributes.addFlashAttribute("noUser", "사용자 정보가 없습니다.");
                return "redirect:/user/login" + url;
            }
        } catch (NoSuchAlgorithmException e) {
            return "redirect:/user/login" + url;
        }
    }

    @GetMapping(value = "/user/logout", name = "로그아웃")
    public String logout(HttpSession session, String url) {
        session.removeAttribute("USER");
        url = url == null ? "/mapper/main/mainView" : url;
        return "redirect:" + url;
    }

    @GetMapping(value = "/user/signUp", name = "회원가입 화면")
    public String signUp(HttpSession session) {
        session.removeAttribute("isIdCheck");
        return "/view/user/SignUp";
    }

}
