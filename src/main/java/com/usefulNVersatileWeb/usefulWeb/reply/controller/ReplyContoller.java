package com.usefulNVersatileWeb.usefulWeb.reply.controller;

import com.usefulNVersatileWeb.usefulWeb.reply.vo.ReplyVo;
import groovy.util.logging.Log4j;
import groovy.util.logging.Slf4j;
import org.springframework.web.bind.annotation.*;

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
            map.put("replySeq", i+1);
            map.put("userSeq", i);
            map.put("boardSeq", i);
            map.put("replyContent", "와웅!"+i);
            map.put("firstUpdate", "2021.01.01");
            map.put("lastUpadte", "2021.01.01");
            list.add(map);
        }
        return list;
    }

    @PostMapping(value = "/insert", name = "댓글 입력")
    public List<Map<String, Object>> registerInsert(ReplyVo replyVo) {
        return null;
    }

    @PostMapping(value = "/update", name = "댓글 수정")
    public List<Map<String, Object>> registerUpdate(ReplyVo replyVo) {
        return null;
    }
}
