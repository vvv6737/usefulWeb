package com.usefulNVersatileWeb.usefulWeb.controller.mainController;

import com.usefulNVersatileWeb.usefulWeb.mapper.UserMapper;
import com.usefulNVersatileWeb.usefulWeb.service.UserService;
import com.usefulNVersatileWeb.usefulWeb.util.ModelUtil;
import com.usefulNVersatileWeb.usefulWeb.util.naverNewsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    UserService userService;

    final static String WEB_PATH = "/view/";
    private final static String ID = "DzXt1NqAAm0gzO4sSP1r";
    private final static String SECRET = "u_sjmtAKb3";

    @GetMapping(value = "/mainView", name = "메인페이지")
    public String mainView(Model model, HttpServletRequest req) throws Exception {
        model.addAttribute("userList", userService.userInfo());
        String search = req.getParameter("search");
        String response = "검색 바랍니다.";
        if(search != null) {
            naverNewsApi crawler = new naverNewsApi();
            String url = URLEncoder.encode(search, "UTF-8");
            response = crawler.search(ID, SECRET, url);
        }
        model.addAttribute("result", response);
        model.addAttribute("search", search);
        return WEB_PATH + "main";
    }

    @ResponseBody
    @PostMapping(value = "/naverNewsApi",  name = "네이버 뉴스 api")
    public String naverNews(HttpServletRequest req) {
        String searchData = req.getParameter("searchData");
        String response = null;
        try {
            naverNewsApi crawler = new naverNewsApi();
            String url = URLEncoder.encode(searchData, "UTF-8");
            response = crawler.search(ID, SECRET, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
