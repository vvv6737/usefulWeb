package com.usefulNVersatileWeb.usefulWeb.user.controller;

import com.usefulNVersatileWeb.usefulWeb.user.service.UserService;
import com.usefulNVersatileWeb.usefulWeb.util.SessionUtil;
import com.usefulNVersatileWeb.usefulWeb.util.StringUtil;
import com.usefulNVersatileWeb.usefulWeb.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping(value = "/loginForm", name = "로그인폼")
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

}