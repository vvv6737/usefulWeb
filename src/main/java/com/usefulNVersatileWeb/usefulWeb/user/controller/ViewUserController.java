package com.usefulNVersatileWeb.usefulWeb.user.controller;

import com.usefulNVersatileWeb.usefulWeb.user.service.UserService;
import com.usefulNVersatileWeb.usefulWeb.user.vo.UserVo;
import com.usefulNVersatileWeb.usefulWeb.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping(value = "/loginForm", name = "로그인폼")
    public String loginForm(UserVo userVo, HttpServletRequest request, RedirectAttributes attributes) {
        try {
            if(userVo.getUserId() == ""){
                attributes.addFlashAttribute("nullId", "아이디를 입력해주세요.");
                return "redirect:/user/login";
            }
            if(userVo.getUserPassword() == "") {
                attributes.addFlashAttribute("nullPwd", "비밀번호를 입력해주세요.");
                return "redirect:/user/login";
            }
            UserVo user = userService.loginForm(userVo);
            if (SessionUtil.setUser(user, request)){
                String url = request.getParameter("url");
                if(url != "") {
                    return "redirect:" + url;
                }
                return "redirect:/main/mainView";
            } else {
                attributes.addFlashAttribute("noUser", "사용자 정보가 없습니다.");
                return "redirect:/user/login";
            }
        } catch (NoSuchAlgorithmException e) {
            return "redirect:/user/login";
        }
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
