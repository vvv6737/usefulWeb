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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/login", name = "로그인")
    public String login() {
        return "/view/Login";
    }

    @GetMapping(value = "/logout", name = "로그아웃")
    public String logout(HttpSession session) {
        session.removeAttribute("USER");
        return "redirect:/main/mainView";
    }

    @GetMapping(value = "/signUp", name = "회원가입 화면")
    public String signUp(HttpSession session) {
        session.removeAttribute("isIdCheck");
        return "/view/SignUp";
    }

    @ResponseBody
    @PostMapping(value = "/isIdCheck", name = "회원가입 ID체크")
    public Boolean isIdCheck(UserVo userVo, HttpSession session) throws Exception {
        if(userService.isUserIdCheck(userVo).size() == 0) {
            session.setAttribute("isIdCheck", true);
            return true;
        } else {
            return false;
        }
    }

    @PostMapping(value = "/loginForm", name = "로그인폼")
    public String loginForm(UserVo userVo, HttpServletRequest request, RedirectAttributes attributes) {
        try {
            if(userVo.getUserId() == ""){
                attributes.addFlashAttribute("nullId", "아이디를 입력해주세요.");
                return "redirect:/login";
            }
            if(userVo.getUserPassword() == "") {
                attributes.addFlashAttribute("nullPwd", "비밀번호를 입력해주세요.");
                return "redirect:/login";
            }
            UserVo user = userService.loginForm(userVo);
            if (SessionUtil.setUser(user, request)){
                return "redirect:/main/mainView";
            } else {
                attributes.addFlashAttribute("noUser", "사용자 정보가 없습니다.");
                return "redirect:/login";
            }
        } catch (NoSuchAlgorithmException e) {
            return "redirect:/login";
        }
    }

    @ResponseBody
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
