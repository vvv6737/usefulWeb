package com.usefulNVersatileWeb.usefulWeb.reply.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reply")
public class ReplyContoller {

    @GetMapping(value = "/list", name = "해당글의 댓글 조회")
    public List<Map<String, Object>> registerView() {
        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put(""+i, "댓글데이터 " + i);
            list.add(map);
        }
        return list;
    }
}
