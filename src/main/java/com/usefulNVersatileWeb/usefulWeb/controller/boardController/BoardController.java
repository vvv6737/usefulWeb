package com.usefulNVersatileWeb.usefulWeb.controller.boardController;

import com.usefulNVersatileWeb.usefulWeb.service.UserService;
import com.usefulNVersatileWeb.usefulWeb.util.UrlUtil;
import com.usefulNVersatileWeb.usefulWeb.util.naverNewsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping(value = "/list", name = "게시판 목록")
    public String mainView(Model model, HttpServletRequest request) throws Exception {
        return UrlUtil.url("BoardList", request);
    }

    @GetMapping(value = "/register", name = "게시판 등록, 수정 페이지")
    public String register(Model model, HttpServletRequest request, HttpSession session) throws Exception {
        Object userInfo = session.getAttribute("USER");
        if(userInfo == null) {
            return "redirect:/user/login";
        }
        return UrlUtil.url("Register", request);
    }

    @GetMapping(value = "/detailView", name = "게시판 상세 페이지")
    public String detailView(Model model, HttpServletRequest request) throws Exception {
        return UrlUtil.url("DetailView", request);
    }
}
