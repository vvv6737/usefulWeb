package com.usefulNVersatileWeb.usefulWeb.controller.mainController;

import com.usefulNVersatileWeb.usefulWeb.util.UrlUtil;
import com.usefulNVersatileWeb.usefulWeb.service.UserService;
import com.usefulNVersatileWeb.usefulWeb.util.naverNewsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/mainView", name = "메인페이지")
    public String mainView(Model model, HttpServletRequest request) throws Exception {
        return UrlUtil.url("Main", request);
    }

    @GetMapping(value = "/register", name = "게시판 등록, 수정 페이지")
    public String register(Model model, HttpServletRequest request) throws Exception {
        return UrlUtil.url("Register", request);
    }

    @GetMapping(value = "/DetailView", name = "게시판 상세 페이지")
    public String detailView(Model model, HttpServletRequest request) throws Exception {
        return UrlUtil.url("DetailView", request);
    }

    @ResponseBody
    @PostMapping(value = "/naverNewsApi",  name = "네이버 뉴스 api")
    public String naverNews(HttpServletRequest req) {
        naverNewsApi naverNewsApi = new naverNewsApi();
        return naverNewsApi.search(req.getParameter("searchData"));
    }
}
