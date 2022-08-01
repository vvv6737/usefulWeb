package com.usefulNVersatileWeb.usefulWeb.controller.userController;

import com.usefulNVersatileWeb.usefulWeb.service.UserService;
import com.usefulNVersatileWeb.usefulWeb.util.SessionUtil;
import com.usefulNVersatileWeb.usefulWeb.util.StringUtil;
import com.usefulNVersatileWeb.usefulWeb.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/login", name = "로그인")
    public String login() {
        return "/view/Login";
    }

    @GetMapping(value = "/signUp", name = "회원가입 화면")
    public String signUp() {
        return "/view/SignUp";
    }

    @PostMapping(value = "/loginForm", name = "로그인폼")
    public String loginForm(UserVo userVo, HttpServletRequest request, RedirectAttributes attributes) {
        try {
            UserVo user = userService.loginForm(userVo);
            if (SessionUtil.setUser(user, request)){
                return "redirect:/main";
            } else {
                attributes.addFlashAttribute("msg", false);
                return "redirect:/login";
            }
        } catch (NoSuchAlgorithmException e) {
            return "redirect:/login";
        }
    }

    @PostMapping(value =  "/addUser", name = "회원가입")
    public String addUser(UserVo userVO) throws Exception{
        String password = userVO.getUserPassword();
        String shaPass = null;
        try {
            shaPass = StringUtil.sha256(password);
            userVO.setUserPassword(shaPass);
            userService.AddUser(userVO);
            return "redirect:/main";
        } catch (NoSuchAlgorithmException e) {
            return "redirect:/main";
        }
    }

}
