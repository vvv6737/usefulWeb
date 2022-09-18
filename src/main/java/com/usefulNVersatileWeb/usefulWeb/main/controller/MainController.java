package com.usefulNVersatileWeb.usefulWeb.main.controller;

import com.usefulNVersatileWeb.usefulWeb.user.service.UserService;
import com.usefulNVersatileWeb.usefulWeb.util.SessionUtil;
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

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/mainView", name = "메인페이지")
    public String mainView(Model model, HttpServletRequest request) throws Exception {
        System.out.println();
        return UrlUtil.url("Main", request);
    }

    @ResponseBody
    @PostMapping(value = "/naverNewsApi",  name = "네이버 뉴스 api")
    public String naverNews(HttpServletRequest req) {
        naverNewsApi naverNewsApi = new naverNewsApi();
        return naverNewsApi.search(req.getParameter("searchData"));
    }
}
