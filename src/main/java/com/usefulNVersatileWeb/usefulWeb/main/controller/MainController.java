package com.usefulNVersatileWeb.usefulWeb.main.controller;

import com.usefulNVersatileWeb.usefulWeb.main.service.MainService;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    MainService mainService;

    @GetMapping(value = "/mainView", name = "메인페이지")
    public String mainView(Model model, HttpServletRequest request) throws Exception {
        List<HashMap<String, Object>> resultMap = mainService.mainBoradList();

        for (int i = 0; i < resultMap.size(); i++) {
            String contentText = resultMap.get(i).get("content").toString();
            //줄바꿈 text가 있다면
            if(contentText.contains("\r\n")) {
                List<String> text = Arrays.asList(contentText.split("\r\n"));
                resultMap.get(i).put("contentArr", text);
            }
        }
        model.addAttribute("mainList", resultMap);
        return UrlUtil.url("Main", request);
    }

    @ResponseBody
    @PostMapping(value = "/naverNewsApi",  name = "네이버 뉴스 api")
    public String naverNews(HttpServletRequest req) {
        naverNewsApi naverNewsApi = new naverNewsApi();
        return naverNewsApi.search(req.getParameter("searchData"));
    }
}
